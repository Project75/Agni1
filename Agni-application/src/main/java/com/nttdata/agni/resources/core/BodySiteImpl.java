/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Immunization;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Patient.ContactComponent;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.BodySite;

import ca.uhn.fhir.model.primitive.IdDt;


/**
 * Copyright NTT Data
 * @author Smita Srivastava
 */

@ToString
@Getter 
@Setter 

public class BodySiteImpl extends AbstractResource{
	/**
	 * 
	 */
	public BodySiteImpl() {
		super();
		this.bodysite = new BodySite();
		// TODO Auto-generated constructor stub
	}

	String identifier, 	active, code, qualifier, description, image, patient;

	BodySite bodysite;
	String resourceName="BodySite";
	
	/**
	 * @return the patient
	 */
	public BodySite getBodySite() {
		return bodysite;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setBodySite(BodySite bodysite) {
		this.bodysite = bodysite;
	}
	
	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.code = map.get("bodysite.code");
		this.qualifier = map.get("bodysite.qualifier");
		
		this.patient = map.get("bodysite.patient");
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResourceData()
	 */
	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		//super.setResourceData();
		
		bodysite.setCode(new CodeableConcept().setText(this.getCode()));
		bodysite.addQualifier(new CodeableConcept().setText(this.getQualifier()));
		
			}
	
	
	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#getResourcedata()
	 */
	@Override
	public void getResourcedata() {
		// TODO Auto-generated method stub
		super.getResourcedata();
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#getResource()
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.bodysite;
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResource(org.hl7.fhir.dstu3.model.Resource)
	 */
	@Override
	public void setResource(Resource resource) {
		// TODO Auto-generated method stub
		this.setBodySite((BodySite) resource);
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#getResourceName()
	 */
	@Override
	public String getResourceName() {
		// TODO Auto-generated method stub
		return this.resourceName;
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResourceName(java.lang.String)
	 */
	@Override
	public void setResourceName(String resourceName) {
		// TODO Auto-generated method stub
		this.resourceName=resourceName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code + " " +  qualifier;
	}
	
	
}
