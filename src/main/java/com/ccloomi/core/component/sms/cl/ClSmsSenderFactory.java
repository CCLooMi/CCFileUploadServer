package com.ccloomi.core.component.sms.cl;

import java.util.Map;

import com.ccloomi.core.component.sms.SmsSender;
import com.ccloomi.core.component.sms.SmsSenderFactory;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ClSmsSenderFactory
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:23:17
 */
public class ClSmsSenderFactory extends SmsSenderFactory{

	protected ClSmsSenderFactory(){
		super();
	}
	public static synchronized SmsSenderFactory getInstance(){
		if(instance==null){
			instance=new ClSmsSenderFactory();
		}
		return instance;
	}

	@Override
	protected SmsSender smsSender() {
		Map<String, String>initP=getInitProperties();
		return new ClSmsSender(initP.get("url_cl"), initP.get("account"), initP.get("pswd"), initP.get("product"), initP.get("extno"));
	}
}
