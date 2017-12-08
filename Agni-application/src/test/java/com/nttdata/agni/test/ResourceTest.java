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
      
    @Test
    public void testMessageHeader() throws Exception {
    	String out=transform("messageheader");
    	System.out.println("messageheader:"+out);
        assertEquals(out, "HIS EKG 199904140038 A01");
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
        assertEquals(out, "FINAL 3 null 2006-02-21 06:18:09 2006-02-21 06:18:09 15 null 8 null null 17 17 7 7 10 null 7");

    }
    
    @Test 
    public void testPractitioner() throws Exception {
    	String out=transform("Practitioner");
    	System.out.println("Practitioner:"+out);
    }	
    
    @Test 
    public void testPractitionerRole() throws Exception {
    	String out=transform("PractitionerRole");
    	System.out.println("PractitionerRole:"+out);
    }
    
    @Test 
    public void testProcedure() throws Exception {
    	String out=transform("Procedure");
    	System.out.println("Procedure:"+out);
    }
    
    @Test 
    public void testProcedureRequest() throws Exception {
    	String out=transform("ProcedureRequest");
    	System.out.println("ProcedureRequest:"+out);
    }
    
    @Test 
    public void testReferralRequest() throws Exception {
    	String out=transform("ReferralRequest");
    	System.out.println("ReferralRequest:"+out);
    }
    
}
