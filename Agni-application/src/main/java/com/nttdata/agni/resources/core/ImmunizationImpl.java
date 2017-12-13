/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Immunization;
import org.hl7.fhir.dstu3.model.Immunization.ImmunizationStatus;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.SimpleQuantity;
import org.hl7.fhir.dstu3.model.Immunization.ImmunizationPractitionerComponent;
import org.hl7.fhir.dstu3.model.Immunization.ImmunizationExplanationComponent;


import com.nttdata.agni.domain.MappingList;

/**
 * Copyright NTT Data
 * Agni-Application-
 * @author Sameer Mathur
 *
 */
@ToString
@Getter 
@Setter 
public class ImmunizationImpl extends AbstractResource{

	Immunization immunization;
	private String identifier, vaccineCodingSystem, vaccineCodingCode, vaccineCodingDisplay, 
	patientIDType, patientIDSystem, patientIDValue, patientIDPeriodStart, patientIDPeriodEnd, patientIDAssigner,
	encounterIDType, encounterIDSystem, encounterIDValue, encounterIDPeriodStart, encounterIDPeriodEnd, encounterIDAssigner,
	immunizationDate, reportCodingSystem, reportCodingCode, reportCodingDisplay, location, manufacturerIDValue, manufacturerIDSystem,
	lotnumber, expirationDate, siteCodingSystem, siteCodingCode, siteCodingDisplay, routeCodingSystem, routeCodingCode, routeCodingDisplay,
	doseQuantity, practitioner, reasonNGCodingSystem, reasonNGCodingCode, reasonNGCodingDisplay , 
	reactionDate, reactionDetail, reactionDetailIDSystem, reactionDetailIDValue;
	
	String resourceName="immunization";
	
	public ImmunizationImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.immunization =  new Immunization();
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	/*Populate this object from the hashmap using the key for  each field*/
	public void setValuesFromMap(HashMap<String,String> map) {
		 this.vaccineCodingSystem = map.get("Immunization.vaccineCode.coding.system");
		 this.vaccineCodingCode = map.get("Immunization.vaccineCode.coding.code");
		 this.vaccineCodingDisplay = map.get("Immunization.vaccineCode.coding.display");
		 this.patientIDType = map.get("Immunization.patient.identifier.type");
		 this.patientIDSystem = map.get("Immunization.patient.identifier.system");
		 this.patientIDValue = map.get("Immunization.patient.identifier.value");
		 this.patientIDPeriodStart = map.get("Immunization.patient.identifier.period.start");
		 this.patientIDPeriodEnd = map.get("Immunization.patient.identifier.period.end");
		 this.patientIDAssigner= map.get("Immunization.patient.identifier.assigner");
		 this.encounterIDType = map.get("Immunization.encounter.identifier.type");
		 this.encounterIDSystem = map.get("Immunization.encounter.identifier.system");
		 this.encounterIDValue = map.get("Immunization.encounter.identifier.value");
		 this.encounterIDPeriodStart = map.get("Immunization.encounter.identifier.period.start");
		 this.encounterIDPeriodEnd = map.get("Immunization.encounter.identifier.period.end");
		 this.encounterIDAssigner = map.get("Immunization.encounter.identifier.assigner");
		 this.immunizationDate = map.get("Immunization.date");
		 this.reportCodingSystem = map.get("Immunization.reportOrigin.coding.system");
		 this.reportCodingCode = map.get("Immunization.reportOrigin.coding.code");
		 this.reportCodingDisplay = map.get("Immunization.reportOrigin.coding.display");
		 this.location = map.get("Immunization.location");
		 this.manufacturerIDSystem = map.get("Immunization.manufacturer.identifier.system");
		 this.manufacturerIDValue = map.get("Immunization.manufacturer.identifier.value");
		 this.lotnumber = map.get("Immunization.lotNumber");
		 this.expirationDate = map.get("Immunization.expirationDate");
		 this.siteCodingSystem = map.get("Immunization.site.coding.system");
		 this.siteCodingCode = map.get("Immunization.site.coding.code");
		 this.siteCodingDisplay = map.get("Immunization.site.coding.display");
		 this.routeCodingSystem = map.get("Immunization.route.coding.system");
		 this.routeCodingCode = map.get("Immunization.route.coding.code");
		 this.routeCodingDisplay = map.get("Immunization.route.coding.display");
		 this.doseQuantity = map.get("Immunization.doseQuantity");
		 this.practitioner = map.get("Immunization.practitioner");
		 this.reasonNGCodingSystem = map.get("Immunization.explanation.reasonNotGiven.coding.system");
		 this.reasonNGCodingCode = map.get("Immunization.explanation.reasonNotGiven.coding.code");
		 this.reasonNGCodingDisplay = map.get("Immunization.explanation.reasonNotGiven.coding.display");
		 this.reactionDate = map.get("Immunization.reaction.date");
		 this.reactionDetail = map.get("Immunization.reaction.detail");
		 this.reactionDetailIDSystem = map.get("Immunization.reaction.detail.identifier.system");
		 this.reactionDetailIDValue = map.get("Immunization.reaction.detail.identifier.value");
	 
	}
	
