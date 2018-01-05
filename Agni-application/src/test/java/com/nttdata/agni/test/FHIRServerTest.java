package com.nttdata.agni.test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.domain.TransformRequest;
import com.nttdata.agni.resources.core.*;
import com.nttdata.agni.transfomer.*;

import org.junit.Before;
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
