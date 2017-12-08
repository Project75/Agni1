/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.PractitionerRole;
import org.hl7.fhir.dstu3.model.PractitionerRole.PractitionerRoleAvailableTimeComponent;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * @author Neha 
 */

@Getter
@Setter

public class PractitionerRoleImpl extends AbstractResource{
	/**
	 * 
	 */
	public PractitionerRoleImpl() {
		super();
		this.practitionerrole = new PractitionerRole();
		// TODO Auto-generated constructor stub
	}
	
	PractitionerRole practitionerrole;
	
	String identifier, active, period, code, specialty, healthcareService, Telecom, availableTime, notAvailable, availabilityExceptions;
	
	String resourceName;
	
	/**
	 * @return the patient
	 */
	public PractitionerRole getPractitionerRole() {
		return practitionerrole;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPractitionerRole(PractitionerRole practitionerrole) {
		this.practitionerrole = practitionerrole;
	}
	
	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		setValuesFromMap(data);
		setResourceData();
	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.identifier = map.get("practitionerrole.identifier");
		this.active = map.get("practitionerrole.active");
		this.period = map.get("practitionerrole.period");
		this.code = map.get("practitionerrole.code");
		this.specialty = map.get("practitionerrole.specialty");
		//this.healthcareService = map.get("practitionerrole.healthcareService");
		//this.Telecom = map.get("practitionerrole.telecom");
		//this.availableTime = map.get("practitionerrole.availableTime");
		//this.notAvailable = map.get("practitionerrole.notAvailable");
		//this.availabilityExceptions = map.get("practitionerrole.availabilityExceptions");
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResourceData()
	 */
	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		//super.setResourceData();
		
		practitionerrole.addIdentifier()
        .setSystem("http://ns.electronichealth.net.au/id/hi/ihi/1.0")
        .setValue(getId());
		
		practitionerrole.setActive(Boolean.getBoolean(getActive()));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			practitionerrole.getPeriod().setStart(formatter.parse(getPer()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CodeableConcept Code = new CodeableConcept();
		Code.setText(getCode()); 
		practitionerrole.addCode(Code);
		
		CodeableConcept Specialty = new CodeableConcept();
		Specialty.setText(getSpecialty());
		practitionerrole.addSpecialty(Specialty);
		
		
		//practitionerrole.setHealthcareService(theHealthcareService);
		
		/*practitionerrole.addTelecom().setValue(getTelecom());	
		
		List<PractitionerRoleAvailableTimeComponent> theAvailableTimeList = new ArrayList<PractitionerRoleAvailableTimeComponent>();
		PractitionerRoleAvailableTimeComponent theAvailableTime = new PractitionerRoleAvailableTimeComponent();
		theAvailableTime.setAvailableStartTime(availableTime);
		practitionerrole.setAvailableTime(theAvailableTimeList);
		
		practitionerrole.setAvailabilityExceptions(availabilityExceptions);
		*/
	}
	
	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#getResourcedata()
	 */
	@Override
	public void getResourcedata() {
		// TODO Auto-generated method stub
		super.getResourcedata();
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#getResource()
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.practitionerrole;
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResource(org.hl7.fhir.dstu3.model.Resource)
	 */
	@Override
	public void setResource(Resource resource) {
		// TODO Auto-generated method stub
		this.setPractitionerRole((PractitionerRole) resource);
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#getResourceName()
	 */
	@Override
	public String getResourceName() {
		// TODO Auto-generated method stub
		return this.resourceName;
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResourceName(java.lang.String)
	 */
	@Override
	public void setResourceName(String resourceName) {
		// TODO Auto-generated method stub
		this.resourceName=resourceName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return identifier+" "+ active+" "+ period+" "+ code+" "+ specialty+" "+ Telecom+" "+ availableTime+" "+ notAvailable+" "+ availabilityExceptions;
	}	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public void setId() {
		this.id = java.util.UUID.randomUUID().toString();
	}
	
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	
	/**
	 * @return the period
	 */
	public String getPer() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPer(String period) {
		this.period = period;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
	/**
	 * @return the Specialty
	 */
	public String getSpecialty() {
		return specialty;
	}

	/**
	 * @param Speciality the Specialty to set
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	/**
	 * @return the telecom
	 */
	public String getTelecom() {
		return Telecom;
	}

	/**
	 * @param telecom the telecom to set
	 */
	public void setTelecom(String telecom) {
		Telecom = telecom;
	}
	
	
}
