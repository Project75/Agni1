/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Location;
import org.hl7.fhir.dstu3.model.Location.LocationMode;
import org.hl7.fhir.dstu3.model.Location.LocationPositionComponent;
import org.hl7.fhir.dstu3.model.Location.LocationStatus;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.Immunization.ImmunizationStatus;

import ca.uhn.fhir.model.primitive.IdDt;

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
public class LocationImpl extends AbstractResource{
	/**
	 * Field for storing the FHIR Resource. Need to import from org.hl7.fhir.dstu3.model.*
	 * e.g Patient patient; or Patient resource;
	 */
	Location location;
	/**
	 * Create Local fields to store data from HL7. 
	 * e.g. familyName, givenName
	 */
	private String identifier;
	private String status;
	private String operationalStatus;
	private String name;
	private String alias;
	private String description;
	private String mode;
	private String type;
	private String telecom;
	private String address;
	private String physicalType;
	private String position;
	private String positionLongitude;
	private String positionLatitude;
	private String positionAltitude;
	private String managingOrganization;
	private String partOf;
	private String endpoint;
	
	String resourceName="Location";
	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public LocationImpl() {
		
		// TODO Auto-generated constructor stub
		this.location =  new Location();
		location.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		setValuesFromMap(data);
		setResourceData();

	}
	

	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.identifier = map.get("location.identifier");
		this.status = map.get("location.status");
		this.operationalStatus = map.get("location.operationalStatus");
		this.name = map.get("location.name");
		this.alias = map.get("location.alias");
		this.description = map.get("location.description");
		this.mode = map.get("location.mode");
		this.type = map.get("location.type");
		this.telecom = map.get("location.telecom");
		this.address = map.get("location.address");
		this.physicalType = map.get("location.physicalType");
		this.position = map.get("location.position");
		this.positionLongitude = map.get("location.positionLongitude");
		this.positionLatitude = map.get("location.positionLatitude");
		this.positionAltitude = map.get("location.positionAltitude");
		this.managingOrganization = map.get("location.managingOrganization");
		this.partOf = map.get("location.partOf");
		this.endpoint = map.get("location.endpoint");


	}
	
	@Override
	public void setResourceData() {
		location.addIdentifier().setValue(this.identifier)
								.setType(new CodeableConcept().setText("Location"));
		//Active Inactive NULL
		location.setStatus(LocationStatus.ACTIVE );
		//map from hl7 code table v2 bed status
		location.setOperationalStatus(new Coding().setCode(operationalStatus));
		location.setName(name);
		location.addAlias(alias);
		location.setDescription(description);
		location.setMode(LocationMode.INSTANCE);  
		location.setType(new CodeableConcept().setText(type));
		location.addTelecom().setValue(telecom);
		location.setAddress(new Address().setText(address)); 
		location.setPhysicalType(new CodeableConcept().setText(physicalType));
		//LocationPositionComponent new LocationPositionComponent().setLongitude(null).setLatitude();
		//shud check below shud nbe numbers regex
		//if (positionLongitude.matches(regex))
		/*location.setPosition(new LocationPositionComponent()
				.setLongitude(Long.parseLong(positionLongitude)).
				setLatitude(Long.parseLong(positionLatitude)).
				setAltitude(Long.parseLong(positionAltitude)));
		*/
		location.setManagingOrganization(new Reference().setReference(managingOrganization)); 
		//managingOrganization
		location.setPartOf(new Reference().setReference(partOf));
		location.addEndpoint().setReference(endpoint);
		
}

	/*
	 * Return the FHIR resource object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.location;
	}
	

	
}
