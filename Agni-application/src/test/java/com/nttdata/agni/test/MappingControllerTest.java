package com.nttdata.agni.test;

/**
 * Uses JsonPath: http://goo.gl/nwXpb, Hamcrest and MockMVC
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.agni.Application;
import com.nttdata.agni.api.rest.HL72FHIRController;
import com.nttdata.agni.api.rest.MappingController;
import com.nttdata.agni.api.rest.UserController;
import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.domain.TransformRequest;
import com.nttdata.agni.domain.User;

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
public class MappingControllerTest {

    private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/fhirtranslator/v1/mappings";

    @InjectMocks
    HL72FHIRController hcontroller;
    
    @InjectMocks
    MappingController mcontroller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;
    
    private String mapName;
    
    List<MappingList> mapping;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapName = "test1";
    }
/*
    @Test
    public void shouldHaveEmptyDB() throws Exception {
        mvc.perform(get("/fhirtranslator/v1/mappings")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
*/
   // @Test
    public void shouldCreateRetrieveDelete() throws Exception {
    	 mapping = GenericResourceTest.mockMappings();
        byte[] r1Json = toJson(mapping);

        //CREATE
        MvcResult result = mvc.perform(post("/fhirtranslator/v1/mappings/savelist/test1")
                .content(r1Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                //.andExpect(redirectedUrlPattern(RESOURCE_LOCATION_PATTERN))
                .andReturn();
       // long id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());

        //RETRIEVE
        mvc.perform(get("/fhirtranslator/v1/mappings/" + mapName)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(mapping.size())))
        .andExpect(jsonPath("$.[*].mapname", hasItems("test1")))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.mapname", is(mapName)));
                
        //DELETE 
 /*      mvc.perform(delete("/fhirtranslator/v1/mappings/" + id))
                .andExpect(status().isNoContent());

        //RETRIEVE should fail
        mvc.perform(get("/fhirtranslator/v1/mappings/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
*/
        //todo: you can test the 404 error body too.


    }

    
    @Test
    public void shouldPostHL7() throws Exception {
        //User r1 = mockUser("shouldCreateRetrieveDelete");
        TransformRequest transformRequest =  new TransformRequest("test1",GenericResourceTest.getTestPayload());
        byte[] r1Json = toJson(transformRequest);
        MvcResult result=null;String resultString =null;
        
        	//CREATE2
        result = mvc.perform(post("/fhirtranslator/v1/hl72fhir/test1")
                    .content(transformRequest.getValue())
                    .contentType(MediaType.TEXT_PLAIN_VALUE)
                    .accept(MediaType.ALL))
       //             .andExpect(status().is2xxSuccessful())          
                    .andReturn();
         resultString = result.getResponse().getContentAsString();
            System.out.println("************Final Output");
            	System.out.println(resultString);
            /*	
             result = mvc.perform(post("/fhirtranslator/v1/hl72fhir/")
                .content(GenericResourceTest.getTestPayload())
                //.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                //.andExpect(status().is2xxSuccessful())          
                .andReturn();
         resultString = result.getResponse().getContentAsString();
        	System.out.println(resultString);
        */
      
    }
    
   
    /*
    ******************************
     */

    private long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }


    private List<MappingList> mockMappingsOld(String mapname) {
    	List<MappingList> mapping =  new ArrayList<MappingList>();
    	
    	mapping.add(new MappingList("patient.identifier","PID-3-1"));
    	mapping.add(new MappingList("patient.name.family","PID-5-1"));
    	mapping.add(new MappingList("patient.name.given","PID-5-2"));
    	mapping.add(new MappingList("patient.gender","PID-8-1"));
    	mapping.add(new MappingList("patient.birthDate","PID-7-1"));
    	mapping.add(new MappingList("patient.address.line","PID-11-1"));
    	mapping.add(new MappingList("patient.address.city","PID-11-3"));
    	mapping.add(new MappingList("patient.address.state","PID-11-4"));
    	mapping.add(new MappingList("patient.address.postalCode","PID-11-5"));
    	mapping.add(new MappingList("patient.address.country","PID-11-6"));
    	mapping.add(new MappingList("patient.telecom.value","PID-13-1"));
    	mapping.add(new MappingList("patient.maritalStatus","PID-16-1"));
    	mapping.add(new MappingList("patient.deceased","PID-30"));
    	mapping.add(new MappingList("patient.multipleBirth","PID-24"));
    	mapping.add(new MappingList("patient.photo","OBX-5"));
    	mapping.add(new MappingList("encounter.identifier","PV1-19-1"));
    	mapping.add(new MappingList("encounter.status","PV1-2-1"));
    	mapping.add(new MappingList("encounter.location.display","PV1-3-1"));
    	mapping.add(new MappingList("encounter.participant.individual.display","PV1-7-2"));
    	mapping.add(new MappingList("encounter.participant.individual.reference","PV1-7-1"));
    	mapping.add(new MappingList("messageheader.event.code","MSH-9-2"));
    	mapping.add(new MappingList("messageheader.destination.name","MSH-5-1"));
    	mapping.add(new MappingList("messageheader.source.name","MSH-3-1"));
    	mapping.add(new MappingList("messageheader.timestamp","MSH-7-1"));
    	mapping.add(new MappingList("observation.bodySite","OBX-20-1"));
    	mapping.add(new MappingList("observation.method","OBX-17-1"));
    	mapping.add(new MappingList("observation.device","OBX-17-1"));
    	mapping.add(new MappingList("observation.referenceRange.low","OBX-7"));
    	mapping.add(new MappingList("observation.referenceRange.high","OBX-7"));
    	mapping.add(new MappingList("observation.referenceRange.type","OBX-10"));
    	mapping.add(new MappingList("observation.referenceRange.appliesTo","OBX-10"));
    	mapping.add(new MappingList("observation.referenceRange.text","OBX-7"));
    	mapping.add(new MappingList("observation.component.code","OBX-3-1"));
    	mapping.add(new MappingList("observation.component.value","OBX-2"));
    	mapping.add(new MappingList("observation.component.interpretation","OBX-8"));
    	mapping.add(new MappingList("observation.component.referenceRange","OBX-7"));
    	mapping.add(new MappingList("observation.effective","OBX-14"));
    	mapping.add(new MappingList("observation.status","OBX-11"));
    	mapping.add(new MappingList("observation.code","OBX-13"));
    	mapping.add(new MappingList("observation.performer","OBX-15"));
    	mapping.add(new MappingList("observation.identifier","OBX-21"));
    	mapping.add(new MappingList("observation.interpretation","OBX-8"));
    	mapping.add(new MappingList("observation.code","OBX-3"));
    		return mapping;
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
