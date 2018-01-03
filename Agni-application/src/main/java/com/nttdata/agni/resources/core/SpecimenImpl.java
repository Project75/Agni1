package com.nttdata.agni.resources.core;


import java.util.Date;
import com.nttdata.agni.resources.utils.TransformMap;

import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.Specimen;




public class SpecimenImpl extends AbstractResource{

	Specimen specimen ;
	
	public SpecimenImpl() {
		// TODO Auto-generated constructor stub
		
		this.specimen = new Specimen ();
		
	}
	

String identifier, accessionIdentifier, status, type, subjectidentifier,subjectreference,subjectdisplay ,parentidentifier,parentreference,
parentdisplay,requestidentifier, requestreference, requestdisplay;


@Override
public void setResourceDataFromMap(TransformMap data) {
	// TODO Auto-generated method stub
	setValuesFromMap(data);
	setResourceData();
	
}

private void setValuesFromMap(TransformMap map) {
	// TODO Auto-generated method stub
	this.identifier = map.get("Specimen.identifier");
	this.accessionIdentifier = map.get("Specimen.accessionIdentifier");
	this.status = map.get("Specimen.status");
	this.type = map.get("Specimen.type");
	this.subjectidentifier = map.get("Specimen.subjectidentifier");
	this.subjectreference = map.get("Specimen.subjectreference");
	this.subjectdisplay = map.get("Specimen.subjectdisplay");
	this.parentidentifier = map.get("Specimen.parentidentifier");
	this.parentreference = map.get("Specimen.parentreference");
	this.parentdisplay = map.get("Specimen.parentdisplay");
	this.requestidentifier = map.get("Specimen.requestidentifier");
	this.requestreference = map.get("Specimen.requestreference");
	this.requestdisplay = map.get("Specimen.requestdisplay");
	

}

@Override
public void setResourceData() {
	// TODO Auto-generated method stub
	specimen.addIdentifier().setValue(identifier);
	specimen.setAccessionIdentifier(new Identifier().setValue(accessionIdentifier));
	specimen.setSubject(new Reference().setDisplay(subjectdisplay).setIdentifier(new Identifier().setValue(subjectidentifier)).setReference(subjectreference));


    
 
}
public Resource getResource() {
	// TODO Auto-generated method stub
	return this.specimen;
}


@Override
public String toString() {
	return identifier ;
}

}