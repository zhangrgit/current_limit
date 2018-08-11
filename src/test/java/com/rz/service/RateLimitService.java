package com.rz.service;

import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.RateLimiter;

@Service
public class RateLimitService {
	
	// 每秒只发出令牌个数(实际可获取令牌比设置数多1)
	RateLimiter rateLimiter = RateLimiter.create(1);
	
	/**
	 * 尝试获取令牌
	 * @return
	 */
	public boolean tryAcquire() {
		//默认每次取1个
		return rateLimiter.tryAcquire(10);
		
	}

}
