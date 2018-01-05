/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.nttdata.agni.resources.utils.TransformMap;
import java.util.List;
import java.util.Date;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.SimpleQuantity;
import org.hl7.fhir.dstu3.model.Duration;
import org.hl7.fhir.dstu3.model.Encounter;
import org.hl7.fhir.dstu3.model.Encounter.EncounterStatus;
import org.hl7.fhir.dstu3.model.Encounter.EncounterParticipantComponent;
import org.hl7.fhir.dstu3.model.Encounter.EncounterHospitalizationComponent;



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
public class EncounterImpl extends AbstractResource{

	Encounter encounter;
	private String identifier, encounterClass, encounterType, priority, subject, episodeOfCare, 
	participant, participantType, participantPeriod, participantIndividual, appointment, periodStart, periodEnd, length, reason, 
	hospPreAdmitIdentifier, hospAdmitSource, hospReAdmission, hospDietPreference, hospSpecialCourtesy, hospSpecialArrangement, hospDestination, 
	hospDischargeDisposition, locLocation, serviceProvider;


	
	String resourceName="encounter";
	
	public EncounterImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.encounter =  new Encounter();
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		
		setValuesFromMap(data);
		setResourceData(data);

	}
	/*Populate this object from the hashmap using the key for  each field*/
	public void setValuesFromMap(TransformMap map) {
		this.identifier = map.get("Encounter.identifier");
		this.encounterClass = map.get("Encounter.class");
		this.encounterType = map.get("Encounter.type");
		this.priority = map.get("Encounter.priority");
		this.subject = map.get("Encounter.subject");
		this.episodeOfCare = map.get("Encounter.episodeOfCare");
		this.participant = map.get("Encounter.participant.type");
		this.participantType = map.get("Encounter.participant.period.start");
		this.participantPeriod = map.get("Encounter.participant.period.end");
		this.participantIndividual = map.get("Encounter.participant.individual");
		this.appointment = map.get("Encounter.appointment");
		this.periodStart = map.get("Encounter.period.start");
		this.periodEnd = map.get("Encounter.period.end");
		this.length = map.get("Encounter.length");
		this.reason = map.get("Encounter.reason");
		this.hospPreAdmitIdentifier = map.get("Encounter.hospitalization.preAdmissionIdentifier");
		this.hospAdmitSource = map.get("Encounter.hospitalization.admitSource");
		this.hospReAdmission = map.get("Encounter.hospitalization.reAdmission");
		this.hospDietPreference = map.get("Encounter.hospitalization.dietPreference");
		this.hospSpecialCourtesy = map.get("Encounter.hospitalization.specialCourtesy");
		this.hospSpecialArrangement = map.get("Encounter.hospitalization.specialArrangement");
		this.hospDestination = map.get("Encounter.hospitalization.destination");
		this.hospDischargeDisposition = map.get("Encounter.hospitalization.dischargeDisposition");
		this.locLocation = map.get("Encounter.location.location");
		this.serviceProvider = map.get("Encounter.serviceProvider");

	 }
	
	@Override
	/*Adding populated values from known mappings to the appointment resource*/
	public void setResourceData(TransformMap map) {
		
		
		//Encounter.identifier
		encounter.addIdentifier().setValue(this.getIdentifier());
		
		//Encounter.status (MapToDo)
		encounter.setStatus(EncounterStatus.valueOf("NULL"));
		
		//Encounter.statusHistory (MapToDo)
		encounter.addStatusHistory().setStatus(EncounterStatus.valueOf("UNKNOWN"));
		
		//Encounter.class () (inpatient | outpatient | ambulatory | emergency +.)
		encounter.setClass_(new Coding().setCode(this.getEncounterClass()));
		
		//Encounter.classHistory (MapToDo)
		encounter.addClassHistory().setClass_(new Coding().setCode(this.getEncounterClass()));
		
		//Encounter.type
		encounter.addType().setText(this.getEncounterType());

		//Encounter.priority
		encounter.setPriority(new CodeableConcept().setText(this.getPriority()));
		
		//Encounter.subject
		encounter.setSubject(new Reference().setIdentifier(new Identifier().setValue(this.getSubject())));
		
		//Encounter.episodeOfCare
		encounter.addEpisodeOfCare().setDisplay(this.getEpisodeOfCare());
		
		//Encounter.incomingReferral (MapToDo)
		encounter.addIncomingReferral().setDisplay("");
		
		//Encounter.participant
		EncounterParticipantComponent encounterParticipant = new EncounterParticipantComponent();
			encounterParticipant.addType().setText(this.getParticipantType());
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmm");
			try {
				if (this.getParticipantPeriod() != null){
					encounterParticipant.setPeriod(new Period().setStart(dateFormatter.parse(this.getParticipantPeriod())));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			encounterParticipant.setIndividual(new Reference().setDisplay(this.getParticipantIndividual()));
		encounter.addParticipant(encounterParticipant);
		
		//Encounter.appointment
		encounter.setAppointment(new Reference().setDisplay(this.getAppointment()));
		
		//Encounter.period
		try {
			if (this.getPeriodStart() != null && this.getPeriodEnd() != null){
				encounter.setPeriod(new Period()
												.setStart(dateFormatter.parse(this.getPeriodStart()))
												.setEnd(dateFormatter.parse(this.getPeriodEnd())));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//Encounter.length (ToDo)
		//Date objEndDate = encounter.getPeriod().getEnd();
		//Date objStartDate = encounter.getPeriod().getStart();
		//double diff = objEndDate.getTime() -  objStartDate.getTime(); 
		//double diffDays = (double)(diff/(24*60*60*1000));
		//encounter.setLength(new Duration().set
		
		//Encounter.reason
		encounter.addReason().setText(this.getReason());
		
		//Encounter.diagnosis (MapToDo)
		encounter.addDiagnosis().setCondition(new Reference().setDisplay(""));
		
		//Encounter.account (MapToDo)
		encounter.addAccount().setDisplay("");
		
		//Encounter.hospitalization
		encounter.setHospitalization(new EncounterHospitalizationComponent()
																			.setPreAdmissionIdentifier(new Identifier().setValue(this.getHospPreAdmitIdentifier()))
																			.setOrigin(new Reference().setDisplay(""))
																			.setAdmitSource(new CodeableConcept().setText(this.getHospAdmitSource()))
																			.setReAdmission(new CodeableConcept().setText(this.getHospReAdmission()))
																			.addDietPreference(new CodeableConcept().setText(this.getHospDietPreference()))
																			.addSpecialCourtesy(new CodeableConcept().setText(this.getHospSpecialCourtesy()))
																			.addSpecialArrangement(new CodeableConcept().setText(this.getHospSpecialArrangement()))
																			.setDestination(new Reference().setDisplay(this.getHospDestination()))
																			.setDischargeDisposition(new CodeableConcept().setText(this.getHospDischargeDisposition()))
																			);

		//Encounter.serviceProvider
		encounter.setServiceProvider(new Reference().setDisplay(this.getServiceProvider()));
		
		//Encounter.partOf (MapToDo)
		encounter.setPartOf(new Reference().setDisplay(""));
		
		
}

	
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.encounter;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.identifier +" "+this.encounterClass+" "+this.encounterType +" "+this.priority+" ";
	}
	
}