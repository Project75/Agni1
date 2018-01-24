package com.nttdata.agni.test;


import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import com.nttdata.agni.resources.utils.ApiClient;



public class FHIRServerTest extends GenericResourceTest{
	//@Test
    public void testGetPatientByIdentifier() {
        //String input = transform("patient");
        System.out.println("Get patient 12345 from  Server\n");
       

		Boolean status = false;
		try {
			status = ApiClient.getPatientByIdentifier("Patient", "12345");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(status);
    }	
	 
	//@Test
    public void testGetPatientbyServerId()  {
        //String input = transform("patient");
        System.out.println("Get patient from  Server\n");
        
        String resourceName = "Patient";
		String resourceId = "4952";
		Boolean status = false;
		try {
			status = ApiClient.getResourceByServerId(resourceName, resourceId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(status);
    }	
	

	@Test
    public void testPostPatient() {
        String input = getPatientPayload(); //transform("patient");
        System.out.println("Post to Server\n");
        
        Boolean status =  ApiClient.postResource(input,"Patient" );
		        
		assertTrue(status);
        /*
			URI location = restTemplate.postForLocation(fooResourceUrl, request);
    	*/
    }	
	
	String getPatientPayload(){
		return     	 "{\r\n" + 
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
	}


}
