package com.nttdata.agni.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nttdata.agni.Application;
import com.nttdata.agni.files.CustomFormatController;
import com.nttdata.agni.files.CustomTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class FlatFileControllerTest {
	
	@InjectMocks
    CustomFormatController customFormatController;
	//@Autowired
    //private CustomTransformer customTransformer;
	
	private MockMvc mvc;
    
    //private String mapName;
    @Autowired
    WebApplicationContext context;

	@Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        //mapName = "test1";
    }
	
    @Test
    public void shouldPostHL7() throws Exception {

    	String input = 
          		 "101,DOE,JOHN,Male,1947-12-28,PHOENIX,AZ,Married\r"+
          		 "102,SMITH,JOHN,Male,1987-12-28,PHOENIX,AZ,Married\r"
         		  ;
        MvcResult result=null;String resultString =null;
        
        	//CREATE2
        result = mvc.perform(post("/fhirtranslator/v1/custom2fhir/demo/patient")
                    .content(input)
                    .contentType(MediaType.TEXT_PLAIN_VALUE)
                    .accept(MediaType.ALL))
       //             .andExpect(status().is2xxSuccessful())          
                    .andReturn();
         resultString = result.getResponse().getContentAsString();
            System.out.println("************Final Output");
            	System.out.println(resultString);
            
      
    }
    

}
