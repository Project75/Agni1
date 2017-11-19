/**
 * 
 */
package com.nttdata.agni.resources.utils;

import java.util.List;

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
		System.out.println();
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
}
