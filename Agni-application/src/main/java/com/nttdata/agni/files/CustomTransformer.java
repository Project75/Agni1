package com.nttdata.agni.files;

import java.util.ArrayList;
import java.util.HashMap;

import org.hl7.fhir.dstu3.model.Resource;
import org.springframework.stereotype.Service;

import com.nttdata.agni.resources.core.AbstractResource;
import com.nttdata.agni.resources.core.BundleImpl;
import com.nttdata.agni.resources.core.PatientImpl;
import com.nttdata.agni.resources.core.ResourceFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.EncodingNotSupportedException;

/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */
@Service
public class CustomTransformer {

	public String transform(String mapname, String payload) {
		// TODO Auto-generated method stub
		String fhir=null;
		HashMap<String, String> map = null;
		ArrayList<AbstractResource> resourceList = new ArrayList<AbstractResource>();
		PatientFlatParser patientParser = new PatientFlatParser();
    	ArrayList<Patient> patients = patientParser.parsePatient(payload);
    	for (Patient patient:patients){
    		map = MapToFHIR(patient);
    		AbstractResource resource= createFHIRResource(map,"patient");
    		resourceList.add(resource);
    	}
    	fhir = buildFHIRBundle(resourceList);
    	return fhir;
	}

	private String buildFHIRBundle(ArrayList<AbstractResource> resourceList) {
		// TODO Auto-generated method stub
		BundleImpl bundle = (BundleImpl)ResourceFactory.getResource("bundle");
    	System.out.println("Creating Bundle : ");
    	bundle.addResourcesFromList(resourceList);
		String json = bundle.toJson();//TransformUtils.resourceToJson(bundle);
		return json;
	}

	private HashMap<String, String> MapToFHIR(Patient patient) {
		// TODO Auto-generated method stub
		//PatientImpl patFhir = new PatientImpl();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("patient.name.family",patient.family);
		map.put("patient.name.given",patient.given);
		map.put("patient.identifier",patient.id);
		map.put("patient.gender",patient.gender);
		map.put("patient.birthdate",patient.dob);
		//map.put("patient.address.line",patient.id);
		map.put("patient.address.city",patient.city);
		map.put("patient.address.state",patient.state);
		//map.put("patient.address.postalCode",patient.id);
		//map.put("patient.address.country",patient.id);
		//map.put("patient.telecom.value",patient.id);
		map.put("patient.maritalStatus",patient.maritalstatus);
		return map;
				
	}
	private AbstractResource createFHIRResource(HashMap<String, String> dataMap,String resourceName) {
		// TODO Auto-generated method stub
    	AbstractResource resource =  null;
    	if (resourceName !=null){
        	resource = ResourceFactory.getResource(resourceName);
        	if (resource !=null){	        		
        		resource.setResourceDataFromMap(dataMap);
        		//resourceList.add(resource);
        	}

     	}
     		
     	return resource;

	}
	
	

}
