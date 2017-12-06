package com.nttdata.agni.resources.utils;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Reference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class FHIRUtils {
	/**
	 * Build FhIRe reference from Patient
	 * @param Hashmap with inbound data mapped to fhir fields as key
	 * @return FHIR Reference
	 */
	public static Reference buildPatientReference(HashMap<String,String> map){
		String patientIDType = map.get("patient.identifier.type"),
				 patientIDSystem = map.get("patient.identifier.system"),
				 patientIDValue = map.get("patient.identifier.value"),
				 patientIDPeriodStart = map.get("patient.identifier.period.start"),
				 patientIDPeriodEnd = map.get("patient.identifier.period.end"),
				 patientIDAssigner= map.get("patient.identifier.assigner");
		Reference patRef = createReference(patientIDValue,patientIDType, patientIDSystem, patientIDPeriodStart, patientIDPeriodEnd, patientIDAssigner);
		
		return patRef;
	}
	/**
	 * Build FhIRe reference from Patient
	 * @param patient patient resource
	 * @return FHIR Reference
	 */
	public static Reference buildPatientReference(Patient patient) {
		//Build and set patient reference
		Reference patientReference = new Reference();
		String name = patient.getName().toString();
		
		String patientUri;
		
		String id= patient.getId();
		
		patientUri =  "Patient/" + id;
		patientReference.setReference(patientUri);
		patientReference.setDisplay(name);
		patientReference.setId(id);
		return patientReference;
	}
	public static Reference createReference(String referenceIDValue, String referenceIDType, String referenceIDSystem, String referenceIDPeriodStart, String referenceIDPeriodEnd, String referenceIDAssigner){
		
		Reference reference = new Reference();
		
		//reference.reference
		reference.setReference("");

		//reference.identifier
		Identifier theEncounterIdentifier = new Identifier();
		
		theEncounterIdentifier.setValue(referenceIDValue);
		theEncounterIdentifier.setType(new CodeableConcept().setText(referenceIDType));
		theEncounterIdentifier.setSystem(referenceIDSystem);
		
			Period referenceIDPeriod = new Period();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmm");
			try {
				if (referenceIDPeriodStart != null){
					referenceIDPeriod.setStart(dateFormatter.parse(referenceIDPeriodStart));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			try {
				if (referenceIDPeriodEnd != null){
					referenceIDPeriod.setEnd(dateFormatter.parse(referenceIDPeriodEnd));
				}
			} catch (ParseException e) {
				e.printStackTrace();
		}
			theEncounterIdentifier.setPeriod(referenceIDPeriod);
		
			Reference referenceAssigner = new Reference();
			referenceAssigner.setId(referenceIDAssigner);
		
			theEncounterIdentifier.setAssigner(referenceAssigner);
		
		reference.setIdentifier(theEncounterIdentifier);
		
		//reference.display
		reference.setDisplay("");
		return reference;
		
	}
	

	
}
