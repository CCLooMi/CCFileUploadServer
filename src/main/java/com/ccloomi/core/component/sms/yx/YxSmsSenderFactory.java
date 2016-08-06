package com.ccloomi.core.component.sms.yx;

import java.util.Map;

import com.ccloomi.core.component.sms.SmsSender;
import com.ccloomi.core.component.sms.SmsSenderFactory;
/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：YxSmsSenderFactory
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:40:00
 */
public class YxSmsSenderFactory extends SmsSenderFactory{

	protected YxSmsSenderFactory() {
		super();
	}
	public static synchronized SmsSenderFactory getInstance(){
		if(instance==null){
			instance=new YxSmsSenderFactory();
		}
		return instance;
	}
	
	@Override
	protected SmsSender smsSender() {
		Map<String, String>initP=getInitProperties();
		return new YxSmsSender(initP.get("url_yx"),initP.get("version"), initP.get("account_sid"), initP.get("auth_token"), initP.get("app_id"));
	}

}
