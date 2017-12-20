package com.nttdata.agni.resources.core;


import java.util.Date;
import java.util.HashMap;

import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.VisionPrescription;




public class VisionPrescriptionImpl extends AbstractResource{

	VisionPrescription visionprescription ;
	
	public VisionPrescriptionImpl() {
		// TODO Auto-generated constructor stub
		
		this.visionprescription = new VisionPrescription ();
		
	}
	

String identifier, status, patientreference, patientidentifier, patientdisplay, encounterreference, encounteridentifier, encounterdisplay,
dateWritten, prescriberreference, prescriberidentifier, prescriberdisplay, reasoncodableconcept,
reasonreference, reasonidentifier, reasondisplay, dispenseproduct;


@Override
public void setResourceDataFromMap(HashMap<String, String> data) {
	// TODO Auto-generated method stub
	setValuesFromMap(data);
	setResourceData();
	
}

private void setValuesFromMap(HashMap<String, String> map) {
	// TODO Auto-generated method stub
	this.identifier = map.get("VisionPrescription.identifier");
	this.status = map.get("VisionPrescription.status");
	this.patientreference = map.get("VisionPrescription.patient.reference");
	this.patientidentifier = map.get("VisionPrescription.patient.identifier");
	this.patientdisplay = map.get("VisionPrescription.patient.display");
	this.encounterreference = map.get("VisionPrescription.encounter.reference");
	this.encounteridentifier = map.get("VisionPrescription.encounter.identifier");
	this.encounterdisplay = map.get("VisionPrescription.encounter.display");
	this.dateWritten = map.get("VisionPrescription.dateWritten");
	this.prescriberreference = map.get("VisionPrescription.prescriber.reference");
	this.prescriberidentifier = map.get("VisionPrescription.prescriber.identifier");
	this.prescriberdisplay = map.get("VisionPrescription.prescriber.display");
	this.reasoncodableconcept = map.get("VisionPrescription.reason.reasoncodableconcept");
	this.reasonreference = map.get("VisionPrescription.reason.reference");
	this.reasonidentifier = map.get("VisionPrescription.reason.identifier");
	this.reasondisplay = map.get("VisionPrescription.reason.display");
	this.dispenseproduct = map.get("VisionPrescription.dispenseproduct");

}

@Override
public void setResourceData() {
	// TODO Auto-generated method stub
	visionprescription.addIdentifier().setValue(identifier);
    visionprescription.setPatient(new Reference().setDisplay(patientdisplay).setIdentifier(new Identifier().setValue(patientidentifier)).setReference(patientreference));
    visionprescription.setEncounter(new Reference().setDisplay(encounterdisplay).setIdentifier(new Identifier().setValue(encounteridentifier)).setReference(encounterreference) );
   // visionprescription.setPrescriber(new Reference().setDisplay(prescriberdisplay).setIdentifier(new Identifier().setValue(prescriberidentifier)).setReference(prescriberreference));
  //  visionprescription.setReason(new Reference().setDisplay(reasondisplay).setIdentifier(new Identifier().setValue(reasonidentifier)).setReference(reasonreference));
   // visionprescription.setDateWritten(new Date());
}
public Resource getResource() {
	// TODO Auto-generated method stub
	return this.visionprescription;
}


@Override
public String toString() {
	return identifier + " " + patientreference + " " + encounterreference + " " + dateWritten  + " " + prescriberreference  + " " + reasonreference ;
}

}