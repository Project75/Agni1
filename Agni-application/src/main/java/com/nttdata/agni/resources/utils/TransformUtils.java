/**
 * 
 */
package com.nttdata.agni.resources.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Factory;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.instance.model.api.IBaseResource;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.util.FhirTerser;
import ca.uhn.hl7v2.model.v24.datatype.CE;
import ca.uhn.hl7v2.model.v24.datatype.CWE;

import com.nttdata.agni.resources.core.AbstractResource;

/**
 * @author harendra
 *
 */
public final class TransformUtils {
	public static String resourceToJson(AbstractResource resource){		
		FhirContext ctx = FhirContext.forDstu3();
		if (resource.getResource() != null){
		String json = ctx.newJsonParser().setPrettyPrint(true)
        		.encodeResourceToString(resource.getResource());	
		return json;
		} else{
			//log.error();
			System.out.println("Resource null - "+resource.getResourceName());
			return "ERROR -  NULL Resource -"+resource.toString();	
		}
				
		
	}
	
	public static String toXML(Resource resource) {
		FhirContext ctx = FhirContext.forDstu3();
		String encoded = ctx.newXmlParser().setPrettyPrint(true)
        		//.newXmlParser().setPrettyPrint(true)
                .encodeResourceToString(resource);
		return encoded;
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
	
	//HL7 -  FHIR UTILS PID.5 JOHN^DOE&M ~ JOHNATHAN
	//terser.get(PID.5) = JOHN 5.1
	public static CodeableConcept BuildCodeableConceptFromHL7Field (String HL7fieldValue) {		
		
	HashMap<String,String> fieldmap = GetHL7FieldMapNonRepeating(HL7fieldValue);
		String codingSystem = null,	codingCode = null ,	codingDisplay = null ,codingVersion = null ,CodeableText = null;

		for (Map.Entry<String, String> entry : fieldmap.entrySet()) {
            String key = entry.getKey(); String value = entry.getValue();
            if (key.startsWith("1")){
            	codingCode = value;
            }else if (key.startsWith("2")){
            	codingDisplay = value;
            }else if (key.startsWith("3")){
            	codingSystem = value;
            }else if (key.startsWith("7")){
            	codingVersion = value;
            }else if (key.startsWith("9")){
            	CodeableText = value;
            }
            System.out.println("Map:key:"+key+":value:"+value);
        }
		CodeableConcept theCodeableConcept = BuildCodeableConcept(codingSystem,codingCode,codingDisplay,CodeableText, codingVersion);
		return theCodeableConcept;
	}
	
	
	
	public static CodeableConcept BuildCodeableConceptFromCE (CE ce) {
		String codingSystem = null,	codingCode = null ,	codingDisplay = null ,codingVersion = null ,CodeableText = null;
		codingCode = ce.getCe1_Identifier().getValue();
		codingDisplay = ce.getCe2_Text().getValue();
		codingSystem = ce.getCe3_NameOfCodingSystem().getValue();
		//codingVersion 
		CodeableText=ce.getCe5_AlternateText().getValue();
		return null;
	}
	public static CodeableConcept BuildCodeableConceptFromCXE (CWE cwe) {
		String codingSystem = null,	codingCode = null ,	codingDisplay = null ,codingVersion = null ,CodeableText = null;
	//shud we use tostring or getvalue or getname  or getvalueorempty ---test
		codingCode = cwe.getCwe1_Identifier().getValue();
		codingDisplay = cwe.getCwe2_Text().getValue();
		codingSystem = cwe.getCwe3_NameOfCodingSystem().getValue();

		codingVersion = cwe.getCwe7_CodingSystemVersionID().toString();
		CodeableText=cwe.getCwe9_OriginalText().toString();
		return null;
	}
	
	public static CodeableConcept BuildCodeableConcept (String codingSystem, String codingCode, String codingDisplay, String CodeableText, String codingVersion) {

		CodeableConcept theCodeableConcept = new CodeableConcept() ; 
		theCodeableConcept.addCoding().setSystem(codingSystem).setCode(codingCode)
		.setDisplay(codingDisplay).setVersion(codingVersion);
		theCodeableConcept.setText(CodeableText );

		return theCodeableConcept;
	}

	public static CodeableConcept BuildCodeableConceptDefault (String codingSystem, String codingCode, String codingDisplay) {
		// call Factory by HAPI
		return Factory.newCodeableConcept(codingCode, codingSystem, codingDisplay);
	}

	
	public static  HashMap<String, String> GetHL7FieldMapNonRepeating(String fieldvalue) 
    {         	
		HashMap<String,String> hl7Map = new HashMap<String,String>();
		
		int beginIndex = fieldvalue.indexOf("[");
		if (beginIndex > -1){
			fieldvalue =  fieldvalue.substring(beginIndex+1);
			int endIndex = fieldvalue.indexOf("]");
			if (endIndex > -1){
				fieldvalue = fieldvalue.substring(0, endIndex);
				System.out.println("fieldvalue:"+fieldvalue);
			}
		}
		int i=1;
		
		String[] components = fieldvalue.split("\\^");
		for (String component : components) {
			String index =  Integer.toString(i);
			int j = 1;
		    String[] subcomps = component.split("\\&");
		    if (subcomps.length == 1){
		    	hl7Map.put( index, component) ;
		    	 
		    	System.out.println(index+":component:"+component);
		    	
		    }else{
		    	for (String subcomp : subcomps) {
		    		hl7Map.put(index+"."+Integer.toString(j), subcomp) ;
			    	System.out.println(j+"subcomponent:"+subcomp);
			    	j++;
		    	}
		    }
		    i++;
		}
		
		
        /*StringDelimiter sd; 

        sd = new StringDelimiter(fieldvalue, "^"); 
        while (sd.hasMoreTokens()) 
        { 
            String nt = sd.nextToken();
            
 
            if (nt.length() > 0)
            	hl7Map.put(i+1, nt) ;            
               
            i++; 
        } */
    	return hl7Map;
    }
	
	
}
