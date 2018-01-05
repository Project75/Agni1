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
public class IdentifierUtilOld {
	
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
	public String TYPE = ".identifier";
	public String IDENTIFIER_SYSTEM_HL7 = "http://hl7.org/fhir/v2/0203";
	public String IDENTIFIER_SYSTEM_LOC = "urn:oid:1.2.36.146.595.217.0.1";
	
	public static List<Identifier> getIdentifierList(TransformMap map,String resourceName){
		IdentifierUtilOld identifierUtil =  new IdentifierUtilOld();
		identifierUtil.SetValues( map, resourceName);
		return identifierUtil.identifierList;
	}
	
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
			  typeCodingSystem= Optional.of(IDENTIFIER_SYSTEM_HL7);
			if (!(typeCodingCode.isPresent()))
				typeCodingCode = Optional.of("MR");
			if (!(system.isPresent()))
				system= Optional.of(IDENTIFIER_SYSTEM_LOC);
		
			Coding coding =new Coding();
			List<Coding> codingList =  new ArrayList<Coding>();
			if (typeCodingCode.isPresent())
				coding.setCode(typeCodingCode.get());
			if (typeCodingSystem.isPresent())
				coding.setSystem(typeCodingSystem.get());
			if (typeCodingDisplay.isPresent())
				coding.setDisplay(typeCodingDisplay.get());
				
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
	public void SetPatientMinimalTestArgs(String value){
		
		//SetPatientArgs(typeCodingValue,null,null,null);
		
		SetAllStringArgs("Patient", null,value, IDENTIFIER_SYSTEM_LOC, 
				IDENTIFIER_SYSTEM_HL7, "MR", null,
				"2001-05-06", null, "ABC Organization");
	}
	public void SetPatientArgs(String value,String use, String system,String typeCodingCode,String assigner ) {
	
		system = Optional.ofNullable(system).orElse(IDENTIFIER_SYSTEM_LOC);
		typeCodingCode = Optional.ofNullable(typeCodingCode).orElse("MR");
		
			
		SetAllStringArgs("Patient", null,value, system, 
				IDENTIFIER_SYSTEM_HL7, typeCodingCode, null,
					null, null, assigner);
		
	}
	public void SetValues(TransformMap map,String resourceName){
		//resourceName shudnt be null
		//if (resourceName = null || map = null)
		//		throw new Exception NullMappingException;
		this.TYPE = resourceName+TYPE;
		//Identifier identifier = new Identifier();
		
/*		Set<String> nameKeySet = map.map.keySet()
		          .stream()
		          .filter(s -> s.startsWith(this.TYPE))
		          .collect(Collectors.toSet());
		System.out.println(TYPE+" keyset size= "+nameKeySet.size());
		System.out.println(map.get("patient.identifier"));
		for (String key: nameKeySet){
			System.out.println(key+"=="+map.get(key));
			
		}*/
		
		this.use = Optional.ofNullable(map.get(TYPE+".use"));
		this.typeCodingSystem  = Optional.ofNullable(map.get(TYPE+".type.coding.system"));
		this.typeCodingCode = Optional.ofNullable(map.get(TYPE+".type.coding.code"));
		this.typeCodingDisplay = Optional.ofNullable(map.get(TYPE+".type.coding.display"));
		this.system = Optional.ofNullable(map.get(TYPE+".system"));
		this.value = Optional.ofNullable(map.get(TYPE+".value"));
		this.periodStart = Optional.ofNullable(map.get(TYPE+".period.start"));
		this.periodEnd = Optional.ofNullable(map.get(TYPE+".period.end"));
		this.assignerValue = Optional.ofNullable(map.get(TYPE+".assigner.value"));
		this.assignerReference = Optional.ofNullable(null);
		
		setIdentifier();
		  
	}
}
