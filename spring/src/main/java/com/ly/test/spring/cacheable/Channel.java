package com.ly.test.spring.cacheable;

import java.io.Serializable;

/**
 * 渠道。
 */
public class Channel implements Serializable {
	/**
	 * serialVersionUID属性说明:
	 */
	private static final long serialVersionUID = -7643682909323678598L;
	private String id;
	/** 渠道名称 */
	private String name;
	/** 渠道编号 */
	
	
	private String code;
	/** 网络代售商编号 */
	
	
	private String onlineSaleCode;
	/** 通讯秘钥 */
	private String secKey;

	/** 可售状态 */
	
	private Boolean salable = true;
	/** 开放状态 */
	
	private Boolean opened = true;
	/** 备注 */
	private String remark;
	/** 关联渠道设置 */
	private ChannelSettings settings;

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

	public String getSecKey() {
		return secKey;
	}

	public void setSecKey(String secKey) {
		this.secKey = secKey;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getSalable() {
		return salable;
	}

	public void setSalable(Boolean salable) {
		this.salable = salable;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
	}

	public String getOnlineSaleCode() {
		return onlineSaleCode;
	}

	public void setOnlineSaleCode(String onlineSaleCode) {
		this.onlineSaleCode = onlineSaleCode;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


		/**
	 * @return the settings
	 */
	public ChannelSettings getSettings() {
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	public void setSettings(ChannelSettings settings) {
		this.settings = settings;
	}
}