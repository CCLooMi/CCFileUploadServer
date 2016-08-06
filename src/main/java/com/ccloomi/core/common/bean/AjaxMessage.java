package com.ccloomi.core.common.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：AjaxMessage
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:08:49
 */
public class AjaxMessage extends BaseBean{
	private static final long serialVersionUID = 6150355000375833158L;
	/**code*/
	private String code;
	/**数据*/
	private Object data;
	public AjaxMessage addData(String key,Object value){
		if(data!=null && data instanceof Map){
			@SuppressWarnings("unchecked")
			Map<String, Object>map=(Map<String, Object>) data;
			map.put(key, value);
		}else{
			Map<String, Object>map=new HashMap<String, Object>();
			this.data=map;
			map.put(key, value);
		}
		return this;
	}
	public String getCode() {
		return code;
	}
	public AjaxMessage setCode(String code) {
		this.code = code;
		return this;
	}
	public Object getData() {
		return data;
	}
	public AjaxMessage setData(Object data) {
		this.data = data;
		return this;
	}
}
