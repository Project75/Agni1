/**
 * 
 */
package com.nttdata.agni.resources.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Factory;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Identifier.IdentifierUse;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author harendra
 *
 */
@Getter @ToString @NoArgsConstructor @AllArgsConstructor
public class IdentifierUtil {
	
	//Optional<Identifier> identifier;
	
	List<Identifier> identifierList = new ArrayList<Identifier>();
	private Optional<String> use;
	private Optional<String> typeCodingSystem ;
	private Optional<String> typeCodingCode;
	private Optional<String> typeCodingDisplay;
	private Optional<String> system;
	private Optional<String>  value;
	private Optional<String>  periodStart;
	private Optional<String>  periodEnd;
	private Optional<String>  assignerValue;
	private Optional<Reference> assignerReference;
	private Optional<String>  parentResource;
	String lookup = ".identifier";
	
	public Identifier getIdentifier(){
		return identifierList.get(0);
	}
	public void setIdentifier() {
		Identifier identifier = new Identifier();
		String option = use.orElse("");
		switch (option){
		case "official":
			identifier.setUse(IdentifierUse.OFFICIAL);
		case "usual":
			identifier.setUse(IdentifierUse.USUAL);
		default:
			identifier.setUse(IdentifierUse.USUAL);
		}		
		
		
			if (!(typeCodingSystem.isPresent()))
			  typeCodingSystem= Optional.of("http://hl7.org/fhir/v2/0203");
			if (!(typeCodingCode.isPresent()))
				typeCodingCode = Optional.of("MR");
		
			Coding coding =new Coding();
			List<Coding> codingList =  new ArrayList<Coding>();
			if (typeCodingCode.isPresent())
				coding.setCode(typeCodingCode.get());
			if (typeCodingSystem.isPresent())
				coding.setSystem(typeCodingSystem.get());
			if (typeCodingDisplay.isPresent())
				coding.setCode(typeCodingDisplay.get());
				
			Optional<Coding> codingOpt = Optional.of(coding);
			if (codingOpt.isPresent())
				codingList.add(codingOpt.get());
			
		CodeableConcept theCodeableConcept = new CodeableConcept();
		if (codingOpt.isPresent())
			theCodeableConcept.setCoding(codingList);
			
		
		identifier.setType(theCodeableConcept);
		if (system.isPresent())
			identifier.setSystem(system.get());
		if (value.isPresent())
			identifier.setValue(value.get());
		Period pidPeriod = new Period();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmm");
		
		if (periodStart.isPresent()){
			try {
				pidPeriod.setStart(dateFormatter.parse(periodStart.get()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}					
	
		if (periodEnd.isPresent()){
			try {
				pidPeriod.setEnd(dateFormatter.parse(periodEnd.get()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		Optional<Period> pidPeriodOpt = Optional.of(pidPeriod);
		if (pidPeriodOpt.isPresent())
		identifier.setPeriod(pidPeriodOpt.get());
	
		if (assignerReference.isPresent())
			identifier.setAssigner(assignerReference.get() ); 
		else if (assignerValue.isPresent())
			identifier.getAssigner().setDisplay(assignerValue.get() ); 
		identifierList.add(identifier);
	}
	


	//Implementation Setter methods for diff variations
	public void SetAllStringArgs(String parentResource, String use,String value,String system, 
				String typeCodingSystem, String typeCodingCode, String typeCodingDisplay, 
				String periodStart, String periodEnd, String assignerValue  ) {
		this.use = Optional.ofNullable(use);
		this.typeCodingSystem  = Optional.ofNullable(typeCodingSystem);
		this.typeCodingCode = Optional.ofNullable(typeCodingCode);
		this.typeCodingDisplay = Optional.ofNullable(typeCodingDisplay);
		this.system = Optional.ofNullable(system);
		this.value = Optional.ofNullable(value);
		this.periodStart = Optional.ofNullable(periodStart);
		this.periodEnd = Optional.ofNullable(periodEnd);
		this.assignerValue = Optional.ofNullable(assignerValue);
		this.parentResource = Optional.ofNullable(parentResource);
		this.assignerReference = Optional.ofNullable(null);
		
		setIdentifier();
	}
	public void SetPatientMinimalArgs(String value){
		
		//SetPatientArgs(typeCodingValue,null,null,null);
		
		SetAllStringArgs("Patient", null,value, "urn:oid:1.2.36.146.595.217.0.1", 
				"http://hl7.org/fhir/v2/0203", "MR", null,
				"2001-05-06", null, "ABC Organization");
	}
	public void SetPatientArgs(String value,String use, String system,String typeCodingCode,String assigner ) {
	
		system = Optional.ofNullable(system).orElse("urn:oid:1.2.36.146.595.217.0.1");
		typeCodingCode = Optional.ofNullable(typeCodingCode).orElse("MR");
		
			
		SetAllStringArgs("Patient", null,value, system, 
					"http://hl7.org/fhir/v2/0203", typeCodingCode, null,
					"2001-05-06", null, assigner);
		
	}
	public void SetValues(HashMap<String,String> map,String resourceName){
		//parentResource shudnt be null
		this.lookup = resourceName+lookup;
		//Identifier identifier = new Identifier();
		
		Set<String> nameKeySet = map.keySet()
		          .stream()
		          .filter(s -> s.startsWith(this.lookup))
		          .collect(Collectors.toSet());
		System.out.println(lookup+" keyset size= "+nameKeySet.size());
		System.out.println(map.get("patient.identifier"));
		for (String key: nameKeySet){
			System.out.println(key+"=="+map.get(key));
			
		}
		
		this.use = Optional.ofNullable(map.get(lookup+".use"));
		this.typeCodingSystem  = Optional.ofNullable(map.get(lookup+".type.coding.system"));
		this.typeCodingCode = Optional.ofNullable(map.get(lookup+".type.coding.code"));
		this.typeCodingDisplay = Optional.ofNullable(map.get(lookup+".type.coding.display"));
		this.system = Optional.ofNullable(map.get(lookup+".system"));
		this.value = Optional.ofNullable(map.get(lookup+".value"));
		this.periodStart = Optional.ofNullable(map.get(lookup+".period.start"));
		this.periodEnd = Optional.ofNullable(map.get(lookup+".period.end"));
		this.assignerValue = Optional.ofNullable(map.get(lookup+".assigner.value"));
		this.assignerReference = Optional.ofNullable(null);
		
		setIdentifier();
		  
	}
}
