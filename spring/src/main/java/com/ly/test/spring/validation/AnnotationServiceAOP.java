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
			logger.debug("@before��Ŀ�귽��Ϊ��" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());

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
			logger.debug("@after��ִ��ʱ��Ϊ�� [" + watch.getTotalTimeMillis() + "]����");
		return obj;
	}

	/**
	 * У��method������request����
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
	 * request�в�����Json����ʱ��У��
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
	 * ͨ�������ע���л�ȡ��ҪУ����ֶ���Ϣ�����䷽����ȷ��Ϊ��name��
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
	 * Json���Ƕ��ʱ����ȡkey·����Ӧ��valueֵ��У�顣
	 * @������key��ʽ��user.order.id��json��ʽ��"user":{"order":{"id":"aa", "code":"ab001"}, "name" : "john"}
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
	 * request�в�����key\value����ʱ��У��
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
