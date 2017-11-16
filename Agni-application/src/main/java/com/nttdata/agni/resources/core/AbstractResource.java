/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.ArrayList;
import java.util.HashMap;

import org.hl7.fhir.dstu3.model.Resource;

import ca.uhn.fhir.context.FhirContext;

/**
 * Copyright NTT Data
 * @author Harendra Pandey
 */
public class AbstractResource {
	
	Resource resource;
	String id;
	String resourceName;
	
	
	
	
	
	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setResourceData() {
	}
	
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
	}
	public void getResourcedata() {
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @param resourceList
	 */
	public void addResourcesFromList(ArrayList<AbstractResource> resourceList) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return java.util.UUID.randomUUID().toString();
	}

	/**
	 * @param id the id to set
	 */
	public void setId() {
		this.id = java.util.UUID.randomUUID().toString();
	}

	/**
	 * @return
	 */
	
	/**
	 * @param resource
	 */
	
	
	
	
}
