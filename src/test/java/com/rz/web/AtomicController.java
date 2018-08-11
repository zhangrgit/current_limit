package com.rz.web;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.web.base.BaseController;

/**
 * 使用计数器限流
 * @author 18217
 */
@RestController
@RequestMapping("/atomic")
public class AtomicController extends BaseController {
	
	private  AtomicInteger count = new AtomicInteger(0);
	
	@RequestMapping("/getdataOfCount")
	public String getData() {
		
		if(count.get()>=5) {//超过并发执行阀值，则直接相应系统繁忙
			return "当前请求用户过多，请稍后再试!";
		}else {
			//统计正在并发执行的次数
			count.incrementAndGet();
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				//并发执行完毕，统计数减一
				count.decrementAndGet();
			}
			return sdf.format(new Date());
		}
		
	}

}
