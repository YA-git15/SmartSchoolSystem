package com.sss.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sss.app.filter.AuthFilter;


@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
	//認証用フィルタの有効化
	@Bean
	public FilterRegistrationBean<AuthFilter> authFilter(){
		var bean =
				new FilterRegistrationBean<AuthFilter>(new AuthFilter());
		bean.addUrlPatterns("/events/*");
		bean.addUrlPatterns("/inqueries/*");
		bean.addUrlPatterns("/notices/*");
		bean.addUrlPatterns("/home");
		return bean;
	}
	
	// バリデーションメッセージのカスタマイズ
	@Override
	public Validator getValidator() {
		var validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		var messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("validation");
		return messageSource;
	}
	
	
	//uploadsフォルダをリソースとして利用可能にする
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**")
				.addResourceLocations("file:///C:/Users/zd1T01/uploads/");
	}
	
	
    
    
	
}
