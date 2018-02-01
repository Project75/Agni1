/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.ArrayList;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Resource;

import com.nttdata.agni.resources.utils.ApiClient;
import com.nttdata.agni.resources.utils.TransformUtils;

import org.hl7.fhir.dstu3.model.Bundle.BundleType;
import org.hl7.fhir.dstu3.model.Bundle.HTTPVerb;

import ca.uhn.fhir.context.FhirContext;
import lombok.Getter;
import lombok.Setter;


/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */

public class BundleImpl extends AbstractResource {
	Bundle bundle;
	
	public BundleImpl(){
		bundle = new Bundle();
		//bundle.setType(BundleType.MESSAGE);
				//.TRANSACTION);
	}
	
	public void setBundleType(String bundleTypeString){
		if (bundleTypeString.equalsIgnoreCase("MESSAGE"))
			bundle.setType(BundleType.MESSAGE);
		if (bundleTypeString.equalsIgnoreCase("TRANSACTION"))
			bundle.setType(BundleType.TRANSACTION);
	}
	void addResourceToBundle(AbstractResource res){
				
		bundle.addEntry()
		   .setFullUrl(res.getResource().getId())
			.setResource(res.getResource())
		   .getRequest().setUrl(res.getResourceName());
		   //.setMethod(HTTPVerb.POST);
		
		//bundle.addEntry()
		//   .setResource(observation).getRequest().setUrl("Observation").setMethod(HTTPVerb.POST);
	}
	@Override	
	public void addResourcesFromList(ArrayList<AbstractResource> resourceList) {
		if (resourceList.size() > 0) {        	
	    	for (AbstractResource res : resourceList) {	  
	    		log.info("Add resource "+ res.getResourceName()+"to bundle" );
	    		bundle.addEntry()
	    			.setFullUrl(res.getResource().getId())
	 		   		.setResource(res.getResource())
	 		   		.getRequest().setUrl(res.getResourceName());//.setMethod(HTTPVerb.POST);
		 		  
	    		postToServer(res);
	        }
	    	log.info("Resource bundle created");//+this.toJson());
    	}
		
		
	}
	
	public void postToServer(ArrayList<AbstractResource> resourceList) {
		if (resourceList.size() > 0) {        	
	    	for (AbstractResource res : resourceList) {	  
	    		
	    		postToServer(res);
	        }
	    	log.info("Post to Server Completed");
    	}
	}
	
	public void postToServer(AbstractResource resource) {
			
    		String payload=  TransformUtils.resourceToJson(resource.getResource());
    		Boolean status = ApiClient.postResource(payload, resource.getResourceName());
  	    	
	}

	
	public String toJson() {
		FhirContext ctx = FhirContext.forDstu3();
		String encoded = ctx.newJsonParser().setPrettyPrint(true)
        		//.newXmlParser().setPrettyPrint(true)
                .encodeResourceToString(bundle);
		return encoded;
	}
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.bundle;
	}
}
