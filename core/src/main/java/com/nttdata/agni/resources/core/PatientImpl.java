/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Patient.ContactComponent;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * @author Harendra Pandey
 */
public class PatientImpl extends AbstractResource{
	String familyName, givenName, id, gender, DOB, AddressLine, AddressCity, AddressState, AddressPostalCode, AddressCountry, 
	Telecom, MaritalStatus, Deceased, Birth, ContactRel, ContactName, ContactTel, ContactAddress, ContactGender, ContactOrg,
	GeneralPractitioner, Link;

	Patient patient;
	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResourceData()
	 */
	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		super.setResourceData();
		
		patient.addName().setUse(HumanName.NameUse.OFFICIAL)
		        .addPrefix("Mr").setFamily(getFamilyName()).addGiven(givenName);
		patient.addIdentifier()
		        .setSystem("http://ns.electronichealth.net.au/id/hi/ihi/1.0")
		        .setValue(getId());
		patient.setGender(Enumerations.AdministrativeGender.valueOf(getGender().toUpperCase()));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
		try {
			patient.setBirthDate(formatter.parse(getDOB()));
		} catch (ParseException e) {
			e.printStackTrace();
		}       
		patient.addAddress().addLine(getAddressLine()).setCity(getAddressCity()).setState(getAddressState())
				.setPostalCode(getAddressPostalCode()).setCountry(getAddressCountry());
		patient.addTelecom().setValue(getTelecom());
		
		CodeableConcept MaritalStatus = new CodeableConcept();
		MaritalStatus.setText(getMaritalStatus()); 
		patient.setMaritalStatus(MaritalStatus); 
		        
		BooleanType Deceased = new BooleanType(Boolean.valueOf(getDeceased()));
		patient.setDeceased(Deceased);
		
		BooleanType Birth = new BooleanType(Boolean.valueOf(getBirth()));
		patient.setMultipleBirth(Birth);
		
		patient.addContact().addRelationship(new CodeableConcept().setText(getContactRel())).setName(new HumanName().addGiven(getContactName()))
		       .addTelecom(new ContactPoint().setValue(getContactTel())).setAddress(new ContactComponent().getAddress().addLine(getContactAddress()))
		       .setGender(Enumerations.AdministrativeGender.valueOf(getContactGender().toUpperCase())).setOrganization(new Reference().setReference(getContactOrg()));
		       
		patient.addGeneralPractitioner().setReference(getGeneralPractitioner());
		patient.addLink().setId(getLink());
		
		patient.setId(IdDt.newRandomUuid());

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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
	
	
	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
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

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return MaritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		MaritalStatus = maritalStatus;
	}

	/**
	 * @return the deceased
	 */
	public String getDeceased() {
		return Deceased;
	}

	/**
	 * @param deceased the deceased to set
	 */
	public void setDeceased(String deceased) {
		Deceased = deceased;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return Birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		Birth = birth;
	}

	/**
	 * @return the contactRel
	 */
	public String getContactRel() {
		return ContactRel;
	}

	/**
	 * @param contactRel the contactRel to set
	 */
	public void setContactRel(String contactRel) {
		ContactRel = contactRel;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return ContactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		ContactName = contactName;
	}

	/**
	 * @return the contactTel
	 */
	public String getContactTel() {
		return ContactTel;
	}

	/**
	 * @param contactTel the contactTel to set
	 */
	public void setContactTel(String contactTel) {
		ContactTel = contactTel;
	}

	/**
	 * @return the contactAddress
	 */
	public String getContactAddress() {
		return ContactAddress;
	}

	/**
	 * @param contactAddress the contactAddress to set
	 */
	public void setContactAddress(String contactAddress) {
		ContactAddress = contactAddress;
	}

	/**
	 * @return the contactGender
	 */
	public String getContactGender() {
		return ContactGender;
	}

	/**
	 * @param contactGender the contactGender to set
	 */
	public void setContactGender(String contactGender) {
		ContactGender = contactGender;
	}

	/**
	 * @return the contactOrg
	 */
	public String getContactOrg() {
		return ContactOrg;
	}

	/**
	 * @param contactOrg the contactOrg to set
	 */
	public void setContactOrg(String contactOrg) {
		ContactOrg = contactOrg;
	}

	/**
	 * @return the generalPractitioner
	 */
	public String getGeneralPractitioner() {
		return GeneralPractitioner;
	}

	/**
	 * @param generalPractitioner the generalPractitioner to set
	 */
	public void setGeneralPractitioner(String generalPractitioner) {
		GeneralPractitioner = generalPractitioner;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return Link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		Link = link;
	}


	
}
