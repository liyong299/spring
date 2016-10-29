package com.ly.test.spring.validation;

import java.io.Serializable;

import com.smart.validate.ValidateBean;

/**
 * @version 1.0
 * @description
 * @ClassName: User
 * @date: 2016/6/5 16:35
 */
@ValidateBean
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3119475606955167074L;

	private String tenantKey;
    private String id;
    private String name;
    private String code;
    private String telphone;
    private String email;
	private int age;
	private boolean gender;

    /**
     *启用状�?(1-启用�?0-禁用)
     */
    private int enable;
    /**
     *创建时间
     */
    private long createTime;
    /**
     *修改时间
     */
    private long updateTime;

    public String getTenantKey() {
        return tenantKey;
    }

    public void setTenantKey(String tenantKey) {
        this.tenantKey = tenantKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public boolean isGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(boolean gender) {
		this.gender = gender;
	}

}
