package com.gyyx.factory;

import java.io.IOException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XMemClientFactory {
	private static MemcachedClient instance = null;
	synchronized public static MemcachedClient getInstance() {
		if (instance == null) {
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(
					AddrUtil.getAddresses("192.168.6.195:10001"));
			try {
				instance = builder.build();
			} catch (Exception e) {
			}
		}
		return instance;
	}
}