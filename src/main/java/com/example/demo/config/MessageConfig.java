package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MessageConfig {

	@Bean
	public MessageSource messageSource(){
		var source = new ReloadableResourceBundleMessageSource();
		//メッセージファイル指定
		source.setBasenames(
				"classpath:i18n/messages",
				"classpath:i18n/ValidationMessages");
		//文字コード指定
		source.setDefaultEncoding("UTF-8");
		//デフォルト指定
		source.setFallbackToSystemLocale(false);
		return source;
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		var bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
