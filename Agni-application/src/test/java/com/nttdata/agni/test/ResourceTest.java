package com.nttdata.agni.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;



public class ResourceTest extends GenericResourceTest{
    
	
	@Test
    public void testPatient() throws Exception {
    	String out=transform("patient");
    	System.out.println("patient:"+out);
     }
	@Test
	public void testAllergyIntolerance() throws Exception {String out=transform("AllergyIntolerance");}
	@Test
	public void testAppointmentResponse() throws Exception {String out=transform("AppointmentResponse");}
	@Test
	public void testCarePlan() throws Exception {String out=transform("CarePlan");}
	@Test
	public void testCareTeam() throws Exception {String out=transform("CareTeam");}
	@Test
	public void testChargeItem() throws Exception {String out=transform("ChargeItem");}
	@Test
	public void testCondition() throws Exception {String out=transform("Condition");}
	@Test
	public void testConsent() throws Exception {String out=transform("Consent");}
	@Test
	public void testCoverage() throws Exception {String out=transform("Coverage");}
	@Test
	public void testGoal() throws Exception {String out=transform("Goal");}
	@Test
	public void testMedia() throws Exception {String out=transform("Media");}
	@Test
	public void testPerson() throws Exception {String out=transform("Person");}
	@Test
	public void testRelatedPerson() throws Exception {String out=transform("RelatedPerson");}
	@Test
	public void testSupplyRequest() throws Exception {String out=transform("SupplyRequest");}
	@Test
	public void testVisionPrescription() throws Exception {String out=transform("VisionPrescription");}
	@Test
	public void testPaymentNotice() throws Exception {String out=transform("PaymentNotice");}

	
	/*
	 
    @Test
    public void testMessageHeader() throws Exception {
    	String out=transform("messageheader");
    	System.out.println("messageheader:"+out);
        //assertEquals(true, out.contains("HIS"));
        //assertNotNull(messageHeaderImpl.getResource());
    }
    
    @Test
    public void testObservation() throws Exception {
    	String out=transform("Observation");
    	System.out.println("Observation:"+out);
       // assertEquals(out, "null FINAL 3 null 2006-02-21 06:18:09 2006-02-21 06:18:09 15 null 8 null null 17 17 7 7 10 null 7");

    }
	@Test
    public void DocumentManifest() throws Exception {
    	String out=transform("DocumentManifest");
    	System.out.println("DocumentManifest:"+out);
    }
	@Test
	public void DocumentReference() throws Exception {
    	String out=transform("DocumentReference");
    	System.out.println("DocumentReference:"+out);
    }
	@Test
	public void ImmunizationRecommendation() throws Exception {
    	String out=transform("ImmunizationRecommendation");
    	System.out.println(out);
    }
	@Test
	public void MedicationAdministration() throws Exception {
    	String out=transform("MedicationAdministration");
    	System.out.println(out);
    }
	@Test
	public void Location() throws Exception {
    	String out=transform("Location");
    	System.out.println(out);        
    }
	
	@Test
	public void OperationOutcome() throws Exception {
    	String out=transform("OperationOutcome");
    	System.out.println(out);        
    }
    //Sameer
    @Test
    public void testAppointment() throws Exception {
    	String out=transform("Appointment");
    	System.out.println("Appointment:"+out);
        //assertEquals(out, "01928374 BOOKED 3-comment 199 201605150800 20160515134500");
    }
    
    @Test
    public void testImmunization() throws Exception {
        	String out=transform("Immunization");
        	System.out.println("Immunization:"+out);
            //assertEquals(out, "null 48 199 0161782703 ");
    
    }
    
    @Test
    public void testOrganization() throws Exception {
    	String out=transform("Organization");
    	System.out.println("Organization:"+out);
        //assertEquals(out, "null (333)444-5555 orcstreet 53 LA CAL 12345 US street 53 PHOENIX null AZ 85013 JOHN DOE null null (111)222-3333");
    }
        @Test
        public void testDataElement() throws Exception {
        	String out=transform("DataElement");
        	System.out.println("DataElement:"+out);
            //assertEquals(out, "2 null 16 11 null 17 (111)222-3333");

        }
          
        @Test
        public void testDiagnosticReport() throws Exception {
        	String out=transform("DiagnosticReport");
        	System.out.println("DiagnosticReport:"+out);
        }
		 @Test
	     public void testDetectedIssue() throws Exception {
	     	String out=transform("DetectedIssue");
	     	System.out.println("DetectedIssue:"+out);
	      
    	 
		 }
		 @Test
		    public void testBodySite() throws Exception {
		    	String out=transform("BodySite");
		    	System.out.println("BodySite:"+out);
		        //assertEquals(out, "20 15");

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
		*/    
}
