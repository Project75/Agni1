/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.nttdata.agni.resources.utils.TransformMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.Resource;

import com.nttdata.agni.resources.utils.TransformMap;

import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Appointment;
import org.hl7.fhir.dstu3.model.Appointment.AppointmentParticipantComponent;
import org.hl7.fhir.dstu3.model.Appointment.AppointmentStatus;
import org.hl7.fhir.dstu3.model.Appointment.ParticipationStatus;
import org.hl7.fhir.dstu3.model.Reference;

/**
 * Copyright NTT Data
 * Agni-Applicationo-
 * @author Sameer Mathur
 *
 */
@ToString
@Getter 
@Setter 
public class AppointmentImpl extends AbstractResource{

	Appointment appointment;
	private String identifier, status, serviceCategory, serviceType, specialty, appointmentType, reason, indication, 
	priority, description, supportingInformation, start, end, minutesDuration, slot, created, comment, incomingReferral, participantType,
	participantActor, participantStatus, requestedPeriod;
	
	String resourceName="appointment";
	
	public AppointmentImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.appointment =  new Appointment();
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	/*Populate this object from the hashmap using the key for  each field*/
	public void setValuesFromMap(TransformMap map) {
		 this.identifier = map.get("appointment.identifier");
		 this.status = map.get("appointment.status");
		 this.appointmentType = map.get("appointment.appointmentType");
		 this.reason = map.get("appointment.reason");
		 this.priority = map.get("appointment.priority");
		 this.description = map.get("appointment.description");
		 this.start = map.get("appointment.start");
		 this.end= map.get("appointment.end");
		 this.comment = map.get("appointment.comment");
		 this.participantType = map.get("appointment.participant.type");
		 this.participantActor = map.get("appointment.participant.actor");
		 this.participantStatus = map.get("appointment.participant.status");
		 setRequestedPeriod(map.get("appointment.requestedPeriod"));
	}
	
	@Override
	/*Adding populated values from known mappings to the appointment resource*/
	public void setResourceData() {
		
		
		//appointment.identifier
		List<Identifier> identifiers = new ArrayList<Identifier>();
		Identifier theIdentifier = new Identifier();
		theIdentifier.setValue(this.getIdentifier());
		theIdentifier.setType(new CodeableConcept().setText("Appointment"));
		identifiers.add(theIdentifier);
		appointment.setIdentifier(identifiers);
		
		//appointment.status
		appointment.setStatus(AppointmentStatus.valueOf(this.getStatus().toUpperCase()));		
		
		//appointment.type
		appointment.setAppointmentType(new CodeableConcept().setText(this.getAppointmentType()));
		
		//appointment.reason
		List<CodeableConcept> appointmentReason = new ArrayList<CodeableConcept>();
		CodeableConcept appReason = new CodeableConcept();
		appReason.setText(this.getReason());
		appointmentReason.add(appReason);
		appointment.setReason(appointmentReason);
		
		//appointment.priority
		appointment.setPriority(Integer.parseInt(this.getPriority()));
		
		//appoinment.description
		appointment.setDescription(this.getDescription());
		
		//appointment.start
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			if (this.getStart() != null){
			appointment.setStart(dateFormatter.parse(this.getStart()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//appointment.end
		try {
			if (this.getEnd() != null){
			appointment.setEnd(dateFormatter.parse(this.getEnd()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//appointment.comment	
		appointment.setComment(this.getComment());
		
		//appointment.participant
		//prepare participant to be added to appointment
		List<AppointmentParticipantComponent> theParticipant = new ArrayList<AppointmentParticipantComponent>();
		AppointmentParticipantComponent participant = new AppointmentParticipantComponent();
		
			//participant.type
			List<CodeableConcept> theType = new ArrayList<CodeableConcept>();
			CodeableConcept appType = new CodeableConcept();
			appType.setText(this.getParticipantType());
			theType.add(appType);
			participant.setType(theType);
					
			//participant.actor - Reference Value
			Reference patientRef = new Reference();
			patientRef.setId(this.getParticipantActor());
			participant.setActor(patientRef);
			
			//participant.status
			participant.setStatus(ParticipationStatus.valueOf(this.getParticipantStatus()));
			
		theParticipant.add(participant);
		appointment.setParticipant(theParticipant);
		
		//appointment.requestedperiod
		SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Period> theRequestedPeriod = new ArrayList<Period>();
		Period requestPeriod = new Period();
			try {
				if (this.getRequestedPeriod()!= null){
				requestPeriod.setStartElement(new DateTimeType(dateFormatter1.parse(this.getRequestedPeriod())));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		theRequestedPeriod.add(requestPeriod);
		appointment.setRequestedPeriod(theRequestedPeriod);
	
}

	public void setRequestedPeriod(String requestedPeriod) {
		this.requestedPeriod = requestedPeriod.substring(0, 4) +"-"+ requestedPeriod.substring(4, 6) 
									+"-"+ requestedPeriod.substring(6, 8) +" "+ requestedPeriod.substring(8, 10) 
				 					+":"+ requestedPeriod.substring(10, 12);
	}
	
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.appointment;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.identifier +" "+this.status+" "+this.description +" "+this.participantActor+" "+this.start+" "+this.end;
	}
	
}