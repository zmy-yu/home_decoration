package com.example.home_decoration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器注册
 */
@Configuration
public class InterceptorRegister implements WebMvcConfigurer {

//	@Autowired
//	UserInterceptor userInterceptor;

	@Autowired
	JwtInterceptor jwtInterceptor;

	/**
	 * 添加拦截器，并配置拦截地址
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> pathPatterns = new ArrayList<>();
		pathPatterns.add("/user/update");
		pathPatterns.add("/user/delete");
		pathPatterns.add("/user/userupload");

		pathPatterns.add("/worker/add");
		pathPatterns.add("/worker/update");
		pathPatterns.add("/worker/delete");
		pathPatterns.add("/worker/updateprices");

		pathPatterns.add("/order/update");
		pathPatterns.add("/order/updateHistoryOrder");
		pathPatterns.add("/order/add");
		pathPatterns.add("/order/delete");
		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns(pathPatterns);

//		List<String> str = new ArrayList<>();
//		str.add("/test/*");
//		registry.addInterceptor(jwtInterceptor)
//				.addPathPatterns(str);
	}


}