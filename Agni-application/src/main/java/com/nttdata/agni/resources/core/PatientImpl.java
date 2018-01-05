/**
 * 
 */
package com.nttdata.agni.resources.core;

import com.nttdata.agni.resources.utils.TransformMap;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;

import com.nttdata.agni.resources.utils.AddressUtils;
import com.nttdata.agni.resources.utils.IdentifierUtils;
import com.nttdata.agni.resources.utils.NameUtils;

import org.hl7.fhir.dstu3.model.Resource;

import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.util.DateUtils;

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
		setResourceDataFromMap(map);
	}
	
	//@Override
	public void setResourceDataFromMap(TransformMap map) {
		
		setValuesFromMap(map);
		setResourceData(map);

	}
	
	/*public List<String> getList(TransformMap map, String key){
		//"patient.name.family"
		String[] out = null;
		List<String> list =	map.entrySet()
        .stream()
        .filter(entry -> entry.getKey().startsWith(key))
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
        return list;
	}*/
	
	public void setValuesFromMap(TransformMap map) {
		

		 
		this.familyName = map.get("patient.name.family");
		this.givenName = map.get("patient.name.given");
		this.id = map.get("patient.identifier");
		this.gender = map.get("patient.gender");
		this.DOB = map.get("patient.birthdate");
		this.AddressLine = map.get("patient.address.line");
		this.AddressCity = map.get("patient.address.city");
		this.AddressState = map.get("patient.address.state");
		this.AddressPostalCode = map.get("patient.address.postalcode");
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
	
	@Override
	public void setResourceData(TransformMap map) {
		
		patient.setName(NameUtils.getNames(map, resourceName));
		
		patient.setIdentifier(IdentifierUtils.getIdentifierList(map, resourceName));

		if (getGender() != null)
		patient.setGender(Enumerations.AdministrativeGender.valueOf(getGender().toUpperCase()));
		
		if (DOB != null){
			patient.setBirthDate(DateUtils.parseDate(DOB,new String[]{"yyyyMMdd","yyyy-MM-dd"}));
			
		} 
		/*
		patient.addAddress().addLine(getAddressLine()).setCity(getAddressCity()).setState(getAddressState())
				.setPostalCode(getAddressPostalCode()).setCountry(getAddressCountry());
		*/
		patient.setAddress(AddressUtils.getAddress(map, resourceName));
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
		}//else {gender = Enumerations.AdministrativeGender.UNKNOWN;}
		patient.addContact().addRelationship(new CodeableConcept().setText(getContactRel())).setName(new HumanName().addGiven(getContactName()))
		       .addTelecom(new ContactPoint().setValue(getContactTel()))
		       .setAddress(AddressUtils.getFirstAddress(map, resourceName+".contact"))
		       //.setAddress(new ContactComponent().getAddress().addLine(getContactAddress()))
		       .setGender(gender)
		       .setOrganization(new Reference().setReference(getContactOrg()));
		       
		patient.addGeneralPractitioner().setReference(getGeneralPractitioner());
		patient.addLink().setId(getLink());
		
		this.setReference();
		//patient.setId(IdDt.newRandomUuid());
		System.out.println("pat id-"+patient.getId()+"   "+getId());
	}
	

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


	
	public void setReference() {
		 Reference ref = new Reference();
		 ref.setIdentifier(patient.getIdentifierFirstRep());		 
		 this.reference = ref;
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




