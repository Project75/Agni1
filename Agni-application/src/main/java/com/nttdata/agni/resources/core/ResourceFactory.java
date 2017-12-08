/**
 * 
 */
package com.nttdata.agni.resources.core;

/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */
public class ResourceFactory {
	public static AbstractResource getResource(String type){
		//if(type.equalsIgnoreCase("patient")) return new PatientImpl();
		
		switch (type.toLowerCase()) {
	      case "patient":
	        return new PatientImpl();
	      case "observation":
		        return new ObservationImpl();
	      case "encounter":
		        return null;
	      case "bundle":
		        return new BundleImpl();
	      case "messageheader":
		        return new MessageHeaderImpl(); 
	      case "practitioner":
		        return new PractitionerImpl(); 
	      case "practitionerrole":
		        return new PractitionerRoleImpl();
	      case "procedure":
		        return new ProcedureImpl();
	      case "procedurerequest":
		        return new ProcedureRequestImpl();
	      case "referralrequest":
		        return new ReferralRequestImpl();
	      default:
	          // Error
	          return null;
	    }
		
		
	}
}
