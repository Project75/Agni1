package com.nttdata.agni.resources.core;

import java.time.Period;
import java.util.Arrays;
import com.nttdata.agni.resources.utils.TransformMap;

import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.Address.AddressType;
import org.hl7.fhir.dstu3.model.Address.AddressUse;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointSystem;


import org.hl7.fhir.dstu3.model.Organization;

import org.hl7.fhir.dstu3.model.Resource;

 
public class OrganizationImpl extends AbstractResource {

	Organization organization ;
	
	public OrganizationImpl() {
		// TODO Auto-generated constructor stub
		this.organization = new Organization ();
	}
	String resourceName = "Organization" ;
	
	String[] varibleArray = {"identifier", "active","type","name", "alias", "telecomsystem", "telecomvalue", "addresstype", "addresstext", "addresscity", "addressstate", "addresspostalcode", "addresscountry","addresscountryperiodstart",
			"contactfamily", "contactgiven", "contactprefix", "contactsuffix", "contactperiodstart" , "contacttelecomvalue", "contactaddressuse", "contactaddressline", "contactaddresscity", "contactaddressdistrict", "contactaddressstate",
			"contactaddresspostalcode", "contactaddresscountry" , "contactaddressperiodstart", "contactaddressperiodend", "endpoint"};
		
	
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.organization;
	}
	
	
	@Override
	public String getResourceName() {
		// TODO Auto-generated method stub
		return this.resourceName;
		
	}
		
	public String[] getVaribleArray() {
		return this.varibleArray;
	}
		

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		// TODO Auto-generated method stub
		setValuesFromMap(data );
		setResourceData(data);
	}

	private void setValuesFromMap(TransformMap map) {
		// TODO Auto-generated method stub
		
	for ( int i=0; i < this.varibleArray.length ; i++)
	{
		this.varibleArray[i]= map.get(getResourceName()+"."+this.varibleArray[i]); // Similar to this.identifier = map.get("Organization.identifier");


	}
//	System.out.println( this.varibleArray[i] );		
	}
	
	@Override
	public void setResourceData(TransformMap map) {
		// TODO Auto-generated method stub
		
		organization.addIdentifier().setValue(this.varibleArray[0]);
		organization.addTelecom().setSystem(ContactPointSystem.PHONE).setValue(this.varibleArray[6]);
		
		organization.addAddress().setType(AddressType.POSTAL).setText(this.varibleArray[8]).setCity(this.varibleArray[9]).setState(this.varibleArray[10]).setPostalCode(this.varibleArray[11]).setCountry(this.varibleArray[12]);
				//organization.addAddress().setPeriod(Period)  In doubt.
		organization.addContact().setAddress(new Address().setUse(AddressUse.HOME).addLine(this.varibleArray[21]).setCity(this.varibleArray[22]).setDistrict(this.varibleArray[23]).setState(this.varibleArray[24]).setPostalCode(this.varibleArray[25]).setCountry(this.varibleArray[26])).setName(new HumanName().setFamily(this.varibleArray[14]).addGiven(this.varibleArray[15]).addPrefix(this.varibleArray[16]).addSuffix(this.varibleArray[17])).addTelecom().setValue(this.varibleArray[19]);
				
		
	}


	@Override
	public String toString() {
		return varibleArray[0] + " " + varibleArray[6] + " " + varibleArray[8] + " " + varibleArray[9] + " " + varibleArray[10] + " " + varibleArray[11] + " " + varibleArray[12] + " " + varibleArray[21] + " " + varibleArray[22] + " " + varibleArray[23] + " " + varibleArray[24] + " " + varibleArray[25]+ " " + varibleArray[14] + " " + varibleArray[15] + " " + varibleArray[16] + " " + varibleArray[17] + " " + varibleArray[19];
	}
	

}
