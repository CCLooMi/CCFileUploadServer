package com.ccloomi.core.component.sms;

import java.util.HashMap;
import java.util.Map;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SmsCodeUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:22:07
 */
public abstract class SmsCodeUtil {
	protected static Map<String, String>map=new HashMap<String, String>();
	public static String getSmsCodeDesc(String code){
		return map.get(code);
	}
}
