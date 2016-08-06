package com.ccloomi.core.component.sms.bean;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.ccloomi.core.common.bean.BaseBean;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Sms
 * 类 描 述：短信Bean
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:23:38
 */
public class Sms extends BaseBean{
	private static final long serialVersionUID = -3775491899488056248L;
	private Set<String> mobile=new HashSet<String>();
	private String msg;
	private boolean needstatus=true;
	public Sms addMobile(String...mobiles){
		for(String mobile:mobiles){
			this.mobile.add(mobile);
		}
		return this;
	}
	public Sms addMobile(Collection<? extends String>mobiles){
		this.mobile.addAll(mobiles);
		return this;
	}
	
	/**
	 * 获取：mobile
	 */
	public Set<String> getMobile() {
		return mobile;
	}
	/**
	 * 设置：mobile
	 */
	public void setMobile(Set<String> mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * 设置：msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * 获取：needstatus
	 */
	public boolean isNeedstatus() {
		return needstatus;
	}
	/**
	 * 设置：needstatus
	 */
	public void setNeedstatus(boolean needstatus) {
		this.needstatus = needstatus;
	}
}
