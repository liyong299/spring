package com.ly.test.spring.cacheable;

import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @description
 * @ClassName: User
 * @author: gyb
 * @date: 2016/6/5 16:35
 */
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

	private Card card;

	private List<Order> orders;

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
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
