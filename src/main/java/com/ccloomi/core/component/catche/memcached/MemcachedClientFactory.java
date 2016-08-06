package com.ccloomi.core.component.catche.memcached;

import java.util.Map;

import com.ccloomi.core.component.catche.CacheClient;
import com.ccloomi.core.component.catche.CatcheClientFactory;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MemcachedClientFactory
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:10:43
 */
public class MemcachedClientFactory extends CatcheClientFactory{
	
	protected MemcachedClientFactory(){
		super();
	};
	public static synchronized CatcheClientFactory getInstance(){
		if(instance==null){
			instance=new MemcachedClientFactory();
		}
		return instance;
	}
	@Override
	public CacheClient cacheClient() {
		Map<String, String>map=getInitProperties();
		SockIOPool pool=SockIOPool.getInstance("whalinMemcachedPool");
		pool.setServers(map.get("servers"));
		pool.setWeights(map.get("weights"));
		pool.setInitConn(map.get("initConnections")==null?3:Integer.valueOf(map.get("initConnections")));
		pool.setMinConn(map.get("minConnections")==null?3:Integer.valueOf(map.get("minConnections")));
		pool.setMaxConn(map.get("maxConnections")==null?250:Integer.valueOf(map.get("maxConnections")));
		pool.setMaxIdle(map.get("maxIdle")==null?21600000:Integer.valueOf(map.get("maxIdle")));
		pool.setMaintSleep(map.get("maintSleep")==null?30:Integer.valueOf(map.get("maintSleep")));
		pool.setNagle(map.get("nagle")==null?false:Boolean.valueOf(map.get("nagle")));
		pool.setSocketTO(map.get("socketTO")==null?3000:Integer.valueOf(map.get("socketTO")));
		pool.setSocketConnectTO(map.get("socketConnectTO")==null?0:Integer.valueOf(map.get("socketConnectTO")));
		if(!pool.isInitialized()){
			pool.initialize();
		}
		MemCachedClient client=new MemCachedClient("whalinMemcachedPool");
		return new MemcachedClient(client);
	}

}
