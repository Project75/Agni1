/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.Observation.ObservationStatus;

/**
 * Copyright NTT Data
 * @author Harendra Pandey
 */
public class ObservationImpl extends AbstractResource {
	String observationID, observationStatus, observationCode, observationSubject, observationEffective,
	observationIssued, observationPerformer, observationValue, observationInterpretation, observationComment, observationBodySite, 
	observationMethod, observationDevice, ReferenceRangeLow, ReferenceRangeHigh, ReferenceRangeType, ReferenceRangeAppliesTo, ReferenceRangeText;
	
	Observation observation;
	String resourceName="observation";
	
	public ObservationImpl() {
		super();
		this.observation = new Observation();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		 this.observationStatus= map.get("observation.status");
		 this.observationCode= map.get("observation.code");
		 this.observationSubject= map.get("observation.subject");
		 this.observationEffective= map.get("observation.effective");
		 this.observationIssued= map.get("observation.issued");
		 this.observationPerformer= map.get("observation.performer");
		 this.observationValue= map.get("observation.value");
		 this.observationInterpretation= map.get("observation.interpretation");
		 this.observationComment= map.get("observation.comment");
		 
			
		 this.observationBodySite = map.get("observation.bodysite");
		 this.observationMethod= map.get("observation.method");
		 this.observationDevice= map.get("observation.device");
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
//		SimpleDateFormat formatter1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//		try {
//			if (getObservationEffective() != null){
//			observation.setEffective(new DateTimeType(formatter1.parse(getObservationEffective())));
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		try {
//			if (getObservationIssued() != null){
//			observation.setIssued(formatter1.parse(getObservationIssued()));}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		//observation.addPerformer().setReference(getObservationPerformer());
		//observation.setValue(new CodeableConcept().setText(getObservationValue()));
		//observation.setInterpretation(new CodeableConcept().setText(getObservationInterpretation()));
		//observation.setComment(getObservationComment());
		observation.setBodySite(new CodeableConcept().setText(getObservationBodySite()));
		observation.setMethod(new CodeableConcept().setText(getObservationMethod()));
		observation.setDevice(new Reference().setReference(getObservationDevice()));
	
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
	/**
	 * @return the observation
	 */
	public Observation getObservation() {
		return observation;
	}
	/**
	 * @param observation the observation to set
	 */
	public void setObservation(Observation observation) {
		this.observation = observation;
	}
	public String getObservationID() {
		return observationID;
	}
	public void setObservationID(String observationID) {
		this.observationID = observationID;
	}
	public String getObservationStatus() {
		return observationStatus;
	}
	public void setObservationStatus(String observationStatus) {
		this.observationStatus = observationStatus;
	}
	public String getObservationCode() {
		return observationCode;
	}
	public void setObservationCode(String observationCode) {
		this.observationCode = observationCode;
	}
	public String getObservationSubject() {
		return observationSubject;
	}
	public void setObservationSubject(String observationSubject) {
		this.observationSubject = observationSubject;
	}
	public String getObservationEffective() {
		return observationEffective;
	}
	public void setObservationEffective(String observationEffective) {
		this.observationEffective = observationEffective.substring(0, 4) +"-"+ observationEffective.substring(4, 6) 
									+"-"+ observationEffective.substring(6, 8) +" "+ observationEffective.substring(8, 10) 
				 					+":"+ observationEffective.substring(10, 12) +":"+ observationEffective.substring(12, 14);
	}
	public String getObservationIssued() {
		return observationIssued;
	}
	public void setObservationIssued(String observationIssued) {
		this.observationIssued = observationIssued.substring(0, 4) +"-"+ observationIssued.substring(4, 6) 
								+"-"+ observationIssued.substring(6, 8)+" "+ observationIssued.substring(8, 10) 
			 					+":"+ observationIssued.substring(10, 12) +":"+ observationIssued.substring(12, 14);
	}
	public String getObservationPerformer() {
		return observationPerformer;
	}
	public void setObservationPerformer(String observationPerformer) {
		this.observationPerformer = observationPerformer;
	}
	public String getObservationValue() {
		return observationValue;
	}
	public void setObservationValue(String observationValue) {
		this.observationValue = observationValue;
	}
	public String getObservationInterpretation() {
		return observationInterpretation;
	}
	public void setObservationInterpretation(String observationInterpretation) {
		this.observationInterpretation = observationInterpretation;
	}
	public String getObservationComment() {
		return observationComment;
	}
	public void setObservationComment(String observationComment) {
		this.observationComment = observationComment;
	}
	public String getObservationBodySite() {
		return observationBodySite;
	}
	public void setObservationBodySite(String observationBodySite) {
		this.observationBodySite = observationBodySite;
	}
	public String getObservationMethod() {
		return observationMethod;
	}
	public void setObservationMethod(String observationMethod) {
		this.observationMethod = observationMethod;
	}
	public String getObservationDevice() {
		return observationDevice;
	}
	public void setObservationDevice(String observationDevice) {
		this.observationDevice = observationDevice;
	}
	public String getReferenceRangeLow() {
		return ReferenceRangeLow;
	}
	public void setReferenceRangeLow(String ReferenceRangeLow) {
		this.ReferenceRangeLow = ReferenceRangeLow;
	}
	public String getReferenceRangeHigh() {
		return ReferenceRangeHigh;
	}
	public void setReferenceRangeHigh(String ReferenceRangeHigh) {
		this.ReferenceRangeHigh = ReferenceRangeHigh;
	}
	public String getReferenceRangeType() {
		return ReferenceRangeType;
	}
	public void setReferenceRangeType(String ReferenceRangeType) {
		this.ReferenceRangeType = ReferenceRangeType;
	}
	public String getReferenceRangeAppliesTo() {
		return ReferenceRangeAppliesTo;
	}
	public void setReferenceRangeAppliesTo(String ReferenceRangeAppliesTo) {
		this.ReferenceRangeAppliesTo = ReferenceRangeAppliesTo;
	}
	public String getReferenceRangeText() {
		return ReferenceRangeText;
	}
	public void setReferenceRangeText(String ReferenceRangeText) {
		this.ReferenceRangeText = ReferenceRangeText;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
