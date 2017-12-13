package com.nttdata.agni.resources.core;


import java.util.HashMap;

import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.MedicationStatement;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;




public class MedicationStatementImpl extends AbstractResource{

	MedicationStatement medicationstatement ;
	
	public MedicationStatementImpl() {
		// TODO Auto-generated constructor stub
		
		this.medicationstatement = new MedicationStatement ();
		
	}

	String identifier, basedon, partof, context, status, category, medication, effective,
    dateAsserted, informationSource, subject, subjectreference, subjectidentifiertype,
    subjectidentifiersystem, subjectidentifiervalue, subjectidentifierperiod, subjectidentifierassigner,
    subjectdisplay, derivedfrom, taken, reasonNotTaken, reasonCode, reasonReference, note, dosage;

	
	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		// TODO Auto-generated method stub
		setValuesFromMap(data);
		setResourceData();
		
	}


	private void setValuesFromMap(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		this.identifier = map.get("MedicationStatement.identifier");
		this.basedon = map.get("MedicationStatement.basedon");
		this.partof = map.get("MedicationStatement.partof");
		this.context = map.get("MedicationStatement.context");
		this.status = map.get("MedicationStatement.status");
		this.category = map.get("MedicationStatement.category");
		this.medication = map.get("MedicationStatement.medication");
		this.effective = map.get("MedicationStatement.effective");
		this.dateAsserted = map.get("MedicationStatement.dateAsserted");
		this.informationSource = map.get("MedicationStatement.informationSource");
		this.subject = map.get("MedicationStatement.subject");
		this.subjectreference = map.get("MedicationStatement.subject.reference");
		this.subjectidentifiertype = map.get("MedicationStatement.subject.identifier.type");
		this.subjectidentifiersystem = map.get("MedicationStatement.subject.identifier.system");
		this.subjectidentifiervalue = map.get("MedicationStatement.subject.identifier.value");
		this.subjectidentifierperiod = map.get("MedicationStatement.subject.identifier.period");
		this.subjectidentifierassigner = map.get("MedicationStatement.subject.identifier.assigner");
		this.subjectdisplay = map.get("MedicationStatement.subject.display");
		this.derivedfrom = map.get("MedicationStatement.derivedFrom");
		this.taken = map.get("MedicationStatement.taken");
		this.reasonNotTaken = map.get("MedicationStatement.reasonNotTaken");
		this.reasonCode = map.get("MedicationStatement.reasonCode");
		this.reasonReference = map.get("MedicationStatement.reasonReference");
		this.note = map.get("MedicationStatement.note");
		this.dosage = map.get("MedicationStatement.dosage");
				
	}


	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		medicationstatement.addIdentifier().setValue(identifier);
		
		
		medicationstatement.setSubject(new Reference().setDisplay(subjectdisplay).setIdentifier(new Identifier().setValue( subjectidentifiervalue)).setReference("Patient/" + subjectreference));
		
	}
    
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.medicationstatement;
	}


	@Override
	public String toString() {
		return identifier + " " + subjectreference + " " + subjectidentifiervalue + " " + subjectdisplay ;
	}
 
}
