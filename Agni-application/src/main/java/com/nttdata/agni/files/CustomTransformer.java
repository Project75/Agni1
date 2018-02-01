package com.nttdata.agni.files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hl7.fhir.dstu3.model.Resource;
import org.springframework.stereotype.Service;

import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.resources.core.AbstractResource;
import com.nttdata.agni.resources.core.BundleImpl;
import com.nttdata.agni.resources.core.PatientImpl;
import com.nttdata.agni.resources.utils.ApiClient;
import com.nttdata.agni.resources.utils.ResourceFactory;
import com.nttdata.agni.resources.utils.TransformMap;
import com.nttdata.agni.resources.utils.TransformUtils;
import com.nttdata.agni.transfomer.AbstractTransformer;

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
public class CustomTransformer extends AbstractTransformer{

	public String transform(String mapname, String payload) {
		// TODO Auto-generated method stub
		String fhir=null;
		TransformMap map = null;
		ArrayList<AbstractResource> resourceList = new ArrayList<AbstractResource>();
		PatientFlatParser patientParser = new PatientFlatParser();
    	ArrayList<Patient> patients = patientParser.parsePatient(payload);
    	for (Patient patient:patients){
    		map = MapToFHIR(patient);
    		//map = getCustomMappings(mapname);
    		AbstractResource resource= createFHIRResource(map,"patient");
    		resourceList.add(resource);
    	}
    	fhir = buildFHIRBundle(resourceList);
    	return fhir;
	}

	public String etl(String mapname, String payload) {
		// TODO Auto-generated method stub
		String status="Resources Posted to Server";
		TransformMap map = null;
		ArrayList<AbstractResource> resourceList = new ArrayList<AbstractResource>();
		PatientFlatParser patientParser = new PatientFlatParser();
    	ArrayList<Patient> patients = patientParser.parsePatient(payload);
    	for (Patient patient:patients){
    		map = MapToFHIR(patient);
    		//map = getCustomMappings(mapname);
    		AbstractResource resource= createFHIRResource(map,"patient");
    		 postToServer(resource);
    	}
    	//fhir = buildFHIRBundle(resourceList);
    	return status;
	}

	public void postToServer(AbstractResource resource) {
		
		String payload=  TransformUtils.resourceToJson(resource.getResource());
		Boolean status = ApiClient.postResource(payload, resource.getResourceName());
	    	
}

	private String buildFHIRBundle(ArrayList<AbstractResource> resourceList) {
		// TODO Auto-generated method stub
		BundleImpl bundle = (BundleImpl)ResourceFactory.getResource("bundle");
    	System.out.println("Creating Bundle : ");
    	bundle.addResourcesFromList(resourceList);
		String json = bundle.toJson();//TransformUtils.resourceToJson(bundle);
		return json;
	}

	private TransformMap MapToFHIR(Patient patient) {
		// TODO Auto-generated method stub
		//PatientImpl patFhir = new PatientImpl();
		TransformMap map = new TransformMap();
		map.put("patient.name.family",patient.family);
		map.put("patient.name.given",patient.given);
		map.put("patient.identifier",patient.id);
		map.put("patient.gender",patient.gender);
		map.put("patient.birthdate",patient.dob);
		map.put("patient.telecom.value",patient.phone);
		map.put("patient.address.city",patient.city);
		map.put("patient.address.state",patient.state);
		//map.put("patient.address.postalCode",patient.id);
		//map.put("patient.address.country",patient.id);
		//map.put("patient.telecom.value",patient.id);
		map.put("patient.maritalStatus",patient.maritalstatus);
		return map;
				
	}
	private TransformMap createFhirKeysToCustomValuesMap(String mapname) {
		// TODO Auto-generated method stub
		TransformMap map = new TransformMap();
	    //   List<MappingList> mappingList = mappingService.findByMapname(mapname);
		//
	    List<MappingList> mappingList = mockMappings();

		return map;
	}
	private TransformMap getCustomMappings(String mapname) {
		// TODO Auto-generated method stub
		TransformMap map = new TransformMap();
	    //   List<MappingList> mappingList = mappingService.findByMapname(mapname);
		//
	    List<MappingList> mappingList = mockMappings();

		return map;
	}
	public List<MappingList> mockMappings() {
    	List<MappingList> mapping =  new ArrayList<MappingList>();
		mapping.add(new MappingList("patient.identifier","patient.id"));
		mapping.add(new MappingList("patient.name.family","patient.family"));
		mapping.add(new MappingList("patient.name.given","PID-5-2"));
		mapping.add(new MappingList("patient.gender","PID-8-1"));
		mapping.add(new MappingList("patient.birthdate","PID-7"));
		mapping.add(new MappingList("patient.address.city","PID-11-3"));
		mapping.add(new MappingList("patient.address.state","PID-11-4"));
		mapping.add(new MappingList("patient.telecom.value","PID-13-1"));
		mapping.add(new MappingList("patient.maritalStatus","PID-16-1"));
		return mapping;

	}
	private AbstractResource createFHIRResource(TransformMap dataMap,String resourceName) {
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
