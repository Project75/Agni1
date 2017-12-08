/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.ProcedureRequest;
import org.hl7.fhir.dstu3.model.ProcedureRequest.ProcedureRequestPriority;
import org.hl7.fhir.dstu3.model.ProcedureRequest.ProcedureRequestRequesterComponent;
import org.hl7.fhir.dstu3.model.ProcedureRequest.ProcedureRequestStatus;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.exceptions.FHIRException;

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

public class ProcedureRequestImpl extends AbstractResource{
	/**
	 * Field for storing the FHIR Resource. Need to import from org.hl7.fhir.dstu3.model.*
	 * e.g Patient patient; or Patient resource;
	 */
	ProcedureRequest procedurerequest;
	/**
	 * Create Local fields to store data from HL7. 
	 * e.g. familyName, givenName
	 */
	private String identifier;
	private String basedOn, requisition, status, priority, authoredOn, requesterAgent, reasonCode, reasonReference;
	
	//String resourceName = "procedure";
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public ProcedureRequestImpl() {
		
		// TODO Auto-generated constructor stub
		this.procedurerequest =  new ProcedureRequest();
		//resource.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.identifier = map.get("procedurerequest.identifier");
		this.basedOn = map.get("procedurerequest.basedOn");
		this.requisition = map.get("procedurerequest.requisition");
		this.status = map.get("procedurerequest.status");
		this.priority = map.get("procedurerequest.priority");
		this.authoredOn = map.get("procedurerequest.authoredOn");
		//this.requesterAgent = map.get("procedurerequest.requester.agent");
		this.reasonCode = map.get("procedurerequest.reasonCode");
		//this.reasonReference = map.get("procedurerequest.reasonReference");
	}
	
	@Override
	public void setResourceData() {
		
		procedurerequest.addIdentifier().setSystem("http://ns.electronichealth.net.au/id/hi/ihi/1.0").setValue(identifier);
		procedurerequest.addBasedOn().setReference(basedOn);
		procedurerequest.setRequisition(new Identifier().setValue(requisition));
		procedurerequest.setStatus(ProcedureRequestStatus.valueOf(status.toUpperCase()));	
		procedurerequest.setPriority(ProcedureRequestPriority.valueOf(priority.toUpperCase()));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			procedurerequest.setAuthoredOn(formatter.parse(authoredOn));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		procedurerequest.addReasonCode(new CodeableConcept().setText(reasonCode));
		
}

	/*
	 * Return the FHIR resource object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.procedurerequest;
	}
	
}
