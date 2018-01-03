/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.ArrayList;
import com.nttdata.agni.resources.utils.TransformMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StringType;
import ca.uhn.fhir.model.primitive.IdDt;
import org.hl7.fhir.dstu3.model.OperationOutcome;
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
public class OperationOutcomeImpl extends AbstractResource{
	/**
	 * Field for storing the FHIR Resource. Need to import from org.hl7.fhir.dstu3.model.*
	 * e.g Patient patient; or Patient resource;
	 */
	OperationOutcome operationOutcome;
	/**
	 * Create Local fields to store data from HL7. 
	 * e.g. familyName, givenName
	 */
	private String familyName;
	private String givenName;
	
	String resourceName="OperationOutcome";
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public OperationOutcomeImpl() {
		
		// TODO Auto-generated constructor stub
		this.operationOutcome =  new OperationOutcome();
		operationOutcome.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(TransformMap map) {
		this.familyName = map.get("patient.family.name");
		this.givenName = map.get("patient.given.name");

	}
	
	@Override
	public void setResourceData() {
		
		

}

	/*
	 * Return the FHIR resource object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.operationOutcome;
	}
	

	
}
