package com.nttdata.agni.resources.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiClient {
	private static final Logger log = LoggerFactory.getLogger(ApiClient.class);
	static String baseURL = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3/";

	
	public static Boolean getPatientByIdentifier(String resourceName, String patientId) throws JsonProcessingException, IOException 
    {
		String resourceUrl = baseURL+"/"+resourceName+"?identifier="+patientId;
		String response = getResourceContent(resourceUrl);
		Boolean status =false;
		if (response!=null){
    		log.debug("Response from Server is: \n"+response);
    		// =true;
	    	// Code to retrieve and specific field from Fhir json response
	    	ObjectMapper mapper = new ObjectMapper();
	    	JsonNode root = mapper.readTree(response);
	    	int total = root.path("total").asInt();
	    	log.info("total:"+total);
	    	if (total>0) status=true;
    	}
    	return status;
    }
	
	public static Boolean getResourceByServerId(String resourceName, String resourceId) throws JsonProcessingException, IOException 
    {
		String resourceUrl = baseURL+"/"+resourceName+"/"+resourceId;
		return getResourceStatus(resourceUrl);
    }
	
	public static Boolean getResourceStatus(String resourceUrl) throws JsonProcessingException, IOException 
    {
    	RestTemplate restTemplate = new RestTemplate();
        Boolean status =false;
    	//String ResourceUrl	  = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3/Patient/54";
    	
    	ResponseEntity<String> response	  = restTemplate.getForEntity(resourceUrl, String.class);
    	log.info("GET Response Code: "+response.getStatusCode());
    	if (response.getStatusCode().equals(HttpStatus.OK)){
    		//log.debug("Response from Server is: \n"+response.getBody());
    		status =true;
	    	// Code to retrieve and specific field from Fhir json response
	    	/*ObjectMapper mapper = new ObjectMapper();
	    	JsonNode root = mapper.readTree(response.getBody());
	    	JsonNode familyname = root.path("name").path(0).path("family");
	    	log.info(familyname.asText());*/
    	}
    	return status;
    }
	
	public static String getResourceContent(String resourceUrl) throws JsonProcessingException, IOException{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
		if (response.getStatusCode().equals(HttpStatus.OK)){
			return response.getBody();
		}else
			return null;
    }
    public static Boolean postResource(String payload,String resourceName ) 
    {
    	Character firstChar = resourceName.charAt(0);
    	if (Character.isLowerCase(firstChar)){
    		String updResourceName = Character.toUpperCase(firstChar)+resourceName.substring(1);
    		resourceName = updResourceName;
    	}
    	
    	RestTemplate restTemplate = new RestTemplate();
    	Boolean status =false;
    	//String ResourceUrl	  = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3/Patient/";
    	String resourceUrl = baseURL+resourceName;
    	
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    	log.debug("Posting payload length "+payload.length()+" to server :"+ resourceUrl);
    	
    	HttpEntity<String> request = new HttpEntity<String>(payload, httpHeaders );
    	ResponseEntity<String> response = null;
    	try {
    		 response = restTemplate.exchange(resourceUrl, HttpMethod.POST, request, String.class);
		} catch (HttpClientErrorException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("Server Error: "+e.getLocalizedMessage()+" ,URL is :"+resourceUrl);
		}catch (ResourceAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("Server Error: "+e.getLocalizedMessage()+" ,URL is :"+resourceUrl);
		}

     	//URI location = restTemplate.postForLocation(resourceUrl, request);
    	if (response !=null){
    		status =true;
    		log.debug("Response Code:"+response.getStatusCode().toString());
    		log.debug("Response Payload:"+response.getBody().toString());
     	}
       return status;
    }
}
