package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Arrays;
import com.nttdata.agni.resources.utils.TransformMap;

import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.Address.AddressType;
import org.hl7.fhir.dstu3.model.Address.AddressUse;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ContactDetail;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Quantity;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointSystem;


import org.hl7.fhir.dstu3.model.DataElement;
import org.hl7.fhir.dstu3.model.Enumerations.PublicationStatus;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.dstu3.model.UsageContext;

 
public class DataElementImpl extends AbstractResource {

	DataElement dataelement ;
	
	public DataElementImpl() {
		// TODO Auto-generated constructor stub
		this.dataelement = new DataElement ();
	}
	String resourceName = "DataElement" ;
	
	String[] varibleArray = {"url","identifiervalue", "version","status","experimental", "date", "publisher", "name","title" ,"contactname", "contacttelecomvalue", "usecontextcodesystem", "usecontextcodeversion", "usecontextcodecode", "usecontextcodedisplay","usecontextcodeuserselected",
			"usecontextvaluecodebleconceptcodingsystem", "usecontextvaluecodebleconcepttext" , "usecontextvaluequantityunit" , "usecontextvaluequantitysystem" , "usecontextvaluequantitycode"};
		
	
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.dataelement;
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
		setResourceData();
	}

	private void setValuesFromMap(TransformMap map) {
		// TODO Auto-generated method stub
		
	for ( int i=0; i < this.varibleArray.length ; i++)
	{
		this.varibleArray[i]= map.get(getResourceName()+"."+this.varibleArray[i]); // Similar to this.identifier = map.get("DataElement.identifier");


	}
	
	}
	
	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		
				
				dataelement.addIdentifier().setValue(this.varibleArray[1]);
				
				dataelement.setStatus(PublicationStatus.UNKNOWN); // how to map to this.varibleArray[3] ??
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				
				try {
					if (this.varibleArray[5] != null){
						dataelement.setDate(formatter.parse(this.varibleArray[5]));
					}
				} catch (ParseException e) {
					e.printStackTrace();
					//patient.setBirthDate(null);
				}
				
				dataelement.setPublisher(this.varibleArray[6]);
				dataelement.setTitle(this.varibleArray[8]);
				dataelement.addContact(new ContactDetail().setName(this.varibleArray[9]).addTelecom(new ContactPoint().setValue(this.varibleArray[10])));
				dataelement.addUseContext(new UsageContext().setCode(new Coding().setCode(this.varibleArray[13]))); //  map UsageContect().setValue pending??
		
	}


	@Override
	public String toString() {
		return varibleArray[1] + " " + varibleArray[5] + " " + varibleArray[6] + " " + varibleArray[8] + " " + varibleArray[9] + " " + varibleArray[10] + " " + varibleArray[13] ;
	}
	

}
