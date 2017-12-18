/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Identifier.IdentifierUse;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Patient.ContactComponent;

import org.hl7.fhir.dstu3.model.Resource;

import ca.uhn.fhir.model.primitive.IdDt;
import java.util.stream.Collectors;
/**
 * Copyright NTT Data
 * @author Harendra Pandey
 */
@ToString @Getter @Setter
public class PatientImpl extends AbstractResource{
	/**
	 * 
	 */
	public PatientImpl() {
		super();
		this.patient = new Patient();
		patient.setId(IdDt.newRandomUuid());
		// TODO Auto-generated constructor stub
	}

	//NameType name;
	//IdentifierType identifier;
	String familyName, givenName,  id, gender, DOB, AddressLine, AddressCity, AddressState, AddressPostalCode, AddressCountry, 
	Telecom, MaritalStatus, Deceased, Birth, ContactRel, ContactName, ContactTel, ContactAddress, ContactGender, ContactOrg,
	GeneralPractitioner, Link;

	Patient patient;
	Reference reference;
	String resourceName="patient";
	
	public void setResourceDataFromMapV1(TransformMap map) {
		setResourceDataFromMap(map.getMap());
	}
	
	//@Override
	public void setResourceDataFromMap(HashMap<String, String> map) {
		
		setValuesFromMap(map);
		setResourceData(map);

	}
	
	public List<String> getList(HashMap<String,String> map, String key){
		//"patient.name.family"
		String[] out = null;
		List<String> list =	map.entrySet()
        .stream()
        .filter(entry -> entry.getKey().startsWith(key))
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
        return list;
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
		//this.id=java.util.UUID.randomUUID().toString();
	}
	
	

	/* (non-Javadoc)
	 * @see com.nttdata.agni.resources.core.AbstractResource#setResourceData()
	 */
	//@Override
	public void setResourceData(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		//super.setResourceData();
		map.put("patient.identifier.value","123");
		map.put("patient.identifier.use","official");
		map.put("patient.identifier.type.coding.code","MRN");
		map.put("patient.identifier.type.coding.system","Hospital Codes");
		map.put("patient.identifier.system","Healthcare");
		
		patient.addName().setUse(HumanName.NameUse.OFFICIAL)
		        .addPrefix("Mr").setFamily(familyName).addGiven(givenName);
		
		IdentifierUtil IdentifierUtil = new IdentifierUtil();
		//Set#1 Set identifier Values from map - generic method
		IdentifierUtil.SetValues(map, resourceName);
				
		//Set#2 Set Values from local variables -patient specific method 
		//IdentifierUtil.SetPatientArgs(getId(),"1","2","3","4");
		//IdentifierUtil.SetPatientMinimalArgs(getId());
		
		//get #1 -get identifier Arraylist 
		patient.setIdentifier(IdentifierUtil.getIdentifierList());
		
		//get #2: Get (one) Identifier  (for non-patient resources)
		//nonpatient.setIdentifier(IdentifierUtil.getIdentifier());
		
		//#3 using default api
		/*
		patient.addIdentifier().setUse(IdentifierUse.OFFICIAL)
		        .setSystem("http://ns.electronichealth.net.au/id/hi/ihi/1.0")
		        .setValue(getId());
		        */
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
		}else {gender = Enumerations.AdministrativeGender.UNKNOWN;}
		patient.addContact().addRelationship(new CodeableConcept().setText(getContactRel())).setName(new HumanName().addGiven(getContactName()))
		       .addTelecom(new ContactPoint().setValue(getContactTel())).setAddress(new ContactComponent().getAddress().addLine(getContactAddress()))
		       .setGender(gender)
		       .setOrganization(new Reference().setReference(getContactOrg()));
		       
		patient.addGeneralPractitioner().setReference(getGeneralPractitioner());
		patient.addLink().setId(getLink());
		
		//patient.setId(IdDt.newRandomUuid());
		System.out.println("patmid-"+patient.getId()+"   "+getId());
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
	
	public String toString2() {
		// TODO Auto-generated method stub
		return familyName+" "+ givenName+" "+ id+" "+ gender+" "+ DOB+" "+ AddressLine+" "+ AddressCity+" "+ AddressState+" "+ AddressPostalCode+" "+ AddressCountry+" "+ 
		Telecom+" "+ MaritalStatus+" "+ Deceased+" "+ Birth+" "+ ContactRel+" "+ ContactName+" "+ ContactTel+" "+ ContactAddress+" "+ ContactGender+" "+ ContactOrg+" "+
		GeneralPractitioner+" "+ Link;
	}
	
	
	public void setReference() {
		 Reference ref = new Reference();
		 ref.setIdentifier(patient.getIdentifierFirstRep());		 
		 this.reference = ref;
	}
	
	public Reference getReference() {
		 
		 return this.reference ;
	}
	
	
		
	public void setId() {
		this.id = java.util.UUID.randomUUID().toString();
	}
	
	/**
	 * @param dOB the dOB to set
	 */
	public void setDOB(String DOB) {
		this.DOB = DOB.substring(0, 4) +"-"+ DOB.substring(4, 6) +"-"+ DOB.substring(6, 8);
	}

	
}




