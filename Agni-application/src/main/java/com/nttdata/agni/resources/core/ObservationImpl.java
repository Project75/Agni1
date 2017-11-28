/**
 * 
 */
package com.nttdata.agni.resources.core;

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
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Observation.ObservationReferenceRangeComponent;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.Observation.ObservationStatus;
import org.hl7.fhir.dstu3.model.SimpleQuantity;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * @author Harendra Pandey
 */
@ToString @Getter @Setter
public class ObservationImpl extends AbstractResource {
	String observationID, observationStatus, observationCode, observationSubject, observationEffective,
	observationIssued, observationPerformer, observationValue, observationInterpretation, observationComment, observationBodySite, 
	observationMethod, observationDevice, ReferenceRangeLow, ReferenceRangeHigh, ReferenceRangeType, ReferenceRangeAppliesTo, ReferenceRangeText;
	
	Observation observation;
	String resourceName="observation";
	
	//referenced resources, hardcoded default values but can be changed by setters
	String refPatient = "Patient/";
	String refDevice = "Device/";
	String refEncounter ="Encounter/"; 
	String refPractitioner ="Practitioner/"; 
	String refRelatedPerson ="RelatedPerson/";
	
	public ObservationImpl() {
		super();
		this.observation = new Observation();
		observation.setId(IdDt.newRandomUuid());
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	//input map ("observation.status","ACTIVE")
	public void setValuesFromMap(HashMap<String,String> map) {
		 this.observationStatus= map.get("observation.status");
		 this.observationCode= map.get("observation.code");
		 this.observationSubject= refPatient+map.get("patient.identifier");
		 setObservationEffective(map.get("observation.effective"));
		 setObservationIssued(map.get("observation.issued"));
		 this.observationPerformer= map.get("observation.performer");
		 this.observationValue= map.get("observation.value");
		 this.observationInterpretation= map.get("observation.interpretation");
		 this.observationComment= map.get("observation.comment");
		 
			
		 this.observationBodySite = map.get("observation.bodysite");
		 this.observationMethod= map.get("observation.method");
		 this.observationDevice= refDevice+map.get("observation.device");
		 this.ReferenceRangeLow= map.get("observation.referenceRange.low");
		 this.ReferenceRangeHigh= map.get("observation.referenceRange.high");
		 this.ReferenceRangeType= map.get("observation.referenceRange.type");
		 this.ReferenceRangeAppliesTo= map.get("observation.referenceRange.appliesto");
		 this.ReferenceRangeText = map.get("observation.referenceRange.text");
	}
	
	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResourceData()
	 */
	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		//super.setResourceData();
		if (getObservationID() != null){
			observation.addIdentifier().setValue(getObservationID());
		}
		List<Reference> thePerformer ;
		//observation.setPerformer(new List<Reference> thePerformer);
		if (getObservationStatus() != null){
		//observation.setStatus(ObservationStatus.FINAL);
				//.valueOf(getObservationStatus()));
		}
		if (getObservationCode() != null){
		observation.setCode(new CodeableConcept().setText(getObservationCode()));
		}
		if (getObservationSubject() != null){
		observation.setSubject(new Reference().setReference(getObservationSubject()));
		}
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		try {
			if (getObservationEffective() != null){
			observation.setEffective(new DateTimeType(formatter1.parse(getObservationEffective())));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if (getObservationIssued() != null){
			observation.setIssued(formatter1.parse(getObservationIssued()));}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//observation.addPerformer().setReference(getObservationPerformer());
		//observation.setValue(new CodeableConcept().setText(getObservationValue()));
		//observation.setInterpretation(new CodeableConcept().setText(getObservationInterpretation()));
		//observation.setComment(getObservationComment());
		observation.setBodySite(new CodeableConcept().setText(getObservationBodySite()));
		observation.setMethod(new CodeableConcept().setText(getObservationMethod()));
		observation.setDevice(new Reference().setReference(getObservationDevice()));
		
		observation.setSubject(new Reference().setReference(getObservationSubject()));
		
		List<ObservationReferenceRangeComponent> theReferenceRange = new ArrayList<ObservationReferenceRangeComponent>();
		ObservationReferenceRangeComponent refRange = new ObservationReferenceRangeComponent();
		SimpleQuantity value =  new SimpleQuantity();
		value.setCode(getReferenceRangeLow());
		refRange.setLow(value );
		theReferenceRange.add(refRange);
		theReferenceRange.add(new ObservationReferenceRangeComponent().setHigh((SimpleQuantity) new SimpleQuantity().setCode(getReferenceRangeHigh())));
		theReferenceRange.add(new ObservationReferenceRangeComponent().setType(new CodeableConcept().setText(ReferenceRangeType)));
		observation.setReferenceRange(theReferenceRange );
	
	}
	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#getResourcedata()
	 */
	@Override
	public void getResourcedata() {
		// TODO Auto-generated method stub
		super.getResourcedata();
	}
	
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.observation;
	}
	
	public void setObservationEffective(String observationEffective) {
		observationEffective="20170828112030";
		System.out.println("observationEffective--"+observationEffective);
		this.observationEffective = observationEffective.substring(0, 4) +"-"+ observationEffective.substring(4, 6) 
										+"-"+ observationEffective.substring(6, 8) +" "+ observationEffective.substring(8, 10) 
				 					+":"+ observationEffective.substring(10, 12) +":"+ observationEffective.substring(12, 14);
	}
	
	public void setObservationIssued(String observationIssued) {
		observationIssued="20170828112030";
		if (observationIssued.length() >= 8){
		this.observationIssued = observationIssued.substring(0, 4) +"-"+ observationIssued.substring(4, 6) 
								+"-"+ observationIssued.substring(6, 8);
		}
		if (observationIssued.length() >= 14){
			this.observationIssued = this.observationIssued +" "+ observationIssued.substring(8, 10) 
 					+":"+ observationIssued.substring(10, 12) +":"+ observationIssued.substring(12, 14);

		}
	}

	public String toString2() {
		// TODO Auto-generated method stub
		return observationID+" "+ observationStatus+" "+ observationCode+" "+ observationSubject+" "+ observationEffective+" "+
		observationIssued+" "+ observationPerformer+" "+ observationValue+" "+ observationInterpretation+" "+ observationComment+" "+ observationBodySite+" "+ 
		observationMethod+" "+ observationDevice+" "+ ReferenceRangeLow+" "+ ReferenceRangeHigh+" "+ ReferenceRangeType+" "+ ReferenceRangeAppliesTo+" "+ ReferenceRangeText;
	}
}
