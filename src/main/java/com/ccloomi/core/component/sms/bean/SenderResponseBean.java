package com.ccloomi.core.component.sms.bean;

import java.util.Date;

import com.ccloomi.core.common.bean.BaseBean;
import com.ccloomi.core.component.sms.cl.ClSmsCodeUtil;
import com.ccloomi.core.component.sms.yp.YpSmsCodeUtil;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SenderResponseBean
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:23:46
 */
public class SenderResponseBean extends BaseBean{
	private static final long serialVersionUID = -4530236832100102529L;
	private Date date;
	private String codeKey;
	private String code;
	private String smsid;
	public SenderResponseBean (){
		super();
	}
	public SenderResponseBean(String timeStr,String code,String smsid){
		this.date=new Date(Long.parseLong(timeStr));
		this.codeKey=code;
		if(this.code==null)this.code=YpSmsCodeUtil.getSmsCodeDesc(code);
		if(this.code==null)this.code=ClSmsCodeUtil.getSmsCodeDesc(code);
		this.smsid=smsid;
	}
	public SenderResponseBean(String timeStr,String code){
		this.date=new Date(Long.parseLong(timeStr));
		this.codeKey=code;
		if(this.code==null)this.code=YpSmsCodeUtil.getSmsCodeDesc(code);
		if(this.code==null)this.code=ClSmsCodeUtil.getSmsCodeDesc(code);
	}
	public SenderResponseBean(Date date,String code,String smsid){
		this.date=date;
		this.codeKey=code;
		if(this.code==null)this.code=YpSmsCodeUtil.getSmsCodeDesc(code);
		if(this.code==null)this.code=ClSmsCodeUtil.getSmsCodeDesc(code);
		this.smsid=smsid;
	}
	public SenderResponseBean(Date date,String code){
		this.date=date;
		this.codeKey=code;
		if(this.code==null)this.code=YpSmsCodeUtil.getSmsCodeDesc(code);
		if(this.code==null)this.code=ClSmsCodeUtil.getSmsCodeDesc(code);
	}
	/**
	 * 获取：date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * 设置：date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * 获取：codeKey
	 */
	public String getCodeKey() {
		return codeKey;
	}
	/**
	 * 设置：codeKey
	 */
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	/**
	 * 获取：code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：smsid
	 */
	public String getSmsid() {
		return smsid;
	}
	/**
	 * 设置：smsid
	 */
	public void setSmsid(String smsid) {
		this.smsid = smsid;
	}
}
