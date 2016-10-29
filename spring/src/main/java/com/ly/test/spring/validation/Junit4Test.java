package com.ly.test.spring.validation;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.smart.validate.rule.RegexpValidate;

/**
 * @功能详解：
 * @author ly
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/java/com/ly/test/spring/validation/spring-validation-context.xml")
public class Junit4Test {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testGetKeyByReflect() {

		RegexpValidate annoTmp;
		try {
			annoTmp = new AddUserFieldRule().getRegexpValidate(RegexpValidate.class, "name", null, "*");
			String key = new AnnotationServiceAOP().getKeyByReflect(annoTmp);
			System.out.println(key);
			Assert.isTrue("name".equals(key));
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testAddUser() {

		User user = new User();
		user.setId("aaaa");

		UserService aa = (UserService) applicationContext.getBean("userService");

		aa.addUser(user);
	}

	/**
	 * 基础注解练习
	 */
	@Test
	public void testQueryUserKeyValueError() {

		AnnotationService aa = (AnnotationService) applicationContext.getBean("annotationService");
		Map<String, Object> params = new HashMap<String, Object>();
		MyHttpRequest request2 = new MyHttpRequest();
		params.clear();
		params.put("id", "abc");
		request2.setParams(params);
		User user2 = aa.queryUser3(request2);
		Assert.isTrue(true);
	}

	/**
	 * 基础注解练习
	 */
	@Test
	public void testQueryUserKeyValueRight() {

		AnnotationService aa = (AnnotationService) applicationContext.getBean("annotationService");
		Map<String, Object> params = new HashMap<String, Object>();
		MyHttpRequest request2 = new MyHttpRequest();
		params.clear();
		params.put("id", "1245");
		request2.setParams(params);
		User user2 = aa.queryUser3(request2);
		Assert.isTrue(true);
	}

	/**
	 * 基础注解练习
	 */
	@Test
	public void testQueryUserJsonError() {
		MyHttpRequest request = new MyHttpRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		User user = new User();
		user.setId("aaaa");
		params.put("user", JSONObject.toJSONString(user));
		request.setParams(params);

		AnnotationService aa = (AnnotationService) applicationContext.getBean("annotationService");
		User user1 = aa.queryUser2(request);
		Assert.isTrue(true);
	}

	/**
	 * 基础注解练习
	 */
	@Test
	public void testQueryUserJsonRight() {
		MyHttpRequest request = new MyHttpRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		User user = new User();
		user.setId("1235");
		params.put("user", JSONObject.toJSONString(user));
		request.setParams(params);

		AnnotationService aa = (AnnotationService) applicationContext.getBean("annotationService");
		User user1 = aa.queryUser2(request);
		Assert.isTrue(true);
	}

	/**
	 * 基础注解练习
	 */
	@Test
	public void testQueryUserJsonHasError() {
		MyHttpRequest request = new MyHttpRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		User user = new User();
		user.setId("1235");
		user.setCode("webde"); // 错误
		params.put("user", JSONObject.toJSONString(user));
		request.setParams(params);

		AnnotationService aa = (AnnotationService) applicationContext.getBean("annotationService");
		User user1 = aa.queryUser2(request);
		Assert.isTrue(true);
	}

	/**
	 * 基础注解练习
	 */
	@Test
	public void testQueryUserJsonAllRight() {
		MyHttpRequest request = new MyHttpRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		User user = new User();
		user.setId("1235");
		user.setCode("1245");
		params.put("user", JSONObject.toJSONString(user));
		request.setParams(params);

		AnnotationService aa = (AnnotationService) applicationContext.getBean("annotationService");
		User user1 = aa.queryUser2(request);
		Assert.isTrue(true);
	}

	/**
	 * 基础注解练习
	 */
	@Test
	public void testQueryUserKeyValueHasError() {

		AnnotationService aa = (AnnotationService) applicationContext.getBean("annotationService");
		Map<String, Object> params = new HashMap<String, Object>();
		MyHttpRequest request2 = new MyHttpRequest();
		params.clear();
		params.put("id", "1245");
		params.put("code", "we23234");
		request2.setParams(params);
		User user2 = aa.queryUser3(request2);
		Assert.isTrue(true);
	}

	/**
	 * 基础注解练习
	 */
	@Test
	public void testQueryUserKeyValueAllRight() {

		AnnotationService aa = (AnnotationService) applicationContext.getBean("annotationService");
		Map<String, Object> params = new HashMap<String, Object>();
		MyHttpRequest request2 = new MyHttpRequest();
		params.clear();
		params.put("id", "1245");
		params.put("code", "23234");
		request2.setParams(params);
		User user2 = aa.queryUser3(request2);
		Assert.isTrue(true);
	}

	/**
	 * 复杂对象校验
	 */
	@Test
	public void testQueryUserCompix() {
		MyHttpRequest request = new MyHttpRequest();
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("user", JSONObject.toJSONString(getUser1()));
		request.setParams(params);

		AnnotationService aa = (AnnotationService) applicationContext.getBean("annotationService");
		User user1 = aa.queryUser4(request);
		Assert.isTrue(true);
	}

	private User getUser1() {
		User user = new User();
		user.setId("1235");
		user.setCode("1245");
		user.setAge(20);
		user.setEmail("liyong@mopon.sz");
		user.setEnable(0);
		user.setName("liyong");
		user.setTelphone("0755-23561487");
		user.setTenantKey("ZY");
		user.setCreateTime(Calendar.getInstance().getTimeInMillis());
		user.setUpdateTime(Calendar.getInstance().getTimeInMillis());
		return user;
	}

	/**
	 * 占位符格式化练习
	 */
	@Test
	public void testInstanceOf() {

		int a = 20;
		System.out.println(Double.valueOf("0.215d"));
		System.out.println(Float.valueOf("0.22f"));

		System.out.println(Double.valueOf("0.215"));
		System.out.println(Float.valueOf("0.22"));

		System.out.println(Double.valueOf("233"));
		System.out.println(Float.valueOf("124"));
		System.out.println(MessageFormat.format("%s必然战胜%s", "aa", "bb"));
		System.out.println(MessageFormat.format("[{0}]必然战胜[{1}]", "aa"));
		System.out.println(String.format("%s必然战胜%s", "aa", "bb"));


		System.out.println("".matches("\\d*"));
		Assert.isTrue("200".toString().matches("\\d+"));
		System.out.println(String.format("%s必然战胜%s", "aa"));
	}

	/**
	 * 占位符格式化练习
	 */
	@Test
	public void testMessageFormat() {

		String pattern = "%s=%s";
		System.out.println(String.format(pattern, "aa", "bb"));
	}
}
