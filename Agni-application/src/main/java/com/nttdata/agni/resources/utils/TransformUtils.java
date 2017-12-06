/**
 * 
 */
package com.nttdata.agni.resources.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.instance.model.api.IBaseResource;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.util.FhirTerser;

import com.nttdata.agni.resources.core.AbstractResource;

/**
 * @author harendra
 *
 */
public final class TransformUtils {
	public static String resourceToJson(AbstractResource resource){		
		FhirContext ctx = FhirContext.forDstu3();
		String json = ctx.newJsonParser().setPrettyPrint(true)
        		.encodeResourceToString(resource.getResource());	
		
		return json;
		
	}
	
	public static String toXML(Resource resource) {
		FhirContext ctx = FhirContext.forDstu3();
		String encoded = ctx.newXmlParser().setPrettyPrint(true)
        		//.newXmlParser().setPrettyPrint(true)
                .encodeResourceToString(resource);
		return encoded;
	}
	
	public static CodeableConcept ConvertToCodeableConcept (String HL7fieldName, String HL7fieldValue) {		
		
		HashMap<String,String> fieldmap = GetHL7FieldMapNonRepeating(HL7fieldName,HL7fieldValue);
		String codingSystem = null,	codingCode = null ,	codingDisplay = null ,codingVersion = null ,CodeableText = null;

		for (Map.Entry<String, String> entry : fieldmap.entrySet()) {
            String key = entry.getKey(); String value = entry.getValue();
            if (key.contains(HL7fieldName+".1")){
            	codingCode = value;
            }else if (key.contains(HL7fieldName+".2")){
            	codingDisplay = value;
            }else if (key.contains(HL7fieldName+".3")){
            	codingSystem = value;
            }else if (key.contains(HL7fieldName+".7")){
            	codingVersion = value;
            }else if (key.contains(HL7fieldName+".9")){
            	CodeableText = value;
            }
            System.out.println("Map:key:"+key+":value:"+value);
        }
		CodeableConcept theCodeableConcept = ConvertToCodeableConcept(codingSystem,codingCode,codingDisplay,CodeableText, codingVersion);
		return theCodeableConcept;
	}
	
	public static CodeableConcept ConvertToCodeableConcept (String codingSystem, String codingCode, String codingDisplay, String CodeableText, String codingVersion) {
		Coding theCoding = new Coding();
		theCoding.setSystem(codingSystem);
		theCoding.setCode(codingCode);
		theCoding.setDisplay(codingDisplay);
		theCoding.setVersion(codingVersion);
		CodeableConcept theCodeableConcept = new CodeableConcept() ; 
		
		theCodeableConcept.setText(CodeableText );
		theCodeableConcept.addCoding(theCoding);
		return theCodeableConcept;
	}
	
	public static void getFHIRValue(IBaseResource nextResource) {
		FhirContext ctx = FhirContext.forDstu3();
		FhirTerser terser = ctx.newTerser();
		//IBaseResource nextResource = null;
		List<StringDt> allStringData = terser.getAllPopulatedChildElementsOfType(nextResource, StringDt.class);
		if (allStringData.size() > 0) {        	
	    	for (StringDt entity : allStringData) {	    		
	    		System.out.println(entity.getValueAsString());
	        }
    	}
	}
	
	public static  HashMap<String,String> GetHL7FieldMapNonRepeating(String HL7fieldName, String fieldvalue) 
    {         	
		HashMap<String,String> hl7Map = new HashMap<String,String>();
		hl7Map = new HashMap<String,String>();
		int i=0;   
        StringDelimiter sd; 

        sd = new StringDelimiter(fieldvalue, "^"); 
        while (sd.hasMoreTokens()) 
        { 
            String nt = sd.nextToken();
            String level2FieldTag;
			
            	level2FieldTag = HL7fieldName + "." + (i+1);
 
            if (nt.length() > 0)
            	hl7Map.put(level2FieldTag, nt) ;            
               
            i++; 
        } 
    	return hl7Map;
    }
	
	
}
