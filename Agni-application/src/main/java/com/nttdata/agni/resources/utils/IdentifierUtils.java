/**
 * 
 */
package com.nttdata.agni.resources.utils;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Identifier.IdentifierUse;

/**
 * @author harendra
 *
 */

public class IdentifierUtils extends TypeUtils{
	
	//Optional<Identifier> identifier;
	
	List<Identifier> identifierList = new ArrayList<Identifier>();
	String[] keys = {"identifier","use","type.coding.system","type.coding.code","type.coding.display",
			"system","value","period.start","period.end","assigner.value","assigner.display"};
	public String TYPE = "identifier";

	
	public static List<Identifier> getIdentifierList(TransformMap map,String resourceName){
		if (resourceName != null){
			IdentifierUtils identifierUtil =  new IdentifierUtils();
			identifierUtil.setIdentifier(map, resourceName.toLowerCase());
			return identifierUtil.identifierList;
		}else
			return null;
		
	}
	
	public static Identifier getFirstIdentifier(TransformMap map,String resourceName){
		//return getIdentifierList(map,resourceName).get(0);
		List<Identifier> identifierList= getIdentifierList(map, resourceName);
		if (!identifierList.isEmpty())
			return identifierList.get(0);
		else 
			return null;
	}
	
	

	
	public void setIdentifier(TransformMap map, String resourceName) {
		Identifier identifier = null;
		Map<String,String> kmap= createKeysMap(resourceName, keys);
		
		int size = map.getCount(kmap.get("value"));
		if (size==0) size = map.getCount(kmap.get("identifier"));
		//System.out.println("");
		for(int i=0;i<size;i++){
			identifier = new Identifier();
			
			String use = Optional.ofNullable(map.get(kmap.get("use"),i)).orElse("default");
			
			switch (use.toLowerCase()){
			case "official":
				identifier.setUse(IdentifierUse.OFFICIAL);
			case "usual":
				identifier.setUse(IdentifierUse.USUAL);
			default:
				//identifier.setUse(IdentifierUse.USUAL);
			}	
			String typeCodingSystem = map.get(kmap.get("type.coding.system"),i);
			String typeCodingCode = map.get(kmap.get("type.coding.code"),i);
			String system = map.get(kmap.get("system"),i);
			
			if (resourceName.equalsIgnoreCase("patient")){
				typeCodingSystem= Optional.ofNullable(typeCodingSystem).orElse(IDENTIFIER_SYSTEM_HL7);
				typeCodingCode = Optional.ofNullable(typeCodingCode).orElse("MR");
				//system = Optional.ofNullable(system).orElse(IDENTIFIER_SYSTEM_LOC);
			}
			
			Coding coding =new Coding();
			List<Coding> codingList =  new ArrayList<Coding>();
			coding.setCode(typeCodingCode);
			coding.setSystem(typeCodingSystem);
			coding.setDisplay(map.get(kmap.get("type.coding.display"),i));
				
			Optional<Coding> codingOpt = Optional.of(coding);
			if (codingOpt.isPresent())
				codingList.add(codingOpt.get());
			
		CodeableConcept theCodeableConcept = new CodeableConcept();
		if (codingOpt.isPresent())
			theCodeableConcept.setCoding(codingList);
			
		
		identifier.setType(theCodeableConcept);
		
		identifier.setSystem(system);
		String value = Optional.ofNullable(map.get(kmap.get("value"),i)).orElse(map.get(kmap.get("identifier"),i));
		
		identifier.setValue(value);
		Period pidPeriod = buildPeriod(map, kmap, i );
		
		
	
		Optional<Period> pidPeriodOpt = Optional.of(pidPeriod);
		if (pidPeriodOpt.isPresent())
		identifier.setPeriod(pidPeriodOpt.get());
		//Set Reference, shud create new Reference object
		identifier.getAssigner().setReference(map.get(kmap.get("assigner.value"),i) ); 
		identifier.getAssigner().setDisplay(map.get(kmap.get("assigner.display"),i) ); 
			
		identifierList.add(identifier);
		}
	}
	
}
	
	
	
	

