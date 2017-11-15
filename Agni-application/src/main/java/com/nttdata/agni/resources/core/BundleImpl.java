/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.ArrayList;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.Bundle.BundleType;
import org.hl7.fhir.dstu3.model.Bundle.HTTPVerb;

import ca.uhn.fhir.context.FhirContext;


/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */
public class BundleImpl extends AbstractResource {
	Bundle bundle;
	BundleImpl(){
		bundle = new Bundle();
		bundle.setType(BundleType.TRANSACTION);
	}
	void addResourceToBundle(AbstractResource res){
				
		bundle.addEntry()
		   .setFullUrl(res.getResource().getId()).setResource(res.getResource())
		   .getRequest().setUrl(res.getResourceName()).setMethod(HTTPVerb.POST);
		
		//bundle.addEntry()
		//   .setResource(observation).getRequest().setUrl("Observation").setMethod(HTTPVerb.POST);
	}
	@Override	
	public void addResourcesFromList(ArrayList<AbstractResource> resourceList) {
		if (resourceList.size() > 0) {        	
	    	for (AbstractResource res : resourceList) {	  
	    		System.out.println("Harendra"+res);
	    		bundle.addEntry()
	 		   .setResource(res.getResource())
	 		   .getRequest().setUrl(res.getResourceName()).setMethod(HTTPVerb.POST);
		 		   //.setFullUrl(res.getResource().getId())		
	        }
	    	//System.out.println("ZZZ"+this.toJson());
    	}
		
		
	}
	
	@Override
	public String toJson() {
		FhirContext ctx = FhirContext.forDstu3();
		String encoded = ctx.newJsonParser().setPrettyPrint(true)
        		//.newXmlParser().setPrettyPrint(true)
                .encodeResourceToString(bundle);
		return encoded;
	}
}
