package com.ly.test.spring.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.smart.validate.SmartValidate;
import com.smart.validate.rule.RegexpValidate;

@Aspect
@Component
public class AnnotationServiceAOP {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.ly.test.spring.validation.AnnotationService.*(..))")
	public void aroundJoin() {}

	@Around("aroundJoin()")
	public Object around(ProceedingJoinPoint point) {
		if (logger.isDebugEnabled())
			logger.debug("@before：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());

		StopWatch watch = new StopWatch();
		watch.start();

		Object obj = null;
		try {

			Signature signature = point.getSignature();
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();
			Object[] arguments = point.getArgs();
			MyHttpRequest request = (MyHttpRequest) arguments[0];

			validate(request, method);

			obj = point.proceed();

		} catch (Throwable ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		}
		if (watch.isRunning())
			watch.stop();

		if (logger.isDebugEnabled())
			logger.debug("@after：执行时间为： [" + watch.getTotalTimeMillis() + "]毫秒");
		return obj;
	}

	/**
	 * 校验method方法中request参数
	 * @param request
	 * @param method
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private boolean validate(MyHttpRequest request, Method method) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		MethodAnnotation annotation = method.getAnnotation(MethodAnnotation.class);
		if (StringUtils.isEmpty(annotation.jsonRoot())) {
			validate(method, request, annotation);
		} else {
			String jsonStr = request.getParamter(annotation.jsonRoot()).toString();
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			validate(method, jsonObject, annotation);
		}
		return true;
	}

	/**
	 * request中参数是Json对象时的校验
	 * @param method
	 * @param jsonObject
	 * @param annotation
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private void validate(Method method, JSONObject jsonObject, MethodAnnotation annotation) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		RegexpValidate[] regexAnnots = annotation.regex2();
		for (RegexpValidate regexAnnot : regexAnnots) {
			String key = regexAnnot.name();
			String value = getJsonValueByKey(key, jsonObject);
			SmartValidate.validate(key, value, regexAnnot);
		}

		Class<? extends FieldValidateRule>[] clazzs = annotation.fieldValidateClasses();
		for (Class<? extends FieldValidateRule> clazz : clazzs) {
			FieldValidateRule rule = clazz.newInstance();
			List<Annotation> list = rule.getRules();
			for (Annotation annoTmp : list) {
				String key = getKeyByReflect(annoTmp);
				String value = getJsonValueByKey(key, jsonObject);
				SmartValidate.validate(key, value, annoTmp);
			}
		}
	}

	/**
	 * 通过反射从注解中获取需要校验的字段信息。反射方法名确定为“name”
	 * @param annoTmp
	 * @param jsonObject
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public String getKeyByReflect(Annotation annoTmp) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method nameMethod = annoTmp.getClass().getMethod("name");
		String key = (String) nameMethod.invoke(annoTmp);
		return key;
	}

	/**
	 * Json多层嵌套时，获取key路径对应的value值的校验。
	 * @举例：key格式：user.order.id。json格式："user":{"order":{"id":"aa", "code":"ab001"}, "name" : "john"}
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	private String getJsonValueByKey(String key, JSONObject jsonObject) {
		if (StringUtils.isEmpty(key) || jsonObject == null)
			return null;
		System.out.println(key);
		int idx = key.indexOf(".");
		if (idx > -1) {
			String subRoot = key.substring(0, idx);
			String subKey = key.substring(idx + 1);
					JSONObject subObj = jsonObject.getJSONObject(subRoot);
			return getJsonValueByKey(subKey, subObj);
		} else {
			return jsonObject.getString(key);
		}
	}

	/**
	 * request中参数是key\value对象时的校验
	 * @param method
	 * @param request
	 * @param annotation
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private void validate(Method method, MyHttpRequest request, MethodAnnotation annotation) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		RegexpValidate[] regexAnnots = annotation.regex2();
		for (RegexpValidate regexAnnot : regexAnnots) {
			String key = regexAnnot.name();
			String value = request.getParamter(key).toString();
			SmartValidate.validate(key, value, regexAnnot);
		}

		Class<? extends FieldValidateRule>[] clazzs = annotation.fieldValidateClasses();
		for (Class<? extends FieldValidateRule> clazz : clazzs) {
			FieldValidateRule rule = clazz.newInstance();
			List<Annotation> list = rule.getRules();
			for (Annotation annoTmp : list) {
				String key = getKeyByReflect(annoTmp);
				String value = request.getParamter(key).toString();
				SmartValidate.validate(key, value, annoTmp);
			}
		}
	}
}
