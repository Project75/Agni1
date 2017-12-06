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
	      case "medicationstatement":
		        return new MedicationStatementImpl();
	      case "organization":
		        return new OrganizationImpl();
	      case "dataelement":
		        return new DataElementImpl();
	      default:
	          // Error
	          return null;
	    }
		
		
	}
}
