package com.nttdata.agni.microservices;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


/**
 * Hide the access to the microservice inside this local service.
 * 
 * @author Harendra
 */
@Service
public class WebMappingService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebMappingService.class
			.getName());

	public WebMappingService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory().getClass());
	}

	public MappingList findByNumber(String accountNumber) {

		logger.info("findByNumber() invoked: for " + accountNumber);
		return restTemplate.getForObject(serviceUrl + "/mapping/{number}",
				MappingList.class, accountNumber);
	}

	public List<MappingList> byMapnameContains(String name) {
		logger.info("byMapnameContains() invoked:  for " + name);
		MappingList[] mapping = null;

		try {
			mapping = restTemplate.getForObject(serviceUrl
					+ "/mapping/{name}", MappingList[].class, name);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		if (mapping == null || mapping.length == 0)
			return null;
		else
			return Arrays.asList(mapping);
	}

	
}
