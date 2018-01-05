/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.Date;
import com.nttdata.agni.resources.utils.TransformMap;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
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
	 * e.g Patient patient; or Patient immunizationRecommendation;
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

	
	String immunizationRecommendationName="ImmunizationRecommendation";
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public ImmunizationRecommendationImpl() {
		
		// TODO Auto-generated constructor stub
		this.immunizationRecommendation =  new ImmunizationRecommendation();
		immunizationRecommendation.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		
		setValuesFromMap(data);
		setResourceData(data);

	}
	
	public void setValuesFromMap(TransformMap map) {
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
	public void setResourceData(TransformMap map) {
		//Todo
		immunizationRecommendation.addIdentifier().setValue(identifier).setSystem("");
		immunizationRecommendation.setPatient(new Reference().setReference(patient));
				//new Identifier().setValue(patient));
		//List<ImmunizationRecommendationRecommendationComponent> t;
	//	immunizationRecommendation.addRecommendation().setDate(new Date("yyyy-MM-dd"));
		
		//(new Reference().setReference(recommendation));

		immunizationRecommendation.addRecommendation().setVaccineCode(new CodeableConcept().setText(vaccineCode));
		
		immunizationRecommendation.addRecommendation().setTargetDisease(new CodeableConcept().setText(targetDisease));
		
	//	immunizationRecommendation.addRecommendation().setDoseNumber(Integer.parseInt(doseNumber));//new CodeableConcept().setText(doseNumber));
		immunizationRecommendation.addRecommendation().setForecastStatus(new CodeableConcept().setText(forecastStatus));
		immunizationRecommendation.addRecommendation().addDateCriterion().setCode(new CodeableConcept().setText(dateCriterion));

//		immunizationRecommendation.addRecommendation().addDateCriterion().setValue(new Date("yyyy-MM-dd"));//.parse(dateCriterionvalue));
		immunizationRecommendation.addRecommendation().addSupportingPatientInformation(new Reference().setReference(supportingPatientInformation));
		//(new DateTimeType(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(protocol)));

		immunizationRecommendation.addRecommendation().getProtocol().setDescription(protocolDescription);
		immunizationRecommendation.addRecommendation().getProtocol().setAuthority(new Reference().setReference(protocolAuthority));
		immunizationRecommendation.addRecommendation().getProtocol().setSeries(protocolSeries);
		
		immunizationRecommendation.addRecommendation().addSupportingImmunization().setReference(supportingImmunization);
		

	
	}

	/*
	 * Return the FHIR immunizationRecommendation object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.immunizationRecommendation;
	}
	

	
}
