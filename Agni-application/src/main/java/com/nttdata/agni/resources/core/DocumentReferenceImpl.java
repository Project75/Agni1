/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hl7.fhir.dstu3.model.DocumentReference;
import org.hl7.fhir.dstu3.model.Resource;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * Agni-Applicationo-
 * @author Harendra Pandey
 *
 */
@ToString
@Getter 
@Setter 
public class DocumentReferenceImpl extends AbstractResource{

	DocumentReference resource;
	private String test1;
	private String test2;
	
	String resourceName="DocumentManifest";
	
	public DocumentReferenceImpl() {
		
		// TODO Auto-generated constructor stub
		this.resource =  new DocumentReference();
		resource.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.test1 = map.get("messageheader.source.name");
		this.test2 = map.get("messageheader.destination.name");

	}
	
	@Override
	public void setResourceData() {
		
}

	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.resource;
	}
	

	
}
