/**
 * 项目名称：spring
 * 文件包名：com.ly.test.spring.validation
 * 文件名称：FieldValidateRegex.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年8月24日 上午11:54:06
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.spring.validation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.smart.validate.rule.MaxLengthValidate;
import com.smart.validate.rule.MaxValueValidate;
import com.smart.validate.rule.MinLengthValidate;
import com.smart.validate.rule.MinValueValidate;
import com.smart.validate.rule.NotNullValidate;
import com.smart.validate.rule.RangeLengthValidate;
import com.smart.validate.rule.RangeValueValidate;
import com.smart.validate.rule.RegexpValidate;

/**
 * @功能描述：参数较多的情况下，全部放在注解，比较混乱，可以放在一个类中。
 * @文件名称：FieldValidateRegex.java
 * @author ly
 */
public abstract class FieldValidateRule {

	public FieldValidateRule() {
		try {
			addRules();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	};

	protected List<Annotation> rules = new ArrayList<Annotation>();

	protected abstract void addRules() throws InstantiationException, IllegalAccessException;

	public Annotation getMaxLengthValidate(final Class<? extends MaxLengthValidate> clazz, final String name,
			final String message, final int length) throws InstantiationException, IllegalAccessException {

		return new MaxLengthValidate() {

			public Class<? extends MaxLengthValidate> annotationType() {
				return clazz;
			}

			public String message() {
				return message;
			}

			public String name() {
				return name;
			}

			public int length() {
				return length;
			}
		};
	}

	public MaxValueValidate getMaxValueValidate(final Class<? extends MaxValueValidate> clazz, final String name,
			final String message, final String value) throws InstantiationException, IllegalAccessException {

		return new MaxValueValidate() {

			public Class<? extends MaxValueValidate> annotationType() {
				return clazz;
			}

			public String message() {
				return message;
			}

			public String name() {
				return name;
			}

			public String value() {
				return value;
			}
		};
	}

	public MinLengthValidate getMinLengthValidate(final Class<? extends MinLengthValidate> clazz, final String name,
			final String message, final int length) throws InstantiationException, IllegalAccessException {

		return new MinLengthValidate() {

			public Class<? extends MinLengthValidate> annotationType() {
				return clazz;
			}

			public String message() {
				return message;
			}

			public String name() {
				return name;
			}

			public int length() {
				return length;
			}
		};
	}

	public MinValueValidate getMinValueValidate(final Class<? extends MinValueValidate> clazz, final String name,
			final String message, final String value) throws InstantiationException, IllegalAccessException {

		return new MinValueValidate() {

			public Class<? extends MinValueValidate> annotationType() {
				return clazz;
			}

			public String message() {
				return message;
			}

			public String name() {
				return name;
			}

			public String value() {
				return value;
			}
		};
	}

	public NotNullValidate getNotNullValidate(final Class<? extends NotNullValidate> clazz, final String name,
			final String message) throws InstantiationException, IllegalAccessException {

		return new NotNullValidate() {

			public Class<? extends NotNullValidate> annotationType() {
				return clazz;
			}

			public String message() {
				return message;
			}

			public String name() {
				return name;
			}

		};
	}

	public RangeLengthValidate getRangeLengthValidate(final Class<? extends RangeLengthValidate> clazz, final String name,
			final String message, final int min, final int max) throws InstantiationException, IllegalAccessException {

		return new RangeLengthValidate() {

			public Class<? extends RangeLengthValidate> annotationType() {
				return clazz;
			}

			public String message() {
				return message;
			}

			public String name() {
				return name;
			}

			public int max() {
				return max;
			}

			public int min() {
				return min;
			}

		};
	}

	public RangeValueValidate getRangeValueValidate(final Class<? extends RangeValueValidate> clazz, final String name,
			final String message, final String min, final String max) throws InstantiationException, IllegalAccessException {

		return new RangeValueValidate() {

			public Class<? extends RangeValueValidate> annotationType() {
				return clazz;
			}

			public String message() {
				return message;
			}

			public String name() {
				return name;
			}

			public String min() {
				return min;
			}

			public String max() {
				return max;
			}

		};
	}

	public RegexpValidate getRegexpValidate(final Class<? extends RegexpValidate> clazz, final String name, final String message,
			final String pattern) throws InstantiationException, IllegalAccessException {

		return new RegexpValidate() {

			public Class<? extends RegexpValidate> annotationType() {
				return clazz;
			}

			public String pattern() {
				return pattern;
			}

			public String name() {
				return name;
			}

			public String message() {
				return message;
			}
		};
	}

	/**
	 * @return the rules
	 */
	public List<Annotation> getRules() {
		return rules;
	}

	/**
	 * @param rules the rules to set
	 */
	public void setRules(List<Annotation> rules) {
		this.rules = rules;
	}

}
