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
import ca.uhn.fhir.model.primitive.IdDt;


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
		bundle.setType(BundleType.MESSAGE);
		bundle.setId(IdDt.newRandomUuid());
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
	    		
	    		bundle.addEntry()
	    		.setFullUrl(res.getResource().getId())
	 		   .setResource(res.getResource())
	 		   .getRequest().setUrl(res.getResourceName()).setMethod(HTTPVerb.POST);
		 		
	        }
	    	//System.out.println("bundle:"+this.toJson());
    	}
		
		
	}
	
	
	public String toJson() {
		FhirContext ctx = FhirContext.forDstu3();
		String encoded = ctx.newJsonParser().setPrettyPrint(true)
        		//.newXmlParser().setPrettyPrint(true)
                .encodeResourceToString(bundle);
		return encoded;
	}
}
