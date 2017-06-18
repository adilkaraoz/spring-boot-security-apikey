package com.adilkaraoz.spring.boot.apikey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiKeyFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(ApiKeyFilter.class);
	
	private final String API_KEY_HEADER_NAME = "X-API-KEY";

	private final ApiKeyValidatorService validator;

    public ApiKeyFilter(final ApiKeyValidatorService validator) {
    	this.validator = validator;
    }

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(
			final ServletRequest request, 
			final ServletResponse response, 
			final FilterChain chain)
			throws IOException, ServletException {
        
		log.warn("[ApiKeyFilter] doFilter()");

		if (!this.validator.isEnabled()) {
			log.warn("[ApiKeyFilter] API Key is disabled");
			chain.doFilter(request, response);
			return;
		}

		final String apiKey = ((HttpServletRequest)request).getHeader(API_KEY_HEADER_NAME);
		String requestURI = ((HttpServletRequest)request).getRequestURI();
		
		log.warn("contextPath: " + requestURI);
		
		String apiKeyError = this.validator.validateRequestApiKey(apiKey, requestURI);
		
		if (apiKeyError == null) {
			log.debug("[ApiKeyFilter] API Key is valid");
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, apiKeyError);
	}

	@Override
	public void destroy() {
	}
}
