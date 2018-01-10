package com.nttdata.agni.test;
/**
 * @author Harendra
 */


import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.agni.Application;
import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.domain.TransformRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class MappingControllerTest {

    private static final String BASE_URL = "/fhirtranslator/v1/";

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;
    
    private String mapName;
    
    List<MappingList> mapping;

    @Before
    public void initTests() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapName = "test1";
    }

    @Test
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
/*            	RestTemplate restTemplate = new RestTemplate();
            	HttpEntity<String> request = new HttpEntity<>(new String(resultString));
            	String fooResourceUrl  = "http://localhost:9001/dstu2/patient";
				URI location = restTemplate.postForLocation(fooResourceUrl, request);
            	assertThat(location, notNullValue());
            	System.out.println(location.toString());*/
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
