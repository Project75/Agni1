/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StringType;
import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * Module Agni-Application
 * @author 
 *
 */


@ToString
@Getter 
@Setter 
/**
 * Class name should be <Resource Name>Impl e.g. PatientImpl
 */
public class ResourceImplTemplate extends AbstractResource{
	/**
	 * Field for storing the FHIR Resource. Need to import from org.hl7.fhir.dstu3.model.*
	 * e.g Patient patient; or Patient resource;
	 */
	Patient patient;
	/**
	 * Create Local fields to store data from HL7. 
	 * e.g. familyName, givenName
	 */
	private String familyName;
	private String givenName;
	
	String resourceName="Patient";
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public ResourceImplTemplate() {
		
		// TODO Auto-generated constructor stub
		this.resource =  new Patient();
		resource.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.familyName = map.get("patient.family.name");
		this.givenName = map.get("patient.given.name");

	}
	
	@Override
	public void setResourceData() {
		

	//Two ways to set the name on patient 
	//1 Create objects for all the types needed
		List<HumanName> theNameList = new ArrayList<HumanName>();
		HumanName theName = new HumanName();
		List<StringType> theGivenName = new ArrayList<StringType>();			
		theGivenName.add(new StringType(givenName));
		theName.setFamily(familyName);
		theName.setGiven(theGivenName);
		//finally setting the name below
		patient.setName(theNameList);
		
	//2-Alternative - one line code Using fluent API (Recommended)
		patient.addName().setFamily(familyName).addGiven(givenName);
}

	/*
	 * Return the FHIR resource object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.patient;
	}
	

	
}
