/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Optional;

import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Patient.ContactComponent;
import org.hl7.fhir.dstu3.model.Resource;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * @author Harendra Pandey
 */
public class PatientImpl extends AbstractResource{
	/**
	 * 
	 */
	public PatientImpl() {
		super();
		this.patient = new Patient();
		// TODO Auto-generated constructor stub
	}

	String familyName, givenName, id, gender, DOB, AddressLine, AddressCity, AddressState, AddressPostalCode, AddressCountry, 
	Telecom, MaritalStatus, Deceased, Birth, ContactRel, ContactName, ContactTel, ContactAddress, ContactGender, ContactOrg,
	GeneralPractitioner, Link;

	Patient patient;
	
	String resourceName;
	
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
	
	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.familyName = map.get("patient.name.family");
		this.givenName = map.get("patient.name.given");
		this.id = map.get("patient.identifier");
		this.gender = map.get("patient.gender");
		this.DOB = map.get("patient.birthdate");
		this.AddressLine = map.get("patient.address.line");
		this.AddressCity = map.get("patient.address.city");
		this.AddressState = map.get("patient.address.state");
		this.AddressPostalCode = map.get("patient.address.postalCode");
		this.AddressCountry = map.get("patient.address.country");
		this.Telecom = map.get("patient.telecom.value");
		this.MaritalStatus = map.get("patient.maritalStatus");
		this.Deceased = map.get("patient.deceased");
		this.Birth = map.get("patient.multipleBirth");
		this.ContactRel = map.get("patient.contact.relationship");
		this.ContactName = map.get("patient.contact.name");
		this.ContactTel = map.get("patient.contact.telecom");
		this.ContactAddress = map.get("patient.contact.address");
		this.ContactGender = map.get("patient.contact.gender");
		this.ContactOrg = map.get("patient.contact.organization");
		this.GeneralPractitioner = map.get("patient.generalPractitioner");
		this.Link= map.get("patient.link.other");;
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResourceData()
	 */
	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		//super.setResourceData();
		
		patient.addName().setUse(HumanName.NameUse.OFFICIAL)
		        .addPrefix("Mr").setFamily(getFamilyName()).addGiven(givenName);
		patient.addIdentifier()
		        .setSystem("http://ns.electronichealth.net.au/id/hi/ihi/1.0")
		        .setValue(getId());
		patient.setGender(Enumerations.AdministrativeGender.valueOf(getGender().toUpperCase()));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
		try {
			if (getDOB() != null){
				patient.setBirthDate(formatter.parse(getDOB()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
			//patient.setBirthDate(null);
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
		
		AdministrativeGender gender = null;
		if (getContactGender()!=null){	
		 gender = //Optional.ofNullable().orElse();
			Enumerations.AdministrativeGender.valueOf(getContactGender().toUpperCase());					
		}else {gender = Enumerations.AdministrativeGender.NULL;}
		patient.addContact().addRelationship(new CodeableConcept().setText(getContactRel())).setName(new HumanName().addGiven(getContactName()))
		       .addTelecom(new ContactPoint().setValue(getContactTel())).setAddress(new ContactComponent().getAddress().addLine(getContactAddress()))
		       .setGender(gender)
		       .setOrganization(new Reference().setReference(getContactOrg()));
		       
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
	 * @see com.nttdata.agni.resources.core.AbstractResource#getResource()
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.patient;
	}

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResource(org.hl7.fhir.dstu3.model.Resource)
	 */
	@Override
	public void setResource(Resource resource) {
		// TODO Auto-generated method stub
		this.setPatient((Patient) resource);
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
		return familyName+" "+ givenName+" "+ id+" "+ gender+" "+ DOB+" "+ AddressLine+" "+ AddressCity+" "+ AddressState+" "+ AddressPostalCode+" "+ AddressCountry+" "+ 
		Telecom+" "+ MaritalStatus+" "+ Deceased+" "+ Birth+" "+ ContactRel+" "+ ContactName+" "+ ContactTel+" "+ ContactAddress+" "+ ContactGender+" "+ ContactOrg+" "+
		GeneralPractitioner+" "+ Link;
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
	
	public void setId() {
		this.id = java.util.UUID.randomUUID().toString();
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
