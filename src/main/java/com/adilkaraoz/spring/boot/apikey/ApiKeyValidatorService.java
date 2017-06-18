package com.adilkaraoz.spring.boot.apikey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;

@ConfigurationProperties("com.adilkaraoz.spring.boot.apikey")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class ApiKeyValidatorService {

	private static Logger log = LoggerFactory.getLogger(ApiKeyValidatorService.class);
	
	private ApiKeyValidatorBase apiKeyValidator;

	@Value("${com.adilkaraoz.spring.boot.apikey.required:Unauthorized}")
	private String apiKeyRequiredError;

	@Value("${com.adilkaraoz.spring.boot.apikey.invalid:Full authentication is required to access this resource (401.400)}")
	private String invalidApiKeyError;

	@Value("${com.adilkaraoz.spring.boot.apikey.exception:Full authentication is required to access this resource (401.500)}")
	private String unexpectedApiKeyError;

	public ApiKeyValidatorService() {
	}

	public boolean isEnabled() {
		return this.apiKeyValidator == null ? false : this.apiKeyValidator.isEnabled();
	}

	public String validateRequestApiKey(final String apiKey, String requestURI) {

		log.debug("[ApiKeyValidator] validateRequestApiKey()");

		if (this.isEnabled()) {
			log.debug("[ApiKeyValidatorService] API Key Enabled");

			if (apiKey == null || apiKey.equals("")) {
				log.warn("[ApiKeyValidatorService] Missing API Key HTTP Header");
				return this.apiKeyRequiredError;
			}

			try {
				if (!apiKeyValidator.validate(apiKey, requestURI)) {
					log.warn("[ApiKeyValidatorService] Invalid API Key Macaroon: " + apiKey);

					return this.invalidApiKeyError;
				}
			} catch (Exception macaroonException) {
				log.error("[ApiKeyValidatorService] API Key Macaroon Error (" + apiKey + "): "
						+ macaroonException.getMessage());

				return this.unexpectedApiKeyError;
			}
		}

		return null;
	}

	public ApiKeyValidatorBase getApiKeyValidator() {
		return apiKeyValidator;
	}

	public void setApiKeyValidator(ApiKeyValidatorBase apiKeyValidator) {
		this.apiKeyValidator = apiKeyValidator;
	}
}
