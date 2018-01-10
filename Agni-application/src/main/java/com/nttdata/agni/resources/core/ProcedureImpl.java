/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.nttdata.agni.resources.utils.IdentifierUtils;
import com.nttdata.agni.resources.utils.TransformMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Procedure;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.exceptions.FHIRException;
//import org.hl7.fhir.dstu3.model.DateTime;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * Module Agni-Application
 * @author Neha
 *
 */


@ToString
@Getter 
@Setter 

public class ProcedureImpl extends AbstractResource{
	/**
	 * Field for storing the FHIR Resource. Need to import from org.hl7.fhir.dstu3.model.*
	 * e.g Patient patient; or Patient resource;
	 */
	Procedure procedure;
	/**
	 * Create Local fields to store data from HL7. 
	 * e.g. familyName, givenName
	 */
	private String identifier;
	private String code, subject, context, performed, performerRole, performerActor, bodySite;
	
	//String resourceName = "procedure";
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public ProcedureImpl() {
		
		// TODO Auto-generated constructor stub
		this.procedure =  new Procedure();
		//resource.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		
		setValuesFromMap(data);
		setResourceData(data);

	}
	
	public void setValuesFromMap(TransformMap map) {
		this.identifier = map.get("procedure.identifier");
		this.code = map.get("procedure.code");
		//this.subject = map.get("procedure.subject");
		//this.context = map.get("procedure.context");
		//this.performed = map.get("procedure.performed");
		this.performerRole = map.get("procedure.performer.role");
		//this.performerActor = map.get("procedure.performer.actor");
		this.bodySite = map.get("procedure.bodySite");
	}
	
	@Override
	public void setResourceData(TransformMap map) {
		
		procedure.setIdentifier(IdentifierUtils.getIdentifierList(map, resourceName));
		procedure.setCode(new CodeableConcept().setText(code));
			
		/*try {
			procedure.getPerformed().addChild(performed);
		} catch (FHIRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		procedure.addPerformer().setRole(new CodeableConcept().setText(performerRole));
		procedure.addBodySite(new CodeableConcept().setText(bodySite));
}

	/*
	 * Return the FHIR resource object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.procedure;
	}
	
}
