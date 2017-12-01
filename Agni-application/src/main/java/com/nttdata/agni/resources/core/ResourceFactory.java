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
		        return new EncounterImpl();
	      case "bundle":
		        return new BundleImpl();
	      case "messageheader":
		        return new MessageHeaderImpl();
	      case "appointment" :
	    	  	return new AppointmentImpl();
	      case "immunization" :
	    	  return new ImmunizationImpl();
	      default:
	          // Error
	          return null;
	    }
		
		
	}
}
