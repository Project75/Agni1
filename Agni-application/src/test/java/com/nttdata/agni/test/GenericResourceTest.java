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
                + "PID||001|199||JOHN^DOE||19751027|Female|||street 53^^PHOENIX^AZ^85013^US||(111)222-3333^^^^^^(111)222-3333||N|W|||001|||||false||||||false|||||PID.35\r"
                + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|Father||||||ORGANIZATION||Male\r"
                +"PV1||O|5501^0113^02|U|00060292||00276^DELBARE^POL^^DR.|00276^DELBARE^POL^^DR.||1901|||N|01|||||0161782703\r"
                +"PD1|1|2|3|4|5|6|7|8|9|10\r"
                +"OBX|1|TX|3|4|5|6|7|8|9|10|FINAL||13|20060221061809|15|16|17|18|19|20|21|22|23|24|25|26\r"
                +"OBR|1|2156286|A140875|MRSHLR-C^MR Shoulder right wo/contrast|5||||9|10|11|12|13||15|16||18|19|20|21|20060221061809|23|24|25|26\r"
                +"NTE|1|2|3|4\r"
                +"ORC||||||||||||||||||||||orcstreet 53^^LA^CAL^12345^US|(333)444-5555^^\r"
                +"OM1||2|||19751027||||||11||13|||16|17|(111)222-3333||||\r"
                +"MFE|MFE1\r"
                +"PRT|1|2|3|PO|5|6|7|PerformerROLEDISPLAY^^PerformerACTOR\r";  
    	
    	return transform(resourceName, payload);
    	
    }
    public  String transform(String resourceName,String payload) {
    	System.out.println("***************************");
    	System.out.println("Processing for "+resourceName);
    	HashMap<String, String> dataMap = null;
    	    	  
        try {        	        	
        	
			Message hapiMsg = hl7Transformer.getHL7FromPayload(payload);
 
        	HashMap<String, String> tempMap = getMappings(resourceName); // Argument added by Ankit
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
	private HashMap<String, String> getMappings(String resourceName) // Argument added by Ankit 
	{
		// TODO Auto-generated method stub
		HashMap<String, String> mappingMap =new HashMap<String, String>();
        List<MappingList> mappingList = mockMappings(resourceName); // Argument added by Ankit
        
        
        if (mappingList.size() > 0) {
        	//System.out.println("MappingList size is "+mappingList.size());
        	for (MappingList entity : mappingList) {	    		
        		mappingMap.put(entity.getFHIR(), entity.getHL7());	    		
	        }
        } 
    	return mappingMap;
		
	}
	private List<MappingList> mockMappings(String resourceName)// Argument Added by Ankit
	{
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
    	
    	// Mapping for MedicationStatement resource.
    	mapping.add(new MappingList("MedicationStatement.identifier","OBR-2-1")); // dummy value just for testing 
    	mapping.add(new MappingList("MedicationStatement.subject.reference","PID-3-1"));
    	mapping.add(new MappingList("MedicationStatement.subject.identifier.value","PID-3-1"));
    	mapping.add(new MappingList("MedicationStatement.subject.display","PID-5-1"));// dummy value just for testing
    		
    				
    			
    		
    	// Mapping for Organization resource.
   	  //  mapping.add(new MappingList(resourceName+"."+ResourceFactory.getResource(resourceName).getVaribleArray()[0],"")); //
   	 //   mapping.add(new MappingList(resourceName+"."+ResourceFactory.getResource(resourceName).getVaribleArray()[1],"")); //
   	  //  mapping.add(new MappingList(resourceName+"."+ResourceFactory.getResource(resourceName).getVaribleArray()[2],"")); //
   	  //  mapping.add(new MappingList(resourceName+"."+ResourceFactory.getResource(resourceName).getVaribleArray()[3],"")); //
   	 //   mapping.add(new MappingList(resourceName+"."+ResourceFactory.getResource(resourceName).getVaribleArray()[4],"")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[5],"ORC-23-3")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[6],"ORC-23-1")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[7],"ORC-22-7")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[8],"ORC-22-1")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[9],"ORC-22-3")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[10],"ORC-22-4")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[11],"ORC-22-5")); //
   	   mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[12],"ORC-22-6")); //
   	  mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[13],"ORC-22-12")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[14],"PID-5-1")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[15],"PID-5-2")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[16],"PID-5-5")); //
   	   mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[17],"PID-5-4")); //
   	  mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[18],"PID-5-10")); //
   	    mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[19],"PID-13-7")); // Concatnate pending
   	   mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[20],"PID-11-7")); //
   	  mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[21],"PID-11-1")); //
   	 mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[22],"PID-11-3")); //
   	 mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[23],"PID-11-9")); //
   	 mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[24],"PID-11-4")); //
   	mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[25],"PID-11-5")); //
   	 mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[26],"PID-11-6")); //
   	 mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[27],"PID-11-12-1")); //
   	 mapping.add(new MappingList("Organization"+"."+ResourceFactory.getResource("Organization").getVaribleArray()[28],"PID-11-12-2")); //
   	  //mapping.add(new MappingList(resourceName+"."+ResourceFactory.getResource(resourceName).getVaribleArray()[29],"")); //

   	    
   	// mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[0],"")); //
   	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[1],"OM1-2")); //
   //	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[2],"")); //
   	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[3],"MFE-1")); //
   //	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[4],"")); //
   	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[5],"OM1-21")); //
   	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[6],"OM1-16")); //
   //	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[7],"")); //
   	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[8],"OM1-11")); //
  //	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[9],"")); //
	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[10],"OM1-17")); //
  //	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[11],"")); //
  //	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[12],"")); //
  	 mapping.add(new MappingList("DataElement"+"."+ResourceFactory.getResource("DataElement").getVaribleArray()[13],"OM1-18")); //

    	
  	 // Mapping for Diagnostic reports
  	mapping.add(new MappingList("DiagnosticReport.identifierValue","OBR-51-1"));
  	mapping.add(new MappingList("DiagnosticReport.status","OBR-25"));
  	mapping.add(new MappingList("DiagnosticReport.categoryCodingCode","OBR-24"));
  	mapping.add(new MappingList("DiagnosticReport.codeCodingCode","OBR-4-1"));
  	mapping.add(new MappingList("DiagnosticReport.subjectIdentifierTypeCodingCode","PID-3-5"));
  	mapping.add(new MappingList("DiagnosticReport.subjectIdentifierSystem","PID-3-4-2"));
  	mapping.add(new MappingList("DiagnosticReport.subjectIdentifierValue","PID-3-1"));
  	mapping.add(new MappingList("DiagnosticReport.subjectIdentifierPeriodStart","PID-3-7"));
  	mapping.add(new MappingList("DiagnosticReport.subjectIdentifierPeriodEnd","PID-3-8"));
  	mapping.add(new MappingList("DiagnosticReport.subjectIdentifierAssignerDisplay","PID-3-4-1"));
  	mapping.add(new MappingList("DiagnosticReport.contextIdentifierTypeCodingCode","PV1-19-5"));
  	mapping.add(new MappingList("DiagnosticReport.contextIdentifierSystem","PV1-19-4-2"));
  	mapping.add(new MappingList("DiagnosticReport.contextIdentifierValue","PV1-19-1"));
  	mapping.add(new MappingList("DiagnosticReport.contextIdentifierPeriodStart","PV1-19-7"));
  	mapping.add(new MappingList("DiagnosticReport.contextIdentifierPeriodEnd","PV1-19-8"));
  	mapping.add(new MappingList("DiagnosticReport.contextIdentifierAssignerDisplay","PV1-19-1"));
  	mapping.add(new MappingList("DiagnosticReport.effectiveEffectivedatetime","OBR-7"));
  	mapping.add(new MappingList("DiagnosticReport.issued","OBR-22"));
  	mapping.add(new MappingList("DiagnosticReport.performerRoleCodingDisplay","PRT-8-1"));  // doubt
  	mapping.add(new MappingList("DiagnosticReport.performerActorReference","PRT-8-3"));  // doubt
  	mapping.add(new MappingList("DiagnosticReport.PRT4","PRT-4"));  // If condition variable
  	
  	 
    				
    		return mapping;		
    }
	
	
	
}
