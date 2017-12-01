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
 /*     
    @Test
    public void testMessageHeader() throws Exception {
    	String out=transform("messageheader");
    	System.out.println("messageheader:"+out);
        assertEquals(true, out.contains("HIS"));
        //assertNotNull(messageHeaderImpl.getResource());
    }
    @Test
    public void testPatient() throws Exception {
    	String out=transform("patient");
    	System.out.println("patient:"+out);
        assertEquals(out, "JOHN DOE 199 Female null street 53 PHOENIX AZ 85013 US (111)222-3333 W false false null null null null null null null null");
        //assertNotNull(messageHeaderImpl.getResource());
    }
    @Test
    public void testObservation() throws Exception {
    	String out=transform("Observation");
    	System.out.println("Observation:"+out);
        assertEquals(out, "null FINAL 3 null 2006-02-21 06:18:09 2006-02-21 06:18:09 15 null 8 null null 17 17 7 7 10 null 7");

    }
*/
    @Test
    public void testAppointment() throws Exception {
    	String out=transform("Appointment");
    	System.out.println("Appointment:"+out);
        assertEquals(out, "01928374 BOOKED 3-comment 199 201605150800 20160515134500");
    }
    
    @Test
    public void testImmunization() throws Exception {
        	String out=transform("Immunization");
        	System.out.println("Immunization:"+out);
            assertEquals(out, "null 48 199 0161782703 ");
    
    }
        
    @Test
    public void testEncounter() throws Exception {
        	String out=transform("Encounter");
        	System.out.println("Encounter:"+out);
            assertEquals(out, "0161782703 O U null ");
    
    }	
    
}
