package com.rz.web;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.web.base.BaseController;

/**
 * 使用信号限流
 * @author 18217
 */
@RestController
@RequestMapping("/semaphore")
public class SemaphoreController extends BaseController {
	
	private  Semaphore semaphore = new Semaphore(3);
	
	@RequestMapping("/getdataOfSemaphore")
	public String getData() {
		
//		如果阻塞队列中排队的请求过多超出系统处理能力，则可以在拒绝请求。
		if(semaphore.getQueueLength()>5) {
			
			return "当前等待排队的任务数大于5，请稍候再试...";
			
		}else {
			try {
				semaphore.acquire();
				// 处理核心逻辑
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaphore.release();
			}
			return sdf.format(new Date());
		}
			
		
		
	}

}
