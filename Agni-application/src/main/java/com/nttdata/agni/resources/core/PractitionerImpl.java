/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Practitioner.PractitionerQualificationComponent;
import org.hl7.fhir.dstu3.model.Resource;
import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * @author Neha 
 */

public class PractitionerImpl extends AbstractResource{
	/**
	 * 
	 */
	public PractitionerImpl() {
		super();
		this.practitioner = new Practitioner();
		//resource.setId(IdDt.newRandomUuid());
		// TODO Auto-generated constructor stub
	}
	
	Practitioner practitioner;
	
	String identifier, active, name, Telecom, AddressLine, AddressCity, AddressState, AddressPostalCode, AddressCountry, Gender, DOB, photo, qualification, communication;
	
	String resourceName;
	
	/**
	 * @return the patient
	 */
	public Practitioner getPractitioner() {
		return practitioner;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPractitioner(Practitioner practitioner) {
		this.practitioner = practitioner;
	}
	
	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		setValuesFromMap(data);
		setResourceData();
	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.identifier = map.get("practitioner.identifier");
		//this.active = map.get("practitioner.active");
		this.name = map.get("practitioner.name");
		this.Telecom = map.get("practitioner.telecom");
		this.AddressLine = map.get("practitioner.address.line");
		this.AddressCity = map.get("practitioner.address.city");
		this.AddressState = map.get("practitioner.address.state");
		this.AddressPostalCode = map.get("practitioner.address.postalCode");
		this.AddressCountry = map.get("practitioner.address.country");
		this.Gender = map.get("practitioner.gender");
		this.DOB = map.get("practitioner.birthDate");
		//this.photo = map.get("practitioner.photo");
		//this.qualification = map.get("practitioner.qualification");
		this.communication = map.get("practitioner.communication");
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResourceData()
	 */
	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		//super.setResourceData();
		
		practitioner.addIdentifier()
        .setSystem("http://ns.electronichealth.net.au/id/hi/ihi/1.0")
        .setValue(getId());
		
		//practitioner.getActive();
		
		practitioner.addName().setUse(HumanName.NameUse.OFFICIAL)
		        .addPrefix("Mr").addGiven(name);
		
		practitioner.addTelecom().setValue(getTelecom());	
		
		practitioner.addAddress().addLine(getAddressLine()).setCity(getAddressCity()).setState(getAddressState())
		.setPostalCode(getAddressPostalCode()).setCountry(getAddressCountry());
		
		practitioner.setGender(Enumerations.AdministrativeGender.valueOf(getgender().toUpperCase()));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
		try {
			if (getDOB() != null){
				practitioner.setBirthDate(formatter.parse(getDOB()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}       
		
		CodeableConcept Communication = new CodeableConcept();
		Communication.setText(getCommunication()); 
		practitioner.addCommunication(Communication);
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
		return this.practitioner;
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResource(org.hl7.fhir.dstu3.model.Resource)
	 */
	@Override
	public void setResource(Resource resource) {
		// TODO Auto-generated method stub
		this.setPractitioner((Practitioner) resource);
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
		return identifier+" "+ active+" "+ name+" "+ Telecom+" "+ AddressLine+" "+ AddressCity+" "+ AddressState+" "+ AddressPostalCode+" "+ AddressCountry+" "+ Gender+" "+ DOB+" "+ communication;
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
	 * @return the gender
	 */
	public String getgender() {
		return Gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setgender(String gender) {
		Gender = gender;
	}

	/**
	 * @return the dOB
	 */
	public String getDOB() {
		return DOB;
	}

	/**
	 * @param dOB the dOB to set
	 */
	public void setDOB(String DOB) {
		this.DOB = DOB.substring(0, 4) +"-"+ DOB.substring(4, 6) +"-"+ DOB.substring(6, 8);
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
	
	/**
	 * @return the addressLine
	 */
	public String getAddressLine() {
		return AddressLine;
	}

	/**
	 * @param addressLine the addressLine to set
	 */
	public void setAddressLine(String addressLine) {
		AddressLine = addressLine;
	}

	/**
	 * @return the addressCity
	 */
	public String getAddressCity() {
		return AddressCity;
	}

	/**
	 * @param addressCity the addressCity to set
	 */
	public void setAddressCity(String addressCity) {
		AddressCity = addressCity;
	}

	/**
	 * @return the addressState
	 */
	public String getAddressState() {
		return AddressState;
	}

	/**
	 * @param addressState the addressState to set
	 */
	public void setAddressState(String addressState) {
		AddressState = addressState;
	}

	/**
	 * @return the addressPostalCode
	 */
	public String getAddressPostalCode() {
		return AddressPostalCode;
	}

	/**
	 * @param addressPostalCode the addressPostalCode to set
	 */
	public void setAddressPostalCode(String addressPostalCode) {
		AddressPostalCode = addressPostalCode;
	}

	/**
	 * @return the addressCountry
	 */
	public String getAddressCountry() {
		return AddressCountry;
	}

	/**
	 * @param addressCountry the addressCountry to set
	 */
	public void setAddressCountry(String addressCountry) {
		AddressCountry = addressCountry;
	}
	
	/**
	 * @return the communication
	 */
	public String getCommunication() {
		return communication;
	}

	/**
	 * @param communication the communication to set
	 */
	public void setCommunication(String comm) {
		communication = comm;
	}
	
}
