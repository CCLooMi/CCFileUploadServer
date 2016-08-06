package com.ccloomi.core.component.catche;

import java.util.Date;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：CacheClient
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:11:20
 */
public interface CacheClient {
	
	public Object get(String key);
	public boolean delete(String key);
	public boolean keyExists(String key);
	public <T> T get(String key,Class<T>claz);
	public boolean set(String key, Object value);
	public boolean add(String key, Object value);
	public boolean replace(String key, Object value);
	public boolean set(String key, Object value, Date expiry);
	public boolean add(String key, Object value, Date expiry);
	public boolean replace(String key, Object value, Date expiry);
}
