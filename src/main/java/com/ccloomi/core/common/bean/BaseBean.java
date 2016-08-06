package com.ccloomi.core.common.bean;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseBean
 * 类 描 述：Base基类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:08:28
 */
public abstract class BaseBean implements Serializable{
	private static final long serialVersionUID = -1922501725683869581L;
	protected Logger log=LoggerFactory.getLogger(getClass());
	/**
	 * 方法描述：转化为JSON字符串
	 * 作        者：Chenxj
	 * 日        期：2015年6月23日-下午3:28:27
	 */
	@Override
	public String toString(){
		ObjectMapper om=new ObjectMapper();
		try {
			return om.writeValueAsString(this);
		} catch (Exception e) {
			return super.toString();
		}finally{
			om=null;
		}
	}
}
