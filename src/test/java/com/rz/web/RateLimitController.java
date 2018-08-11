package com.rz.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rz.service.RateLimitService;
import com.rz.web.base.BaseController;
/**
 * 令牌桶限流
 * @author 18217
 */
@Controller
@RequestMapping("/rateLimit")
public class RateLimitController extends BaseController{
	
	@Autowired
	private RateLimitService service;
	
	@RequestMapping("/access")
	@ResponseBody
	public String access() {
		
		//尝试获取令牌
		if(service.tryAcquire()) {
			//模拟业务执行時間
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
             return "aceess success [" + sdf.format(new Date()) + "]";
		}else {
			 return "aceess limit [" + sdf.format(new Date()) + "]";
		}
	}
	
	@RequestMapping("/que")
	public String que(Model model) {
		//尝试获取令牌
		if(service.tryAcquire()) {
            try {
            	//模拟业务执行時間
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "success";
		}else {
			return "queue";
		}
	}
	
}
