package com.ccloomi.core.component.sms.cl;

import com.ccloomi.core.component.sms.SmsCodeUtil;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ClSmsCodeUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:23:27
 */
public class ClSmsCodeUtil extends SmsCodeUtil{
	static{
		map.put("0", "提交成功");
		map.put("101", "无此用户");
		map.put("102", "密码错误");
		map.put("103", "提交过快");
		map.put("104", "系统忙碌");
		map.put("105", "敏感短信");
		map.put("106", "消息过长");
		map.put("107", "错误号码");
		map.put("108", "号码超量");
		map.put("109", "短信用完");
		map.put("110", "非法时间");
		map.put("111", "短信超额");
		map.put("112", "无此产品");
		map.put("113", "格式错误");
		map.put("115", "审核失败");
		map.put("116", "非法签名");
		map.put("117", "IP错误");
		map.put("118", "无发送权");
		map.put("119", "用户过期");
	}
	public static String getSmsCodeDesc(String code){
		return map.get(code);
	}
}
