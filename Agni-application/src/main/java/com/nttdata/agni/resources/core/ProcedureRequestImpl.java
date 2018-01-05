/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.nttdata.agni.resources.utils.IdentifierUtils;
import com.nttdata.agni.resources.utils.TransformMap;
import java.util.List;
import java.util.Optional;

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
import ca.uhn.fhir.util.DateUtils;

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
	
	String resourceName = "procedurerequest";
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public ProcedureRequestImpl() {
		
		// TODO Auto-generated constructor stub
		this.procedurerequest =  new ProcedureRequest();
		//resource.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		
		setValuesFromMap(data);
		setResourceData(data);

	}
	
	public void setValuesFromMap(TransformMap map) {
		this.identifier = map.get("procedurerequest.identifier");
		this.basedOn = map.get("procedurerequest.basedOn");
		this.requisition = map.get("procedurerequest.requisition");
		this.status = Optional.ofNullable(map.get("procedurerequest.status")).orElse(ProcedureRequestStatus.ACTIVE.toString());
		this.priority = map.get("procedurerequest.priority");
		this.authoredOn = map.get("procedurerequest.authoredOn");
		//this.requesterAgent = map.get("procedurerequest.requester.agent");
		this.reasonCode = map.get("procedurerequest.reasonCode");
		//this.reasonReference = map.get("procedurerequest.reasonReference");
	}
	
	@Override
	public void setResourceData(TransformMap map) {
		
		//procedurerequest.addIdentifier().setSystem("http://ns.electronichealth.net.au/id/hi/ihi/1.0").setValue(identifier);
		procedurerequest.setIdentifier(IdentifierUtils.getIdentifierList(map, resourceName));
		procedurerequest.addBasedOn().setReference(basedOn);
		procedurerequest.setRequisition(new Identifier().setValue(requisition));
		if(status!=null)
		procedurerequest.setStatus(ProcedureRequestStatus.valueOf(status.toUpperCase()));	
		if(priority!=null)
		procedurerequest.setPriority(ProcedureRequestPriority.valueOf(priority.toUpperCase()));
		
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		

		if (authoredOn != null)
			procedurerequest.setAuthoredOn(DateUtils.parseDate(authoredOn,new String[]{"yyyyMMdd","yyyy-MM-dd"}));

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
