package com.ccloomi.core.common.factory;

import java.util.Map;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseFactory
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:09:59
 */
public abstract class BaseFactory {
	protected Map<String, String>initProperties;
	/**
	 * 获取：初始化参数
	 */
	public Map<String, String> getInitProperties() {
		return initProperties;
	}
	/**
	 * 设置：初始化参数
	 */
	public void setInitProperties(Map<String, String> initProperties) {
		this.initProperties = initProperties;
	}
}
