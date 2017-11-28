package com.nttdata.agni.test;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;













import ca.uhn.fhir.context.FhirContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;





import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.resources.core.*;
import com.nttdata.agni.resources.utils.TransformUtils;
import com.nttdata.agni.transfomer.*;

import org.junit.Before;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;







public class GenericResourceTest {

    private HL7Transformer hl7Transformer;
    @Before
    public void setUp() {
    	  	hl7Transformer = new HL7Transformer();
    	  	
    	  	Logger Logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ca.uhn.fhir");
    	  			//("ca.uhn.fhir.context.ModelScanner");
    	    Logger.setLevel(Level.WARN);
    	     Logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ca.uhn.hl7v2");
    	    Logger.setLevel(Level.WARN); 
    }

    public String testResource(String resource) {
    	String toStringResource = transform(resource);
    	System.out.println(resource+" "+toStringResource);        
        return toStringResource;
    }

    public  String transform(String resourceName) {
    	String payload = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID||001|199^^^EHR^MR||JOHN^DOE||19751027|Female|||street 53^^PHOENIX^AZ^85013^US||(111)222-3333||N|W|||001|||||false||||||false|||||PID.35\r"
                + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|Father||||||ORGANIZATION||Male\r"
                +"PV1||O|5501^0113^02|U|00060292||00276^DELBARE^POL^^DR.|00276^DELBARE^POL^^DR.||1901|||N|01|||||0161782703^^^EHR^ACCT\r"
                +"PD1|1|2|3|4|5|6|7|8|9|10\r"
                +"OBX|1|TX|3|4|5|6|7|8|9|10|FINAL||13|20060221061809|15|16|17|18|19|20|21|22|23|24|25|26\r"
                +"OBR|1|2156286|A140875|MRSHLR-C^MR Shoulder right wo/contrast|5||||9|10|11|12|13||15|16||18|19|20|21|20060221061809|23|24|25|26\r"
                +"NTE|1|2|3-comment|4\r"
                +"SCH|01928374|57483920|||||||1|hr|1^^^20160515133000^20160515134500|||||||||1173^MATTHEWS^JAMES^A|||||BOOKED\r"
    			+"AIP|1||1069^GOOD^ALLAN^B|RADIOLOGIST||20160515134500|15|min|45|min||ACCEPTED|\r"
                +"AIS|1||73610^X-RAY ANKLE 3+ VW^CPT|20160515134500|15|min|45|min||\r"
                +"ARQ|19940047^SCH001|||||047^Referral||NORMAL|||201605150800^201605151700|2|||0045^Contact^Carrie^S^^^||||3372^Person^Entered||||\r"
                +"RXA|0|1|201312211100||48^Hib^CVX|2|mL^milliliters^UCUM||00^Administered^NIP001||||||K7164HI||PMC^sanofipasteur^MVX||||A|\r"
                +"RXR|IMLA^LeftArmIntramuscular^HL70162|LA^Left arm^HL70163|\r";                ;
    	return transform(resourceName, payload);
    	
    }
    public  String transform(String resourceName,String payload) {
    	System.out.println("***************************");
    	System.out.println("Processing for "+resourceName);
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
		return TransformUtils.resourceToJson(resource);
	}
	private String getResourceAsString(AbstractResource resource) {
		return resource.toString();
	}
	private HashMap<String, String> getMappings() {
		// TODO Auto-generated method stub
		HashMap<String, String> mappingMap =new HashMap<String, String>();
        List<MappingList> mappingList = mockMappings();
        
        
        if (mappingList.size() > 0) {
        	//System.out.println("MappingList size is "+mappingList.size());
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
    	
    	// Appointment mappings
    	mapping.add(new MappingList("appointment.identifier","SCH-1"));
    	mapping.add(new MappingList("appointment.status","SCH-25"));
    	mapping.add(new MappingList("appointment.appointmentType","ARQ-7"));
    	mapping.add(new MappingList("appointment.reason","AIS-3-2"));
    	mapping.add(new MappingList("appointment.priority","ARQ-12"));
    	mapping.add(new MappingList("appointment.description","NTE-3"));
    	mapping.add(new MappingList("appointment.start","ARQ-11-1"));
    	mapping.add(new MappingList("appointment.end","SCH-11-5"));
    	mapping.add(new MappingList("appointment.comment","NTE-3"));
    	mapping.add(new MappingList("appointment.participant.type","AIP-4"));
    	mapping.add(new MappingList("appointment.participant.actor","PID-3-1"));
    	mapping.add(new MappingList("appointment.participant.status","AIP-12"));
    	mapping.add(new MappingList("appointment.requestedPeriod","ARQ-11-1"));
    	
    	//Immunization mappings
    	mapping.add(new MappingList("Immunization.vaccineCode.coding.system","RXA-5-3"));
    	mapping.add(new MappingList("Immunization.vaccineCode.coding.code","RXA-5-1"));
    	mapping.add(new MappingList("Immunization.vaccineCode.coding.display","RXA-5-2"));
    	mapping.add(new MappingList("Immunization.patient.identifier.type","PID-3-5"));
    	mapping.add(new MappingList("Immunization.patient.identifier.system","PID-3-4-2"));
    	mapping.add(new MappingList("Immunization.patient.identifier.value","PID-3-1"));
    	mapping.add(new MappingList("Immunization.patient.identifier.period.start","PID-3-7"));
    	mapping.add(new MappingList("Immunization.patient.identifier.period.end","PID-3-8"));
    	mapping.add(new MappingList("Immunization.patient.identifier.assigner","PID-3-4-1"));
    	mapping.add(new MappingList("Immunization.encounter.identifier.type","PV1-19-5"));
    	mapping.add(new MappingList("Immunization.encounter.identifier.system","PV1-19-4-2"));
    	mapping.add(new MappingList("Immunization.encounter.identifier.value","PV1-19-1"));
    	mapping.add(new MappingList("Immunization.encounter.identifier.period.start","PV1-19-7"));
    	mapping.add(new MappingList("Immunization.encounter.identifier.period.end","PV1-19-8"));
    	mapping.add(new MappingList("Immunization.encounter.identifier.assigner","PV1-19-4-1"));
    	mapping.add(new MappingList("Immunization.date","RXA-3"));
    	mapping.add(new MappingList("Immunization.reportOrigin.coding.system","RXA-9-3"));
    	mapping.add(new MappingList("Immunization.reportOrigin.coding.code","RXA-9-1"));
    	mapping.add(new MappingList("Immunization.reportOrigin.coding.display","RXA-9-2"));
    	mapping.add(new MappingList("Immunization.location","RXA-27"));
    	mapping.add(new MappingList("Immunization.manufacturer.identifier.system","RXA-17-3"));
    	mapping.add(new MappingList("Immunization.manufacturer.identifier.value","RXA-17-1"));
    	mapping.add(new MappingList("Immunization.lotNumber","RXA-15"));
    	mapping.add(new MappingList("Immunization.expirationDate","RXA-16"));
    	mapping.add(new MappingList("Immunization.site.coding.system","RXR-2-3"));
    	mapping.add(new MappingList("Immunization.site.coding.code","RXR-2-1"));
    	mapping.add(new MappingList("Immunization.site.coding.display","RXR-2-2"));
    	mapping.add(new MappingList("Immunization.route.coding.system","RXR-1-3"));
    	mapping.add(new MappingList("Immunization.route.coding.code","RXR-1-1"));
    	mapping.add(new MappingList("Immunization.route.coding.display","RXR-1-2"));
    	mapping.add(new MappingList("Immunization.doseQuantity","RXA-6"));
    	mapping.add(new MappingList("Immunization.practitioner","RXA-10"));
    	/*mapping.add(new MappingList("Immunization.practitioner.role.coding.system","RXA-18-3"));
    	mapping.add(new MappingList("Immunization.practitioner.role.coding.code","RXA-18-1"));
    	mapping.add(new MappingList("Immunization.practitioner.role.coding.display","RXA-18-2"));*/
    	mapping.add(new MappingList("Immunization.reaction.date","OBX-14"));
    	mapping.add(new MappingList("Immunization.reaction.detail","OBX-5"));
    	mapping.add(new MappingList("Immunization.reaction.detail.identifier.system","OBX-3-3"));
    	mapping.add(new MappingList("Immunization.reaction.detail.identifier.value","OBX-3-1"));


  	
    	
    	
    		return mapping;
    }
}
