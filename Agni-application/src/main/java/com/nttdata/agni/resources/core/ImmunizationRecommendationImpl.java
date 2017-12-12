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
import org.hl7.fhir.dstu3.model.ImmunizationRecommendation;
/**
 * Copyright NTT Data
 * Module Agni-Application
 * @harendra
 *
 */


@ToString
@Getter 
@Setter 
/**
 * Class name should be <Resource Name>Impl e.g. PatientImpl
 */
public class ImmunizationRecommendationImpl extends AbstractResource{
	/**
	 * Field for storing the FHIR Resource. Need to import from org.hl7.fhir.dstu3.model.*
	 * e.g Patient patient; or Patient resource;
	 */
	ImmunizationRecommendation immunizationRecommendation;
	/**
	 * Create Local fields to store data from HL7. 
	 * e.g. familyName, givenName
	 */
	private String identifier;
	private String patient;
	private String recommendation;
	private String date;
	private String vaccineCode;
	private String targetDisease;
	private String doseNumber;
	private String forecastStatus;
	private String dateCriterion;
	private String dateCriterionCode;
	private String dateCriterionValue;
	private String protocol;
	private String protocolDoseSequence;
	private String protocolDescription;
	private String protocolAuthority;
	private String protocolSeries;
	private String supportingImmunization;
	private String supportingPatientInformation;

	
	String resourceName="ImmunizationRecommendation";
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public ImmunizationRecommendationImpl() {
		
		// TODO Auto-generated constructor stub
		this.immunizationRecommendation =  new ImmunizationRecommendation();
		immunizationRecommendation.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.identifier = map.get("immunizationrecommendation.identifier");
		this.patient = map.get("immunizationrecommendation.patient");
		this.recommendation = map.get("immunizationrecommendation.recommendation");
		this.date = map.get("immunizationrecommendation.date");
		this.vaccineCode = map.get("immunizationrecommendation.vaccineCode");
		this.targetDisease = map.get("immunizationrecommendation.targetDisease");
		this.doseNumber = map.get("immunizationrecommendation.doseNumber");
		this.forecastStatus = map.get("immunizationrecommendation.forecastStatus");
		this.dateCriterion = map.get("immunizationrecommendation.dateCriterion");
		this.dateCriterionCode = map.get("immunizationrecommendation.dateCriterion.Code");
		this.dateCriterionValue = map.get("immunizationrecommendation.dateCriterion.Value");
		this.protocol = map.get("immunizationrecommendation.protocol");
		this.protocolDoseSequence = map.get("immunizationrecommendation.protocol.doseSequence");
		this.protocolDescription = map.get("immunizationrecommendation.protocol.description");
		this.protocolAuthority = map.get("immunizationrecommendation.protocol.Authority");
		this.protocolSeries = map.get("immunizationrecommendation.protocol.Series");
		this.supportingImmunization = map.get("immunizationrecommendation.supportingImmunization");
		this.supportingPatientInformation = map.get("immunizationrecommendation.supportingPatientInformation");


	}
	
	@Override
	public void setResourceData() {
		//Todo

	
	}

	/*
	 * Return the FHIR resource object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.immunizationRecommendation;
	}
	

	
}
