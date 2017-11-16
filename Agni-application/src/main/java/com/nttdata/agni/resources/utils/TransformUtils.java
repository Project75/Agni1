/**
 * 
 */
package com.nttdata.agni.resources.utils;

import org.hl7.fhir.dstu3.model.Resource;

import ca.uhn.fhir.context.FhirContext;

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
}
