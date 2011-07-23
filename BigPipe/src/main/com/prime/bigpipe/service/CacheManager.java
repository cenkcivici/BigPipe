package com.prime.bigpipe.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class CacheManager {

	private ConcurrentHashMap<String, String> cacheMap = new ConcurrentHashMap<String, String>();
	
	public String get(String key) {
		return cacheMap.get(key);
	}

	public void put(String key, String content, long ttlInSeconds) {
		cacheMap.put(key, content);
	}

}
