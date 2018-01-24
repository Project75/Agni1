/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.ArrayList;

import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.agni.resources.utils.TransformMap;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Copyright NTT Data
 * @author Harendra Pandey
 */
@Data
public class AbstractResource {
	
	Resource resource;
	String id;
	String resourceName;
	
    protected final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	
	

	public void setResourceData(TransformMap map) {
	}
	
	public void setResourceDataFromMap(TransformMap data) {
		
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

	public String[] getVaribleArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	
	/**
	 * @param resource
	 */
	
	
	
	
}
