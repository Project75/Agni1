package com.nttdata.agni.transfomer;

import java.lang.reflect.Field;

import org.hl7.fhir.dstu3.model.MessageHeader;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IBaseResource;

import ca.uhn.fhir.context.FhirContext;

public class testFHIR {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}
	
	public static void convert(String fhir) {
		// TODO Auto-generated method stub
		FhirContext ctx = FhirContext.forDstu3();
		IBaseResource resource = ctx.newJsonParser().parseResource(fhir);
		String str= resource.getClass().getSimpleName();// .getCanonicalName();
		MessageHeader msgh=(MessageHeader)resource;
		 Field[] fld = resource.getClass().getFields();
		 String arg0 = null;
		//((Object) msgh).getAllPopulatedChildElementsOfType();
		Patient p = new Patient();
		//p.getAllPopulatedChildElementsOfType();
		 for (int i=0;i<fld.length;i++){
			 System.out.println(i+"++++++++"+fld[i]); 
		 }
		 String flds = fld[10].toString();
		String src= msgh.getSource().getEndpoint();
		String json = ctx.newJsonParser().setPrettyPrint(true)
        		.encodeResourceToString(resource);	
		
		System.out.println(src+"==haren====="+str+"======");
	}

}
