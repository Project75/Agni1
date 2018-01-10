package com.nttdata.agni.test;


import java.net.URI;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;



public class FHIRServerTest extends GenericResourceTest{
  
	
	

	@Test
    public void shouldPostHL7ToServer() throws Exception {
        String input = transform("patient");
        System.out.println("Post to Server\n"+input);
  
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
        	HttpEntity<String> request = new HttpEntity<>(input,header);
        	String fooResourceUrl  = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3/Patient";
			URI location = restTemplate.postForLocation(fooResourceUrl, request);
        	System.out.println(location.toString());
    }	


}
