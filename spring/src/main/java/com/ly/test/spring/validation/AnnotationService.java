package com.ly.test.spring.validation;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.smart.validate.rule.RegexpValidate;

@Service
public class AnnotationService {

	@MethodAnnotation(regex2 = @RegexpValidate(name = "user.id", pattern = "\\d+"))
	public User queryUser2(MyHttpRequest request) {
		User user = new User();
		user.setCode("校验通过");
		return user;
	}

	@MethodAnnotation(regex2 = @RegexpValidate(name = "id", pattern = "\\d+"), jsonRoot = "user")
	public User queryUser3(MyHttpRequest request) {
		User user = new User();
		user.setCode("校验通过");
		return user;
	}

	@MethodAnnotation(jsonRoot = "user", fieldValidateClasses = { AddUserFieldRule.class })
	public User queryUser4(MyHttpRequest request) {
		User user = new User();
		user.setCode("校验通过");
		return user;
	}
}

class MyHttpRequest {
	private Map<String, Object> params;

	public Object getParamter(String paramName) {
		return params == null ? null : params.get(paramName);
	}
	/**
	 * @return the params
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}