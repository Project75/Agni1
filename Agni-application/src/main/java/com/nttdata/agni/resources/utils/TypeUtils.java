package com.nttdata.agni.resources.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Period;

import ca.uhn.fhir.util.DateUtils;


public abstract class TypeUtils {
	public static String IDENTIFIER_SYSTEM_HL7 = "http://hl7.org/fhir/v2/0203";
	public String IDENTIFIER_SYSTEM_LOC = "urn:oid:1.2.36.146.595.217.0.1";
	//key,value pairs
	//value, {resourceName=patient}.identifier.value
	public Map<String, String> createKeysMap(String resourceName, String[] keys)
    {
       Map<String,String> map = new HashMap<String,String>();
       for (int i=0;i<keys.length;i++){
    	   if (i==0)
    		  map.put(keys[0],resourceName+"."+keys[0]) ;
    	   else
    		   map.put(keys[i], resourceName+"."+keys[0]+"."+keys[i]);
       }
        return map;
    }

	public static Period buildPeriod(TransformMap map,Map<String, String> kmap, int index ){
		Period pidPeriod = new Period();
		
		//pidPeriod.setStart setEnd
		String startDate = map.get(kmap.get("period.start"),index);
		String endDate = map.get(kmap.get("period.end"),index);
		if (startDate != null)
			pidPeriod.setStart(DateUtils.parseDate(startDate,new String[]{"yyyyMMdd","yyyy-MM-dd"}));
		if (endDate != null)
			pidPeriod.setEnd(DateUtils.parseDate(endDate,new String[]{"yyyyMMdd","yyyy-MM-dd"}));
	
			return pidPeriod;
	}
	public static CodeableConcept BuildCodeableConcept(TransformMap map,String prefix, int index ){
		Map<String, String> kmap = createKeysMap(prefix,"CodeableConcept");
		String codingSystem = map.get(kmap.get("coding.system"),index);
		String codingCode = map.get(kmap.get("coding.code"),index);
		String codingDisplay = map.get(kmap.get("coding.display"),index);
		String codingVersion = map.get(kmap.get("coding.version"),index);
		String codeText = map.get(kmap.get("text"),index);
		CodeableConcept theCodeableConcept = BuildCodeableConcept(codingSystem,codingCode,codingDisplay,codingVersion,codeText);
		return theCodeableConcept;
	}
	public static CodeableConcept BuildCodeableConcept (String codingSystem, String codingCode, String codingDisplay, String CodeableText, String codingVersion) {

		CodeableConcept theCodeableConcept = new CodeableConcept() ; 
		theCodeableConcept.addCoding().setSystem(codingSystem).setCode(codingCode)
		.setDisplay(codingDisplay).setVersion(codingVersion);
		theCodeableConcept.setText(CodeableText );

		return theCodeableConcept;
	}
	
	public static Coding BuildCoding (TransformMap map,String prefix, int index ){
		Map<String, String> kmap = createKeysMap(prefix,"Coding");
		String codingSystem = map.get(kmap.get("coding.system"),index);
		String codingCode = map.get(kmap.get("coding.code"),index);
		String codingDisplay = map.get(kmap.get("coding.display"),index);
		String codingVersion = map.get(kmap.get("coding.version"),index);
		Coding coding =new Coding();
		coding.setCode(codingCode).setSystem(codingSystem)
			  .setDisplay(codingDisplay).setVersion(codingVersion);
			
		return coding;
	}
	public static Map<String, String> createKeysMap(String fieldName, String dataType)
    {
       Map<String,String> map = new HashMap<String,String>();
       ArrayList<String> keys = null;
       if (dataType.equalsIgnoreCase("coding")){
    	    keys = new ArrayList<String>(
    			    Arrays.asList("coding.system","coding.code","coding.display","coding.version"));
       }
       if (dataType.equalsIgnoreCase("CodeableConcept")){
   	    keys = new ArrayList<String>(
   			    Arrays.asList("coding.system","coding.code","coding.display","coding.version","text"));
      }
       for (int i=0;i<keys.size();i++){
    	   
    		   map.put(keys.get(i), fieldName+"."+keys.get(i));
       }
        return map;
    } 
}
