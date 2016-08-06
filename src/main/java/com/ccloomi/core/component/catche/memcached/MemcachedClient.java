package com.ccloomi.core.component.catche.memcached;

import java.util.Date;

import com.ccloomi.core.component.catche.CacheClient;
import com.whalin.MemCached.MemCachedClient;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MemcachedClient
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:10:23
 */
public class MemcachedClient implements CacheClient{
	
	private MemCachedClient client;
	protected MemcachedClient(MemCachedClient client) {
		this.client=client;
	}
	@Override
	public Object get(String key) {
		return client.get(key);
	}

	@Override
	public boolean delete(String key) {
		return client.delete(key);
	}

	@Override
	public boolean keyExists(String key) {
		return client.keyExists(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key, Class<T> claz) {
		return (T) client.get(key);
	}

	@Override
	public boolean set(String key, Object value) {
		return client.set(key, value);
	}

	@Override
	public boolean add(String key, Object value) {
		return client.add(key, value);
	}

	@Override
	public boolean replace(String key, Object value) {
		return client.replace(key, value);
	}

	@Override
	public boolean set(String key, Object value, Date expiry) {
		return client.set(key, value, expiry);
	}

	@Override
	public boolean add(String key, Object value, Date expiry) {
		return client.add(key, value, expiry);
	}

	@Override
	public boolean replace(String key, Object value, Date expiry) {
		return client.replace(key, value, expiry);
	}

}
