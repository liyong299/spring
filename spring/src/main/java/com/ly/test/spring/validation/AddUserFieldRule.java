package com.ly.test.spring.validation;

import com.smart.validate.rule.MaxLengthValidate;
import com.smart.validate.rule.MaxValueValidate;
import com.smart.validate.rule.MinLengthValidate;
import com.smart.validate.rule.MinValueValidate;
import com.smart.validate.rule.NotNullValidate;
import com.smart.validate.rule.RangeLengthValidate;
import com.smart.validate.rule.RangeValueValidate;
import com.smart.validate.rule.RegexpValidate;


/**
 * @功能描述：新增用户对象检查规则
 * @文件名称：UserFieldRule.java
 * @author ly
 */
public class AddUserFieldRule extends FieldValidateRule {

	@Override
	protected void addRules() throws InstantiationException, IllegalAccessException {
		rules.add(getMaxLengthValidate(MaxLengthValidate.class, "id", null, 10));
		rules.add(getMaxValueValidate(MaxValueValidate.class, "age", null, "200.0"));
		rules.add(getMinLengthValidate(MinLengthValidate.class, "name", null, 2));
		rules.add(getMinValueValidate(MinValueValidate.class, "age", null, "0.01"));
		rules.add(getNotNullValidate(NotNullValidate.class, "gender", null));
		rules.add(getRangeLengthValidate(RangeLengthValidate.class, "email", null, 1, 20));
		rules.add(getRangeValueValidate(RangeValueValidate.class, "enable", null, "0", "1"));
		rules.add(getRegexpValidate(RegexpValidate.class, "telphone", null, "[-\\d]+"));
	}
}
