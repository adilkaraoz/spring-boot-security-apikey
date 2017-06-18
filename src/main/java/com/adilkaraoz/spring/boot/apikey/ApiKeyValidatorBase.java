package com.adilkaraoz.spring.boot.apikey;

public interface ApiKeyValidatorBase {
	boolean validate(String apiKey, String requestURI);
	
	boolean isEnabled();
}
