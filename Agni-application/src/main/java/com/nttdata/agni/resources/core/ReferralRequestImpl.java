/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nttdata.agni.resources.utils.FHIRConstants;
import com.nttdata.agni.resources.utils.IdentifierUtils;
import com.nttdata.agni.resources.utils.TransformMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.ReferralRequest;
import org.hl7.fhir.dstu3.model.ReferralRequest.ReferralPriority;
import org.hl7.fhir.dstu3.model.ReferralRequest.ReferralRequestStatus;
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

public class ReferralRequestImpl extends AbstractResource{
	/**
	 * Field for storing the FHIR Resource. Need to import from org.hl7.fhir.dstu3.model.*
	 * e.g Patient patient; or Patient resource;
	 */
	ReferralRequest referralrequest;
	String resourceName="referralrequest";
	/**
	 * Create Local fields to store data from HL7. 
	 * e.g. familyName, givenName
	 */
	private String identifier;
	private String status, type, priority, serviceRequested, subject, context, occurrence, authoredOn, specialty, recipient, reasonCode;
	
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public ReferralRequestImpl() {
		
		// TODO Auto-generated constructor stub
		this.referralrequest =  new ReferralRequest();
		this.referralrequest.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		setValuesFromMap(data);
		setResourceData(data);
	}
	
	public void setValuesFromMap(TransformMap map) {
		this.identifier = map.get("referralrequest.identifier");
		this.status = map.get("referralrequest.status");
		this.type = map.get("referralrequest.type");
		this.priority = map.get("referralrequest.priority");
		this.serviceRequested = map.get("referralrequest.serviceRequested");
		this.subject = map.get("referralrequest.subject");
		this.context = map.get("referralrequest.context");
		this.occurrence = map.get("referralrequest.occurrence");
		this.authoredOn = map.get("referralrequest.authoredOn");
		this.specialty = map.get("referralrequest.specialty");
		this.recipient = map.get("referralrequest.recipient");
		this.reasonCode = map.get("referralrequest.reasonCode");
	}
	
	@Override
	public void setResourceData(TransformMap map) {
		
		referralrequest.setIdentifier(IdentifierUtils.getIdentifierList(map, resourceName));
		if (status !=null)
		referralrequest.setStatus(ReferralRequestStatus.valueOf(status.toUpperCase()));
		referralrequest.setType(new CodeableConcept().setText(type));
		if (priority !=null)
		referralrequest.setPriority(ReferralPriority.valueOf(priority.toUpperCase()));
		referralrequest.addServiceRequested(new CodeableConcept().setText(serviceRequested));
		referralrequest.getSubject().setReference(subject);
		referralrequest.getContext().setReference(context);
		
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (occurrence !=null)
			referralrequest.getOccurrenceDateTimeType().setValue(DateUtils.parseDate(occurrence,new String[]{"yyyyMMdd","yyyy-MM-dd"}));
		} catch (FHIRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (authoredOn !=null)
		referralrequest.setAuthoredOn(DateUtils.parseDate(authoredOn,FHIRConstants.DATE_FORMATS));
		
		referralrequest.setSpecialty(new CodeableConcept().setText(specialty));
		referralrequest.addRecipient().setReference(recipient);
		referralrequest.addReasonCode(new CodeableConcept().setText(reasonCode));
		
}

	/*
	 * Return the FHIR resource object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.referralrequest;
	}
	
}
