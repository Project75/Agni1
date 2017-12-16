/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.MedicationAdministration;
import org.hl7.fhir.dstu3.model.MedicationAdministration.MedicationAdministrationDosageComponent;
import org.hl7.fhir.dstu3.model.MedicationAdministration.MedicationAdministrationStatus;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Quantity;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.SimpleQuantity;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.exceptions.FHIRException;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * Module Agni-Application
 * @author harendra
 *
 */


@ToString
@Getter 
@Setter 
/**
 * Class name should be <Resource Name>Impl e.g. PatientImpl
 */
public class MedicationAdministrationImpl extends AbstractResource{
	/**
	 * Field for storing the FHIR Resource. Need to import from org.hl7.fhir.dstu3.model.*
	 * e.g Patient patient; or Patient resource;
	 */
	MedicationAdministration medicationAdministration;
	/**
	 * Create Local fields to store data from HL7. 
	 * e.g. familyName, givenName
	 */
	private String identifier;
	private String definition;
	private String partOf;
	private String status;
	private String category;
	private String medicationCodeableConcept;
	private String medicationReference;
	private String subject;
	private String context;
	private String supportingInformation;
	private String effectiveDateTime;
	private String effectivePeriod;
	private String performerActor;
	private String performerOnBehalfOf;
	private String notGiven;
	private String reasonNotGiven;
	private String reasonCode;
	private String reasonReference;
	private String prescription;
	private String device;
	private String note;
	private String dosageText;
	private String dosageSite;
	private String dosageRoute;
	private String dosageMethod;
	private String dosageDose;
	private String dosageRateX;
	private String eventHistory;
	
	String resourceName="MedicationAdministration";
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public MedicationAdministrationImpl() {
		
		// TODO Auto-generated constructor stub
		this.medicationAdministration =  new MedicationAdministration();
		medicationAdministration.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		identifier = map.get("medicationAdministration.identifier");
		definition = map.get("medicationAdministration.definition");
		partOf = map.get("medicationAdministration.partOf");
		status = map.get("medicationAdministration.status");
		category = map.get("medicationAdministration.category");
		medicationCodeableConcept = map.get("medicationAdministration.medicationCodeableConcept");
		medicationReference = map.get("medicationAdministration.medicationReference");
		subject = map.get("medicationAdministration.subject");
		context = map.get("medicationAdministration.context");
		supportingInformation = map.get("medicationAdministration.supportingInformation");
		effectiveDateTime = map.get("medicationAdministration.effectiveDateTime");
		effectivePeriod = map.get("medicationAdministration.effectivePeriod");
		performerActor = map.get("medicationAdministration.performerActor");
		performerOnBehalfOf = map.get("medicationAdministration.performerOnBehalfOf");
		notGiven = map.get("medicationAdministration.notGiven");
		reasonNotGiven = map.get("medicationAdministration.reasonNotGiven");
		reasonCode = map.get("medicationAdministration.reasonCode");
		reasonReference = map.get("medicationAdministration.reasonReference");
		prescription = map.get("medicationAdministration.prescription");
		device = map.get("medicationAdministration.device");
		note = map.get("medicationAdministration.note");
		dosageText = map.get("medicationAdministration.dosageText");
		dosageSite = map.get("medicationAdministration.dosageSite");
		dosageRoute = map.get("medicationAdministration.dosageRoute");
		dosageMethod = map.get("medicationAdministration.dosageMethod");
		dosageDose = map.get("medicationAdministration.dosageDose");
		dosageRateX = map.get("medicationAdministration.dosageRateX");
		eventHistory = map.get("medicationAdministration.eventHistory");


	}
	
	@Override
	public void setResourceData() {
		medicationAdministration.addIdentifier(new Identifier().setValue(identifier));
		medicationAdministration.addDefinition(new Reference().setReference(definition));
		medicationAdministration.addPartOf(new Reference().setReference(partOf));
		//todo
		medicationAdministration.setStatus(MedicationAdministrationStatus.INPROGRESS); //new CodeableConcept().setText(status));
		medicationAdministration.setCategory(new CodeableConcept().setText(category));
		//medicationAdministration.setMedication[x](medication[x]);
		medicationAdministration.setMedication(new CodeableConcept().setText(medicationCodeableConcept));
		medicationAdministration.setMedication(new Reference().setReference(medicationReference));
		medicationAdministration.setSubject(new Reference().setReference(subject));
		medicationAdministration.setContext(new Reference().setReference(context));
		medicationAdministration.addSupportingInformation(new Reference().setReference(supportingInformation));
		//medicationAdministration.setEffective(effective[x]);
		try{
			if(effectiveDateTime != null){
				medicationAdministration.setEffective(new DateTimeType(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(effectiveDateTime)));
			}
			}catch(ParseException e) {
				e.printStackTrace();
			}
		try {
			medicationAdministration.setEffective(new Period().setStart(new SimpleDateFormat("yyyyMMddHHmm").parse(effectivePeriod)));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //.effectivePeriod);
		//todo
		medicationAdministration.addPerformer().setActor(new Reference().setReference(null));
		//medicationAdministration.setPerformerOnBehalfOf(new Reference().setReference(performerOnBehalfOf));
		medicationAdministration.setNotGiven(new Boolean(false));
		medicationAdministration.addReasonNotGiven(new CodeableConcept().setText(reasonNotGiven));
		medicationAdministration.addReasonCode(new CodeableConcept().setText(reasonCode));
		medicationAdministration.addReasonReference(new Reference().setReference(reasonReference));
		medicationAdministration.setPrescription(new Reference().setReference(prescription));
		medicationAdministration.addDevice(new Reference().setReference(device));
		//Annotation type
		//medicationAdministration.setNote(note);

		MedicationAdministrationDosageComponent t;
		
		medicationAdministration.getDosage().setSite(new CodeableConcept().setText(dosageSite));
		medicationAdministration.getDosage().setRoute(new CodeableConcept().setText(dosageRoute));
		medicationAdministration.getDosage().setMethod(new CodeableConcept().setText(dosageMethod));
		medicationAdministration.getDosage().setDose((SimpleQuantity) new SimpleQuantity().setCode(dosageDose));
		Type t1 = null;
		medicationAdministration.getDosage().setRate(t1);

		try {
			medicationAdministration.getDosage().getRateRatio().setDenominator(new Quantity()) ;
		} catch (FHIRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//.setRate(dosageRateX);
		medicationAdministration.addEventHistory(new Reference().setReference(eventHistory));



}

	/*
	 * Return the FHIR resource object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.medicationAdministration;
	}
	

	
}
