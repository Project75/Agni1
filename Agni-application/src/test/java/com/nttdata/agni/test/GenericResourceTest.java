package com.nttdata.agni.test;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.resources.core.*;
import com.nttdata.agni.transfomer.*;

import org.junit.Before;




public class GenericResourceTest {

    private HL7Transformer hl7Transformer;
    @Before
    public void setUp() {
    	  	hl7Transformer = new HL7Transformer();
    }

    public String testResource(String resource) {
    	String toStringResource = transform(resource);
    	System.out.println(resource+" "+toStringResource);        
        return toStringResource;
    }

    public  String transform(String resourceName) {
    	String payload = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID||001|199||JOHN^DOE||19751027|Female|||street 53^^PHOENIX^AZ^85013^US||(111)222-3333||N|W|||001|||||false||||||false|||||PID.35\r"
                + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|Father||||||ORGANIZATION||Male\r"
                +"PV1||O|5501^0113^02|U|00060292||00276^DELBARE^POL^^DR.|00276^DELBARE^POL^^DR.||1901|||N|01|||||0161782703\r"
                +"PD1|1|2|3|4|5|6|7|8|9|10\r"
                +"OBX|1|TX|3|4|5|6|7|8|9|10|FINAL||13|20060221061809|15|16|17|18|19|20|21|22|23|24|25|26\r"
                +"OBR|1|2156286|A140875|MRSHLR-C^MR Shoulder right wo/contrast|5||||9|10|11|12|13||15|16||18|19|20|21|20060221061809|23|24|25|26\r"
                +"NTE|1|2|3|4\r";  
    	return transform(resourceName, payload);
    	
    }
    public  String transform(String resourceName,String payload) {
    	
    	HashMap<String, String> dataMap = null;
    	    	  
        try {        	        	
        	
			Message hapiMsg = hl7Transformer.getHL7FromPayload(payload);
 
        	HashMap<String, String> tempMap = getMappings();
        	dataMap = hl7Transformer.getHL7ValuesMap(hapiMsg, tempMap);
        	
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        AbstractResource res = createFHIRResource(dataMap,resourceName);
        //print json 
        System.out.println(getResourceAsJson(res));
        return getResourceAsString(res);
        
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
	private String getResourceAsJson(AbstractResource resource) {
		FhirContext ctx = FhirContext.forDstu3();
		String encoded = ctx.newJsonParser().setPrettyPrint(true)
        		.encodeResourceToString(resource.getResource());
		return encoded;
	}
	private String getResourceAsString(AbstractResource resource) {
		return resource.toString();
	}
	private HashMap<String, String> getMappings() {
		// TODO Auto-generated method stub
		HashMap<String, String> mappingMap =new HashMap<String, String>();
        List<MappingList> mappingList = mockMappings();
        
        
        if (mappingList.size() > 0) {
        	System.out.println("MappingList size is "+mappingList.size());
        	for (MappingList entity : mappingList) {	    		
        		mappingMap.put(entity.getFHIR(), entity.getHL7());	    		
	        }
        } 
    	return mappingMap;
		
	}
	private List<MappingList> mockMappings() {
    	List<MappingList> mapping =  new ArrayList<MappingList>();
    	
    	mapping.add(new MappingList("patient.identifier","PID-3-1"));
    	mapping.add(new MappingList("patient.name.family","PID-5-1"));
    	mapping.add(new MappingList("patient.name.given","PID-5-2"));
    	mapping.add(new MappingList("patient.gender","PID-8-1"));
    	mapping.add(new MappingList("patient.birthDate","PID-7-1"));
    	mapping.add(new MappingList("patient.address.line","PID-11-1"));
    	mapping.add(new MappingList("patient.address.city","PID-11-3"));
    	mapping.add(new MappingList("patient.address.state","PID-11-4"));
    	mapping.add(new MappingList("patient.address.postalCode","PID-11-5"));
    	mapping.add(new MappingList("patient.address.country","PID-11-6"));
    	mapping.add(new MappingList("patient.telecom.value","PID-13-1"));
    	mapping.add(new MappingList("patient.maritalStatus","PID-16-1"));
    	mapping.add(new MappingList("patient.deceased","PID-30"));
    	mapping.add(new MappingList("patient.multipleBirth","PID-24"));
    	mapping.add(new MappingList("patient.photo","OBX-5"));
    	mapping.add(new MappingList("encounter.identifier","PV1-19-1"));
    	mapping.add(new MappingList("encounter.status","PV1-2-1"));
    	mapping.add(new MappingList("encounter.location.display","PV1-3-1"));
    	mapping.add(new MappingList("encounter.participant.individual.display","PV1-7-2"));
    	mapping.add(new MappingList("encounter.participant.individual.reference","PV1-7-1"));
    	mapping.add(new MappingList("messageheader.event.code","MSH-9-2"));
    	mapping.add(new MappingList("messageheader.destination.name","MSH-5-1"));
    	mapping.add(new MappingList("messageheader.source.name","MSH-3-1"));
    	mapping.add(new MappingList("messageheader.timestamp","MSH-7-1"));
    	mapping.add(new MappingList("observation.bodySite","OBX-20-1"));
    	mapping.add(new MappingList("observation.method","OBX-17-1"));
    	mapping.add(new MappingList("observation.device","OBX-17-1"));
    	mapping.add(new MappingList("observation.referenceRange.low","OBX-7"));
    	mapping.add(new MappingList("observation.referenceRange.high","OBX-7"));
    	mapping.add(new MappingList("observation.referenceRange.type","OBX-10"));
    	mapping.add(new MappingList("observation.referenceRange.appliesTo","OBX-10"));
    	mapping.add(new MappingList("observation.referenceRange.text","OBX-7"));
    	mapping.add(new MappingList("observation.component.code","OBX-3-1"));
    	mapping.add(new MappingList("observation.component.value","OBX-2"));
    	mapping.add(new MappingList("observation.component.interpretation","OBX-8"));
    	mapping.add(new MappingList("observation.component.referenceRange","OBX-7"));
    	mapping.add(new MappingList("observation.effective","OBX-14"));
    	mapping.add(new MappingList("observation.issued","OBX-14"));
    	mapping.add(new MappingList("observation.status","OBX-11"));
    	mapping.add(new MappingList("observation.code","OBX-13"));
    	mapping.add(new MappingList("observation.performer","OBX-15"));
    	mapping.add(new MappingList("observation.identifier","OBX-21"));
    	mapping.add(new MappingList("observation.interpretation","OBX-8"));
    	mapping.add(new MappingList("observation.code","OBX-3"));
    		return mapping;
    }
}