	@Override
	/*Adding populated values from known mappings to the appointment resource*/
	public void setResourceData() {
		
		
		//immunization.identifier
		immunization.addIdentifier()
									.setValue(this.getIdentifier())
									.setType(new CodeableConcept().setText("Immunization"));
		
		//immunization.status (MapToDo)
		immunization.setStatus(ImmunizationStatus.valueOf("COMPLETED"));
		
		//immunization.notGiven (MapToDo)
		immunization.setNotGiven(false);
		
		//immunization.vaccineCode
		CodeableConcept vaccCode = new CodeableConcept();
		vaccCode.addCoding()
							.setSystem(this.getVaccineCodingSystem())
							.setCode(this.getVaccineCodingCode())
							.setDisplay(this.getVaccineCodingDisplay());
		vaccCode.setText("");
		immunization.setVaccineCode(vaccCode);

		
		//immunization.patient (Reference value)
			// prepare patient to be added to immunization
			Reference patient = new Reference();
		
			//patient.reference
			patient.setReference("");

			//patient.identifier
			Identifier thePatientIdentifier = new Identifier();
			
			thePatientIdentifier.setValue(this.getPatientIDValue());
			thePatientIdentifier.setType(new CodeableConcept().setText(this.getPatientIDType()));
			thePatientIdentifier.setSystem(this.getPatientIDSystem());
			
				Period pidPeriod = new Period();
				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmm");
				try {
					if (this.getPatientIDPeriodStart() != null){
						pidPeriod.setStart(dateFormatter.parse(this.getPatientIDPeriodStart()));
						new Period().setStart(new SimpleDateFormat("yyyyMMddHHmm").parse(this.getPatientIDPeriodStart()));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
							
				try {
					if (this.getPatientIDPeriodEnd() != null){
						pidPeriod.setEnd(dateFormatter.parse(this.getPatientIDPeriodEnd()));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			thePatientIdentifier.setPeriod(pidPeriod);
			
				Reference pidassigner = new Reference();
				pidassigner.setId(this.getPatientIDAssigner());
			
			thePatientIdentifier.setAssigner(pidassigner);
			
			patient.setIdentifier(thePatientIdentifier);
			
			//patient.display
			patient.setDisplay("");
			
		immunization.setPatient(patient);

		//immunization.encounter (Reference value)
		// prepare encounter to be added to immunization
		Reference encounter = new Reference();
	
		//encounter.reference
		encounter.setReference("");

		//encounter.identifier
		Identifier theEncounterIdentifier = new Identifier();
		
		theEncounterIdentifier.setValue(this.getEncounterIDValue());
		theEncounterIdentifier.setType(new CodeableConcept().setText(this.getEncounterIDType()));
		theEncounterIdentifier.setSystem(this.getEncounterIDSystem());
		
			Period encounterIDPeriod = new Period();
			try {
				if (this.getEncounterIDPeriodStart() != null){
					encounterIDPeriod.setStart(dateFormatter.parse(this.getEncounterIDPeriodStart()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			try {
				if (this.getEncounterIDPeriodEnd() != null){
					encounterIDPeriod.setEnd(dateFormatter.parse(this.getEncounterIDPeriodEnd()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
		}
			theEncounterIdentifier.setPeriod(encounterIDPeriod);
		
			Reference encounterAssigner = new Reference();
			encounterAssigner.setId(this.getEncounterIDAssigner());
		
			theEncounterIdentifier.setAssigner(encounterAssigner);
		
		encounter.setIdentifier(theEncounterIdentifier);
		
		//encounter.display
		encounter.setDisplay("");
		
	immunization.setEncounter(encounter);

	//immunization.date
	try {
		if (this.getImmunizationDate() != null){
			immunization.setDate(dateFormatter.parse(this.getImmunizationDate()));
		}
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	//Immunization.primarySource (MapToDo)
	immunization.setPrimarySource(true);
	
	//Immunization.reportOrigin
	Coding immReportOriginCoding = new Coding();
	
	immReportOriginCoding.setSystem(this.getReportCodingSystem());
	immReportOriginCoding.setCode(this.getReportCodingCode());
	immReportOriginCoding.setDisplay(this.getReportCodingDisplay());
	
	immunization.setReportOrigin(new CodeableConcept().addCoding(immReportOriginCoding));

	//Immunization.location (Reference Value)
	immunization.setLocation(new Reference().setDisplay(this.getLocation()));
	
	
	//Immunization.manufacturer (Reference value)
	Reference mfr = new Reference();
	mfr.setIdentifier(new Identifier()
									.setSystem(this.getManufacturerIDSystem())
									.setValue(this.getManufacturerIDValue()));
	immunization.setManufacturer(mfr);
	
	//Immunization.lotNumber
	immunization.setLotNumber(this.getLotnumber());
	
	//Immunization.expirationDate
	try {
		if (this.getExpirationDate() != null){
			immunization.setDate(dateFormatter.parse(this.getExpirationDate()));
		}
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	//Immunization.site
	Coding theImmSiteCoding = new Coding();
	theImmSiteCoding.setSystem(this.getSiteCodingSystem());
	theImmSiteCoding.setCode(this.getSiteCodingCode());
	theImmSiteCoding.setDisplay(this.getSiteCodingDisplay());
	
	immunization.setSite(new CodeableConcept().addCoding(theImmSiteCoding));

	//Immunization.site
	
	Coding theImmRouteCoding = new Coding();
	theImmRouteCoding.setSystem(this.getRouteCodingSystem());
	theImmRouteCoding.setCode(this.getRouteCodingCode());
	theImmRouteCoding.setDisplay(this.getRouteCodingDisplay());
	
	immunization.setRoute(new CodeableConcept().addCoding(theImmRouteCoding));
	
	//Immunization.doseQuantity
	SimpleQuantity immDoseQuantity = new SimpleQuantity();
	immDoseQuantity.setValue(Long.parseLong(this.getDoseQuantity()));
	immunization.setDoseQuantity(immDoseQuantity);
	
	//Immunization.practitioner
	immunization.addPractitioner().setActor(new Reference().setDisplay(this.getPractitioner()));
	
	
	//Immunization.explanation
	ImmunizationExplanationComponent theImmExplanation = new ImmunizationExplanationComponent();
	Coding theImmExplanationCoding = new Coding();
	theImmExplanationCoding.setSystem(this.getReasonNGCodingSystem());
	theImmExplanationCoding.setCode(this.getReasonNGCodingCode());
	theImmExplanationCoding.setDisplay(this.getReasonNGCodingDisplay());
	
	theImmExplanation.addReasonNotGiven().addCoding(theImmExplanationCoding);

	immunization.setExplanation(theImmExplanation);
	
	//Immunization.reaction
	
	Date objreactionDate = new Date();
	try {
		if (this.getReactionDate()!= null){
			objreactionDate = dateFormatter.parse(this.getReactionDate());
		}
	} catch (ParseException e) {
		e.printStackTrace();
	}
	immunization.addReaction()
							.setDate(objreactionDate)
							.setDetail(new Reference()
														.setDisplay(this.getReactionDetail())
														.setIdentifier(new Identifier()
																						.setSystem(this.getReactionDetailIDSystem())
																						.setValue(this.getReactionDetailIDValue())));

	//Immunization.vaccinationProtocol
	immunization.addVaccinationProtocol().setDescription("");
}

	
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.immunization;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.identifier +" "+this.vaccineCodingCode+" "+this.patientIDValue +" "+this.encounterIDValue+" ";
	}
	
}