package com.ly.test.spring.cacheable;

import java.io.Serializable;

/**
 * 渠道设置。
 */
public class ChannelSettings implements Serializable {
	/**
	 * serialVersionUID属性说明:
	 */
	private static final long serialVersionUID = 126214425607008950L;
	/** ID */
	private String id;

	private Channel channel;
	/** 接口日志长度 */
	private Integer logLength = 1000;

	/** 字符串格式的开放的票务接口CODE 接收字段 */
	private String apiMethodCodes;
	/** 停售时间 */
	private Integer stopSellTime = 30;
	/** 停退时间 */
	private Integer stopRevokeTime = 60;
	/** 提前开放所有区域的时间 */
	private Integer preAreaTime = 0;

	/** 时段规则的字符串表示变量用来接收数据库查询出来的数据：添加原因：原来用的hibernate 现在用的Mybatis*/
	private String periodRuleStr;

	public Integer getPreAreaTime() {
		return preAreaTime;
	}

	public void setPreAreaTime(Integer preAreaTime) {
		this.preAreaTime = preAreaTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Integer getLogLength() {
		return logLength;
	}

	public void setLogLength(Integer logLength) {
		this.logLength = logLength;
	}

	public Integer getStopSellTime() {
		return stopSellTime;
	}

	public void setStopSellTime(Integer stopSellTime) {
		this.stopSellTime = stopSellTime;
	}

	public Integer getStopRevokeTime() {
		return stopRevokeTime;
	}

	public void setStopRevokeTime(Integer stopRevokeTime) {
		this.stopRevokeTime = stopRevokeTime;
	}

	public String getApiMethodCodes() {
		return apiMethodCodes;
	}

	public void setApiMethodCodes(String apiMethodCodes) {
		this.apiMethodCodes = apiMethodCodes;
	}

	public String getPeriodRuleStr() {
		return periodRuleStr;
	}

	public void setPeriodRuleStr(String periodRuleStr) {
		this.periodRuleStr = periodRuleStr;
	}
	
	
}
