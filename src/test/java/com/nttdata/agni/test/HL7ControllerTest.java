package com.nttdata.agni.test;

/**
 * Uses JsonPath: http://goo.gl/nwXpb, Hamcrest and MockMVC
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.agni.Application;
import com.nttdata.agni.api.rest.HL72FHIRController;
import com.nttdata.agni.api.rest.MappingController;
import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.domain.TransformRequest;
import com.nttdata.agni.service.TransformUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class HL7ControllerTest {

    private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/fhirtranslator/v1/hl72fhir";
/*
    @InjectMocks
    HL72FHIRController hcontroller;
    
    @InjectMocks
    MappingController mcontroller;
*/
    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    
 /*   
    @Test
    public void shouldCreateMappings() throws Exception {
    	List<MappingList> mapping =  new ArrayList<MappingList>();
    	
    	mapping.add(new MappingList("patient.identifier","PID.3.1"));
    	mapping.add(new MappingList("patient.name.family","PID.5.1"));
    	mapping.add(new MappingList("patient.name.given","PID.5.2"));
    	mapping.add(new MappingList("patient.gender","PID.8.1"));
    	mapping.add(new MappingList("patient.birthDate","PID.7.1"));
    	mapping.add(new MappingList("patient.address.line","PID.11.1"));
    	mapping.add(new MappingList("patient.address.city","PID.11.3"));
    	mapping.add(new MappingList("patient.address.state","PID.11.4"));
    	mapping.add(new MappingList("patient.address.postalCode","PID.11.5"));
    	mapping.add(new MappingList("patient.address.country","PID.11.6"));
    	mapping.add(new MappingList("patient.telecom.value","PID.13.1"));
    	mapping.add(new MappingList("patient.maritalStatus","PID.16.1"));
    	mapping.add(new MappingList("patient.deceased","PID.30"));
    	mapping.add(new MappingList("patient.multipleBirth","PID.24"));
    	mapping.add(new MappingList("patient.photo","OBX.5"));
    	mapping.add(new MappingList("encounter.identifier","PV1.19.1"));
    	mapping.add(new MappingList("encounter.status","PV1.2.1"));
    	mapping.add(new MappingList("encounter.location.display","PV1.3.1"));
    	mapping.add(new MappingList("encounter.participant.individual.display","PV1.7.2"));
    	mapping.add(new MappingList("encounter.participant.individual.reference","PV1.7.1"));
    	mapping.add(new MappingList("messageheader.event.code","MSH.9.2"));
    	mapping.add(new MappingList("messageheader.destination.name","MSH.5.1"));
    	mapping.add(new MappingList("messageheader.source.name","MSH.3.1"));
    	mapping.add(new MappingList("messageheader.timestamp","MSH.7.1"));
        byte[] r1Json = toJson(mapping);

        //CREATE
        MvcResult result = mvc.perform(post("/fhirtranslator/v1/mapping?mapname=default")
                .content(r1Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern(RESOURCE_LOCATION_PATTERN))
                .andReturn();
        long id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());
        
      
    }
    
*//*
    @Test
    public void shouldPostHL7() throws Exception {
        //User r1 = mockUser("shouldCreateRetrieveDelete");
        TransformRequest transformRequest =  new TransformRequest("default");
        byte[] r1Json = toJson(transformRequest);

        //CREATE
        MvcResult result = mvc.perform(post("/fhirtranslator/v1/hl72fhir")
                .content(r1Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())          
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        	System.out.println(resultString);
        
      
    }
    
    public static void main(String[] args) throws Exception {
    	
    	
    	String msg  = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID||001|199||JOHN^DOE||19751027|Female|||street 53^^PHOENIX^AZ^85013^US||(111)222-3333||N|W|||001|||||false||||||false|||||PID.35\r"
                + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|Father||||||ORGANIZATION||Male\r"
                +"PV1||O|5501^0113^02|U|00060292||00276^DELBARE^POL^^DR.|00276^DELBARE^POL^^DR.||1901|||N|01|||||0161782703\r"
                +"PD1|1|2|3|4|5|6|7|8|9|10\r"
                +"OBX|1|TX|3|4|5|6|7|8|9|10|FINAL||13|20170828043002|15|16|17|18|19|20|21|22|23|24|25|26\r"
                +"OBR|1|2|3|4|5||||9|10|11|12|13||15|16||18|19|20|21|20170828042512|23|24|25|26\r"
                +"NTE|1|2|3|4\r";
    	TransformRequest transformRequest =  new TransformRequest("default",msg);
    	TransformUtils instance =  new TransformUtils();
        System.out.println(instance.convertHL72FHIR(transformRequest));
        

    }
    */
    
   



    private long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }




    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

    // match redirect header URL (aka Location header)
    private static ResultMatcher redirectedUrlPattern(final String expectedUrlPattern) {
        return new ResultMatcher() {
            public void match(MvcResult result) {
                Pattern pattern = Pattern.compile("\\A" + expectedUrlPattern + "\\z");
                assertTrue(pattern.matcher(result.getResponse().getRedirectedUrl()).find());
            }
        };
    }

}

