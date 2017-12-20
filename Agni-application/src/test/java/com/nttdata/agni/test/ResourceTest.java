package com.nttdata.agni.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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



public class ResourceTest extends GenericResourceTest{
      
    //@Test
    public void testMessageHeader() throws Exception {
    	String payload = "MSH|^~\\&|HIS|RIH|EKG1|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID||001|199||JOHN1^DOE||19751027|Female|||street 53^^PHOENIX^AZ^85013^US||(111)222-3333||N|W|||001|||||false||||||false|||||PID.35\r"
                + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|Father||||||ORGANIZATION||Male\r"
                +"PV1||O|5501^0113^02|U|00060292||00276^DELBARE^POL^^DR.|00276^DELBARE^POL^^DR.||1901|||N|01|||||0161782703\r"
                +"PD1|1|2|3|4|5|6|7|8|9|10\r"
                +"OBX|1|TX|3|4|5|6|7|8|9|10|FINAL||13|20060221061809|15|16|17|18|19|20|21|22|23|24|25|26\r"
                +"OBR|1|2156286|A140875|MRSHLR-C^MR Shoulder right wo/contrast|5||||9|10|11|12|13||15|16||18|19|20|21|20060221061809|23|24|25|26\r"
                +"NTE|1|2|3|4\r"; 
    	String out=transform("messageheader", payload);
    	System.out.println("messageheader:"+out);
        assertEquals(true, out.contains("HIS"));
        //assertNotNull(messageHeaderImpl.getResource());
    }
    
    //@Test
    public void testEncounter() throws Exception {
    	String out=transform("encounter");
    	System.out.println("encounter:"+out);
    	//assertEquals(true, out.contains("EpisodeofCare"));
        //assertNotNull(messageHeaderImpl.getResource());
    }
    
    @Test
    public void testPatient() throws Exception {
    	String out=transform("patient");
    	System.out.println("patient:"+out);
    	assertEquals(true, out.contains("JOHN"));
        //assertNotNull(messageHeaderImpl.getResource());
    }
    //@Test
    public void testObservation() throws Exception {
    	String out=transform("Observation");
    	System.out.println("Observation:"+out);
    	assertEquals(true, out.contains("FINAL"));

    }

   
    public void testBundle() throws Exception {
    	String out=transform();
    	//System.out.println("Bundle:"+out);
    	//assertEquals(true, out.contains("JOHN"));
        assertNotNull(out);
    }
    	
    
}
