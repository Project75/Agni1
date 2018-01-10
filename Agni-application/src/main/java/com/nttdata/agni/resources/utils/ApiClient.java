package com.nttdata.agni.resources.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiClient {
	private static final Logger log = LoggerFactory.getLogger(ApiClient.class);
	static String baseURL = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3/";

    public static void getResource(String resourceName, String resourceId) throws JsonProcessingException, IOException 
    {
    	RestTemplate restTemplate = new RestTemplate();
        
    	//String ResourceUrl	  = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3/Patient/54";
    	String ResourceUrl = baseURL+"/"+resourceName+"/"+resourceId;
    	ResponseEntity<String> response	  = restTemplate.getForEntity(ResourceUrl, String.class);

    	log.info(response.getBody());
    	
    	// Code to retrieve and specific field from Fhir json response
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode root = mapper.readTree(response.getBody());
    	JsonNode familyname = root.path("name").path(0).path("family");
    	log.info(familyname.asText());
       
    }
    
    public static void postResource(String payload,String resourceName ) throws JsonProcessingException, IOException 
    {
    	RestTemplate restTemplate = new RestTemplate();
        
    	//String ResourceUrl	  = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3/Patient/";
    	String ResourceUrl = baseURL+"/"+resourceName;

    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    	String requstJson = "{\r\n" + 
    			"  \"resourceType\": \"Patient\",\r\n" + 
    			"  \"id\": \"121216\",\r\n" + 
    			"  \"meta\": {\r\n" + 
    			"    \"versionId\": \"1\",\r\n" + 
    			"    \"lastUpdated\": \"2017-07-17T20:48:47.274-04:00\"\r\n" + 
    			"  },\r\n" + 
    			"  \"text\": {\r\n" + 
    			"    \"status\": \"generated\",\r\n" + 
    			"    \"div\": \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><div class=\\\"hapiHeaderText\\\">Mr Ayush <b>Ayush </b></div><table class=\\\"hapiPropertyTable\\\"><tbody><tr><td>Date of birth</td><td><span>14 February 1984</span></td></tr></tbody></table></div>\"\r\n" + 
    			"  },\r\n" + 
    			"  \"name\": [\r\n" + 
    			"    {\r\n" + 
    			"      \"family\": \"Doe\",\r\n" + 
    			"      \"given\": [\r\n" + 
    			"        \"JOhn\",\r\n" + 
    			"        \"A\"\r\n" + 
    			"      ],\r\n" + 
    			"      \"prefix\": [\r\n" + 
    			"        \"Mr.\"\r\n" + 
    			"      ]\r\n" + 
    			"    }\r\n" + 
    			"  ],\r\n" + 
    			"  \"telecom\": [\r\n" + 
    			"    {\r\n" + 
    			"      \"system\": \"phone\",\r\n" + 
    			"      \"value\": \"07 50505050\",\r\n" + 
    			"      \"use\": \"home\"\r\n" + 
    			"    },\r\n" + 
    			"    {\r\n" + 
    			"      \"system\": \"email\",\r\n" + 
    			"      \"value\": \"john.Doe@yahoo.com\"\r\n" + 
    			"    },\r\n" + 
    			"    {\r\n" + 
    			"      \"system\": \"phone\",\r\n" + 
    			"      \"value\": \"09781212121\",\r\n" + 
    			"      \"use\": \"mobile\"\r\n" + 
    			"    }\r\n" + 
    			"  ],\r\n" + 
    			"  \"gender\": \"male\",\r\n" + 
    			"  \"birthDate\": \"1984-02-14\"\r\n" + 
    			"}";
    	
    	HttpEntity<String> request = new HttpEntity<String>(payload, httpHeaders );
    	URI location = restTemplate.postForLocation(ResourceUrl, request);
    	//restTemplate.exchange(requestEntity, responseType)
    	log.info(location.toString());
       
    }
}
