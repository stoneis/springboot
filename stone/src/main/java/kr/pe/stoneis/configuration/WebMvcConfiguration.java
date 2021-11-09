package kr.pe.stoneis.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.pe.stoneis.interceptor.LoggerInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
	}
	
	@Bean
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = 
				new CommonsMultipartResolver();
		// 파일 인코딩
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		// 5MB 크기 제한
		commonsMultipartResolver.setMaxUploadSizePerFile(5*1024*1024);
		return commonsMultipartResolver;
	}
	
	/** 스프링 부트 2.1.x 버전 이후부터는 이미 인코딩 필터가 적용되어있음. 이걸 적용시 톰켓 기동 안됨.
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		return characterEncodingFilter;
	}
	
	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}
	*/
	
}
