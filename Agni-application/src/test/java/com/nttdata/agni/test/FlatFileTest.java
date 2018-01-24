package com.nttdata.agni.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.nttdata.agni.files.CustomTransformer;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class FlatFileTest {
	   private CustomTransformer customTransformer;
	    
	    @Before
	    public void setUp() {
	    		customTransformer = new CustomTransformer();
	    	  	
	    	  	Logger Logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ca.uhn.fhir");
	    	  			//("ca.uhn.fhir.context.ModelScanner");
	    	    Logger.setLevel(Level.WARN);
	    	     Logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("org.apache.commons.beanutils");
	  			//("ca.uhn.fhir.context.ModelScanner");
	    	     Logger.setLevel(Level.WARN);

	    }
	@Test
    public void testPatient() throws Exception {
    	String out=transform("patient");
    	System.out.println("FHIR OUTPUT \n:"+out);
     }

	private String transform(String mapname) {
		// TODO Auto-generated method stub
		
		String payload = TestUtils.generateFlatFileTestPayload(2);

		String fhir = customTransformer.transform(mapname, payload);
		
		return fhir;
	}

}
