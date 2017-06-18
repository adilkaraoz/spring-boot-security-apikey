package com.adilkaraoz.spring.boot.apikey;

import javax.servlet.Filter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApiKeyValidatorService.class)
public class ApiKeyAutoConfiguration {

	@Bean(name = "apiKeyFilter")
	public Filter apiKeyFilter() {
		return new ApiKeyFilter(validatorService());
	}
	
	@Bean(name = "validatorService")
	public ApiKeyValidatorService validatorService() {
		return new ApiKeyValidatorService();
	}

	@Bean(name = "defaultApiKeyValidator")
	public DefaultApiKeyValidator getDefaultApiKeyValidator() {
		return new DefaultApiKeyValidator();
	}
}
