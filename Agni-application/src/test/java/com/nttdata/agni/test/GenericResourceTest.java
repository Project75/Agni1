package com.nttdata.agni.test;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.nttdata.agni.resources.utils.TransformMap;
import java.util.List;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
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
    public static String getTestPayload(){
    	String payload = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                +"PID||TEST|199&100^20110101^^EHR^MR&SSN^FAC~1000||JOHN^DOE~JOHNY^DEPP||19751027|FEMALE|||street 53^^PHOENIX^AZ^85013^US||(111)222-3333||N|W|||001|||||false||||||false|||||PID.35\r"
 
                +"NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|Father||||||ORGANIZATION||Male\r"
                +"PV1||O|5501^0113^02|U|00060292||00276^DELBARE^POL^^DR.|00276^DELBARE^POL^^DR.||1901|||N|01|||||0161782703^^^EHR^ACCT\r"
                +"PD1|1|2|3|4|5|6|7|8|9|10\r"
                +"OBX|1|TX|3|4|5|6|7|8|9|10|FINAL||13|20060221061809|15|16|17|18|19|20|21|22|23|24|25|26\r"
                +"OBR|1|2156286|A140875|MRSHLR-C^MR Shoulder right wo/contrast|5||||9|10|11|12|13||15|16||18|19|20|21|20060221061809|23|24|25|26\r"
                +"NTE|1|2|3-comment|4\r"
                
                +"SCH|01928374|57483920|||||||1|hr|1^^^20160515133000^20160515134500|||||||||1173^MATTHEWS^JAMES^A|||||BOOKED\r"
    			+"AIP|1||1069^GOOD^ALLAN^B|RADIOLOGIST||20160515134500|15|min|45|min||ACCEPTED|\r"
                +"AIS|1||73610^X-RAY ANKLE 3+ VW^CPT|20160515134500|15|min|45|min||\r"
                +"ARQ|19940047^SCH001|||||047^Referral||NORMAL|||201605150800^201605151700|2|||0045^Contact^Carrie^S^^^||||3372^Person^Entered||||\r"
                +"RXA|0|1|201312211100||48^Hib^CVX|2|mL^milliliters^UCUM||00^Administered^NIP001|DUMMYPRACTITIONER|||||K7164HI||PMC^sanofipasteur^MVX|DUMNONED^DUMALLERGIC^DUM|||A|\r"
                +"RXR|IMLA^LeftArmIntramuscular^HL70162|LA^Left arm^HL70163|\r"
                +"ORC||||||||||||||||||||||orcstreet 53^^LA^CAL^12345^US|(333)444-5555^^\r"
                +"OM1||2|||19751027||||||11||13|||16|17|(111)222-3333||||\r"
                +"MFE|MFE1\r"
                +"PRT|1|2|3|PO|5|6|7|PerformerROLEDISPLAY^^PerformerACTOR\r"
    	        +"IAM|IAM1|IAM2|IAM3|IAM4|IAM5|IAM6|IAM7|IAM8\r"
    	        +"AL1||AL12.1^AL12.2^AL12.3|AL13|HIGH|AL15|20060221|AL17|AL18\r"
    			+"PRD|1|Adam|3|4|5|6|1111|2017-10-10|9\r"
                +"PRT|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(222)222-3333\r"
                +"ORC|1|2|3|4|cancelled|6|20170202|8|20170221|10|11|12|13|(333)111-3333|20170221061810|16|17|18|19|20|21|22|23|street 3^^PHOENIX^AZ^85013^US|25\r"
                +"STF|1|2|3|4|Female|19751027|7|8|9||11|||||||18\r"
                +"PRA|1|2|3|4|GS|6|7\r"
                +"TQ1|1|2|3|4|5|6|7|8|stat\r"
                +"RF1|active|Urgent|3|4|5|6|2017-01-01|8|9|10\r"
                +"PR1|1|2|3|4||6|7|8\r";  
    	return payload;
    }
    public  String transform(String resourceName) {
    	              
    	return transform(resourceName, getTestPayload());
    	
    }
    public  String transform(String resourceName,String payload) {
    	System.out.println("***************************");
    	System.out.println("Processing for "+resourceName);
    	TransformMap dataMap = null;
    	    	  
        try {        	        	
        	
			Message hapiMsg = hl7Transformer.getHL7FromPayload(payload);
 
        	ListMultimap<String, String> tempMap = getMappings();
        	dataMap = hl7Transformer.getHL7ValuesMap(hapiMsg, tempMap);
        	
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        AbstractResource res = createFHIRResource(dataMap,resourceName);
        //print json 
        System.out.println(getResourceAsJson(res));
        return getResourceAsJson(res);
        
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
	private String getResourceAsJson(AbstractResource resource) {
		return TransformUtils.resourceToJson(resource);
	}
	private String getResourceAsString(AbstractResource resource) {
		return resource.toString();
	}
	private ListMultimap<String, String> getMappings() {
		// TODO Auto-generated method stub
		ListMultimap<String, String> mappingMap =ArrayListMultimap.create();
        List<MappingList> mappingList = mockMappings();
        
        
        if (mappingList.size() > 0) {
        	//System.out.println("MappingList size is "+mappingList.size());
        	for (MappingList entity : mappingList) {	    		
        		mappingMap.put(entity.getFhir(), entity.getHl7());	    		
	        }
        } 
    	return mappingMap;
		
	}
	
	public static List<MappingList> mockMappings() {
    	List<MappingList> mapping =  new ArrayList<MappingList>();
    	
		//mapping.add(new MappingList("patient.identifier.use","PID-3-?"));
		mapping.add(new MappingList("patient.identifier.value","PID-3-1"));
		mapping.add(new MappingList("patient.identifier.value","PID-3-1-2"));
		//mapping.add(new MappingList("patient.identifier.system","PID-3-"));
		//mapping.add(new MappingList("patient.identifier.type.coding.code","PID-3-5"));
		mapping.add(new MappingList("patient.identifier.type.coding.display","PID-3-5"));
		//mapping.add(new MappingList("patient.identifier.type.coding.system","PID-3-5"));
		//mapping.add(new MappingList("patient.identifier.period.start","PID-3-2"));
		//mapping.add(new MappingList("patient.identifier.period.end","PID-7-1"));
		//mapping.add(new MappingList("patient.identifier.assigner.value","PID-3-4"));
		//mapping.add(new MappingList("patient.identifier.assigner.display","PID-3-4"));
    	mapping.add(new MappingList("patient.name.family","PID-5-1"));
    	mapping.add(new MappingList("patient.name.given","PID-5-2"));
    	mapping.add(new MappingList("patient.gender","PID-8-1"));
    	mapping.add(new MappingList("patient.birthDate","PID-7-1"));
    	mapping.add(new MappingList("patient.address.line","PID-11-1"));
    	mapping.add(new MappingList("patient.address.city","PID-11-3"));
    	mapping.add(new MappingList("patient.address.state","PID-11-4"));
    	mapping.add(new MappingList("patient.address.postalcode","PID-11-5"));
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
    	mapping.add(new MappingList("Immunization.vaccineCode","RXA-5"));
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
    	mapping.add(new MappingList("Immunization.explanation.reasonNotGiven.coding.system","RXA-18-3"));
    	mapping.add(new MappingList("Immunization.explanation.reasonNotGiven.coding.code","RXA-18-1"));
    	mapping.add(new MappingList("Immunization.explanation.reasonNotGiven.coding.display","RXA-18-2"));
    	mapping.add(new MappingList("Immunization.reaction.date","OBX-14"));
    	mapping.add(new MappingList("Immunization.reaction.detail","OBX-5"));
    	mapping.add(new MappingList("Immunization.reaction.detail.identifier.system","OBX-3-3"));
    	mapping.add(new MappingList("Immunization.reaction.detail.identifier.value","OBX-3-1"));

//Ankit
    	// Mapping for MedicationStatement resource.
    	mapping.add(new MappingList("MedicationStatement.identifier","OBR-2-1")); // dummy value just for testing 
    	mapping.add(new MappingList("MedicationStatement.subject.reference","PID-3-1"));
    	mapping.add(new MappingList("MedicationStatement.subject.identifier.value","PID-3-1"));
    	mapping.add(new MappingList("MedicationStatement.subject.display","PID-5-1"));// dummy value just for testing
    		
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[5],"ORC-23-3")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[6],"ORC-23-1")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[7],"ORC-22-7")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[8],"ORC-22-1")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[9],"ORC-22-3")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[10],"ORC-22-4")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[11],"ORC-22-5")); //
   	   mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[12],"ORC-22-6")); //
   	  mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[13],"ORC-22-12")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[14],"PID-5-1")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[15],"PID-5-2")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[16],"PID-5-5")); //
   	   mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[17],"PID-5-4")); //
   	  mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[18],"PID-5-10")); //
   	    mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[19],"PID-13-7")); // Concatnate pending
   	   mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[20],"PID-11-7")); //
   	  mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[21],"PID-11-1")); //
   	 mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[22],"PID-11-3")); //
   	 mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[23],"PID-11-9")); //
   	 mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[24],"PID-11-4")); //
   	mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[25],"PID-11-5")); //
   	 mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[26],"PID-11-6")); //
   	 mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[27],"PID-11-12-1")); //
   	 mapping.add(new MappingList("Organization"+"."+getVaribleArrayOrganization()[28],"PID-11-12-2")); //
 

	    
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[0],"PID-2")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[1],"OM1-2")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[2],"PID-2")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[3],"MFE-1")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[4],"PID-2")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[5],"OM1-21")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[6],"OM1-16")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[7],"PID-2")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[8],"OM1-11")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[9],"PID-2")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[10],"OM1-17")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[11],"PID-2")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[12],"PID-2")); 
	 mapping.add(new MappingList("DataElement"+"."+getVaribleArrayDataElement()[13],"OM1-18")); 

    	
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
  	
 // Mapping for Detected Issue
  	mapping.add(new MappingList("DetectedIssue.identifierValue","IAM-7"));
  	mapping.add(new MappingList("DetectedIssue.categoryCodingCode","AL1-2-1"));
  	mapping.add(new MappingList("DetectedIssue.categoryCodingSystem","AL1-2-3"));
  	mapping.add(new MappingList("DetectedIssue.categoryCodingDisplay","AL1-2-2"));
  	mapping.add(new MappingList("DetectedIssue.severity","AL1-4-1"));
  	mapping.add(new MappingList("DetectedIssue.patientIdentifierTypeCodingCode","PID-3-5"));
  	mapping.add(new MappingList("DetectedIssue.patientIdentifierSystem","PID-3-4-2"));
  	mapping.add(new MappingList("DetectedIssue.patientIdentifierValue","PID-3-1"));
  	mapping.add(new MappingList("DetectedIssue.patientIdentifierPeriodStart","PID-3-7"));
  	mapping.add(new MappingList("DetectedIssue.patientIdentifierPeriodEnd","PID-3-8"));
  	mapping.add(new MappingList("DetectedIssue.patientIdentifierAssignerDisplay","PID-3-4-1"));
  	mapping.add(new MappingList("DetectedIssue.date","AL1-6"));
  	mapping.add(new MappingList("DetectedIssue.detail","AL1-5"));
  	
	mapping.add(new MappingList("bodysite.code","OBX-20"));
	mapping.add(new MappingList("bodysite.qualifier","OBR-15"));
	
	//neha
	mapping.add(new MappingList("practitioner.identifier","PRD-7"));
	//mapping.add(new MappingList("practitioner.active",""));
	mapping.add(new MappingList("practitioner.name","PRD-2"));
	mapping.add(new MappingList("practitioner.telecom","PRT-15"));
	mapping.add(new MappingList("practitioner.address.line","ORC-24-1"));
	mapping.add(new MappingList("practitioner.address.city","ORC-24-3"));
	mapping.add(new MappingList("practitioner.address.state","ORC-24-4"));
	mapping.add(new MappingList("practitioner.address.postalCode","ORC-24-5"));
	mapping.add(new MappingList("practitioner.address.country","ORC-24-6"));	
	mapping.add(new MappingList("practitioner.gender","STF-5"));
	mapping.add(new MappingList("practitioner.birthDate","STF-6"));
	//mapping.add(new MappingList("practitioner.photo",""));
	//mapping.add(new MappingList("practitioner.qualification",""));
	mapping.add(new MappingList("practitioner.communication","PID-15"));
	mapping.add(new MappingList("practitionerrole.identifier","PRD-7"));
	mapping.add(new MappingList("practitionerrole.active","STF-7"));
	mapping.add(new MappingList("practitionerrole.period","PRD-8"));
	//mapping.add(new MappingList("practitionerrole.practitioner",""));
	//mapping.add(new MappingList("practitionerrole.observation",""));
	mapping.add(new MappingList("practitionerrole.code","PRD-1"));
	mapping.add(new MappingList("practitionerrole.specialty","PRA-5"));
	//mapping.add(new MappingList("practitionerrole.location",""));
	//mapping.add(new MappingList("practitionerrole.healthcareService","EDU-2"));
	//mapping.add(new MappingList("practitionerrole.telecom",""));
	//mapping.add(new MappingList("practitionerrole.availableTime",""));
	//mapping.add(new MappingList("practitionerrole.notAvailable",""));
	//mapping.add(new MappingList("practitionerrole.availabilityExceptions",""));
	//mapping.add(new MappingList("practitionerrole.endpoint",""));
	mapping.add(new MappingList("procedure.identifier","ORC-2"));
	//mapping.add(new MappingList("procedure.definition",""));
	//mapping.add(new MappingList("procedure.basedOn",""));
	//mapping.add(new MappingList("procedure.partOf",""));
	//mapping.add(new MappingList("procedure.status",""));
	//mapping.add(new MappingList("procedure.notDone",""));
	//mapping.add(new MappingList("procedure.notDoneReason",""));	
	//mapping.add(new MappingList("procedure.category",""));
	mapping.add(new MappingList("procedure.code","OBR-44"));
	//mapping.add(new MappingList("procedure.subject","PID-3"));
	//mapping.add(new MappingList("procedure.context","PV1-19"));
	//mapping.add(new MappingList("procedure.performed","OBR-7-1"));
	mapping.add(new MappingList("procedure.performer.role","STF-18"));
	//mapping.add(new MappingList("procedure.performer.actor","ORC-19"));
	//mapping.add(new MappingList("procedure.location",""));
	//mapping.add(new MappingList("procedure.reasonCode",""));
	//mapping.add(new MappingList("procedure.reasonReference",""));
	mapping.add(new MappingList("procedure.bodySite","OBX-20"));
	//mapping.add(new MappingList("procedure.outcome",""));
	//mapping.add(new MappingList("procedure.report",""));
	//mapping.add(new MappingList("procedure.complication",""));
	//mapping.add(new MappingList("procedure.complicationDetail",""));
	//mapping.add(new MappingList("procedure.followUp",""));
	//mapping.add(new MappingList("procedure.note","NTE"));
	//mapping.add(new MappingList("procedure.focalDevice",""));
	//mapping.add(new MappingList("procedure.usedReference",""));
	//mapping.add(new MappingList("procedure.usedCode",""));
	mapping.add(new MappingList("procedurerequest.identifier","ORC-2"));
	//mapping.add(new MappingList("procedurerequest.definition",""));
	mapping.add(new MappingList("procedurerequest.basedOn","ORC-8"));
	//mapping.add(new MappingList("procedurerequest.replaces",""));
	mapping.add(new MappingList("procedurerequest.requisition","ORC-4"));
	mapping.add(new MappingList("procedurerequest.status","ORC-5"));
	//mapping.add(new MappingList("procedurerequest.intent",""));
	mapping.add(new MappingList("procedurerequest.priority","TQ1-9"));
	//mapping.add(new MappingList("procedurerequest.doNotPerform",""));
	//mapping.add(new MappingList("procedurerequest.category",""));
	//mapping.add(new MappingList("procedurerequest.code",""));
	//mapping.add(new MappingList("procedurerequest.subject",""));
	//mapping.add(new MappingList("procedurerequest.context",""));
	//mapping.add(new MappingList("procedurerequest.occurrence",""));
	//mapping.add(new MappingList("procedurerequest.asNeeded",""));
	mapping.add(new MappingList("procedurerequest.authoredOn","ORC-9"));
	//mapping.add(new MappingList("procedurerequest.requester.agent","ORC-12"));
	//mapping.add(new MappingList("procedurerequest.performerType",""));
	//mapping.add(new MappingList("procedurerequest.performer",""));
	mapping.add(new MappingList("procedurerequest.reasonCode","ORC-16"));
	//mapping.add(new MappingList("procedurerequest.reasonReference","ORC-16"));
	//mapping.add(new MappingList("procedurerequest.supportingInfo",""));
	//mapping.add(new MappingList("procedurerequest.specimen","SPM"));
	//mapping.add(new MappingList("procedurerequest.bodySite","SPM"));
	//mapping.add(new MappingList("procedurerequest.note","NTE"));
	//mapping.add(new MappingList("procedurerequest.relevantHistory",""));
	mapping.add(new MappingList("referralrequest.identifier","RF1-6"));
	//mapping.add(new MappingList("referralrequest.definition",""));
	//mapping.add(new MappingList("referralrequest.basedOn",""));
	//mapping.add(new MappingList("referralrequest.replaces",""));
	//mapping.add(new MappingList("referralrequest.groupIdentifier",""));
	mapping.add(new MappingList("referralrequest.status","RF1-1"));
	//mapping.add(new MappingList("referralrequest.intent",""));
	mapping.add(new MappingList("referralrequest.type","RF1-10"));
	mapping.add(new MappingList("referralrequest.priority","RF1-2"));
	mapping.add(new MappingList("referralrequest.serviceRequested","PR1-3"));
	mapping.add(new MappingList("referralrequest.subject","PID-3-1"));
	mapping.add(new MappingList("referralrequest.context","PV1-19"));
	//mapping.add(new MappingList("referralrequest.occurrence","ORC-7"));
	mapping.add(new MappingList("referralrequest.authoredOn","RF1-7"));
	//mapping.add(new MappingList("referralrequest.requester",""));
	mapping.add(new MappingList("referralrequest.specialty","RF1-3"));
	mapping.add(new MappingList("referralrequest.recipient","PRD-2"));
	mapping.add(new MappingList("referralrequest.reasonCode","RF1-10"));
	//mapping.add(new MappingList("referralrequest.reasonReference",""));
	//mapping.add(new MappingList("referralrequest.description",""));
	//mapping.add(new MappingList("referralrequest.supportingInfo",""));
	//mapping.add(new MappingList("referralrequest.note",""));
	//mapping.add(new MappingList("referralrequest.relevantHistory",""));
    	
    	//harendra
	
	mapping.add(new MappingList("location.identifier","PID-2"));
	mapping.add(new MappingList("location.status","PID-2"));
	mapping.add(new MappingList("location.operationalStatus","PID-2"));
	mapping.add(new MappingList("location.name","PID-2"));
	mapping.add(new MappingList("location.alias","PID-2"));
	mapping.add(new MappingList("location.description","PID-2"));
	mapping.add(new MappingList("location.mode","PID-2"));
	mapping.add(new MappingList("location.type","PID-2"));
	mapping.add(new MappingList("location.telecom","PID-2"));
	mapping.add(new MappingList("location.address","PID-2"));
	mapping.add(new MappingList("location.physicalType","PID-2"));
	mapping.add(new MappingList("location.position","PID-2"));
	mapping.add(new MappingList("location.positionLongitude","PID-2"));
	mapping.add(new MappingList("location.positionLatitude","PID-2"));
	mapping.add(new MappingList("location.positionAltitude","PID-2"));
	mapping.add(new MappingList("location.managingOrganization","PID-2"));
	mapping.add(new MappingList("location.partOf","PID-2"));
	mapping.add(new MappingList("location.endpoint","PID-2"));
	
	mapping.add(new MappingList("medicationAdministration.identifier","PID-2"));
	mapping.add(new MappingList("medicationAdministration.definition","PID-2"));
	mapping.add(new MappingList("medicationAdministration.partOf","PID-2"));
	mapping.add(new MappingList("medicationAdministration.status","PID-2"));
	mapping.add(new MappingList("medicationAdministration.category","PID-2"));
	mapping.add(new MappingList("medicationAdministration.medicationCodeableConcept","PID-2"));
	mapping.add(new MappingList("medicationAdministration.medicationReference","PID-2"));
	mapping.add(new MappingList("medicationAdministration.subject","PID-2"));
	mapping.add(new MappingList("medicationAdministration.context","PID-2"));
	mapping.add(new MappingList("medicationAdministration.supportingInformation","PID-2"));
	mapping.add(new MappingList("medicationAdministration.effectiveDateTime","PID-2"));
	mapping.add(new MappingList("medicationAdministration.effectivePeriod","PID-2"));
	mapping.add(new MappingList("medicationAdministration.performerActor","PID-2"));
	mapping.add(new MappingList("medicationAdministration.performerOnBehalfOf","PID-2"));
	mapping.add(new MappingList("medicationAdministration.notGiven","PID-2"));
	mapping.add(new MappingList("medicationAdministration.reasonNotGiven","PID-2"));
	mapping.add(new MappingList("medicationAdministration.reasonCode","PID-2"));
	mapping.add(new MappingList("medicationAdministration.reasonReference","PID-2"));
	
	mapping.add(new MappingList("documentmanifest.masteridentifier","PID-2"));
	mapping.add(new MappingList("documentmanifest.identifier","PID-2"));
	mapping.add(new MappingList("documentmanifest.status","PID-2"));
	mapping.add(new MappingList("documentmanifest.type","PID-2"));
	mapping.add(new MappingList("documentmanifest.subject","PID-2"));
	mapping.add(new MappingList("documentmanifest.created","PID-2"));
	mapping.add(new MappingList("documentmanifest.author","PID-2"));
	mapping.add(new MappingList("documentmanifest.recipient","PID-2"));
	mapping.add(new MappingList("documentmanifest.source","PID-2"));
	mapping.add(new MappingList("documentmanifest.description","PID-2"));
	mapping.add(new MappingList("documentmanifest.content","PID-2"));
	mapping.add(new MappingList("documentmanifest.content.px","PID-2"));
	mapping.add(new MappingList("documentmanifest.content.pattachment","PID-2"));
	mapping.add(new MappingList("documentmanifest.content.preference","PID-2"));
	mapping.add(new MappingList("documentmanifest.related","PID-2"));
	mapping.add(new MappingList("documentmanifest.related.identifier","PID-2"));
	mapping.add(new MappingList("documentmanifest.related.ref","PID-2"));
	mapping.add(new MappingList("documentmanifest.masteridentifier","PID-2"));
	mapping.add(new MappingList("documentmanifest.identifier","PID-2"));
	mapping.add(new MappingList("documentmanifest.status","PID-2"));
	mapping.add(new MappingList("documentmanifest.type","PID-2"));
	mapping.add(new MappingList("documentmanifest.subject","PID-2"));
	mapping.add(new MappingList("documentmanifest.created","PID-2"));
	mapping.add(new MappingList("documentmanifest.author","PID-2"));
	mapping.add(new MappingList("documentmanifest.recipient","PID-2"));
	mapping.add(new MappingList("documentmanifest.source","PID-2"));
	mapping.add(new MappingList("documentmanifest.description","PID-2"));
	mapping.add(new MappingList("documentmanifest.content","PID-2"));
	mapping.add(new MappingList("documentmanifest.content.px","PID-2"));
	mapping.add(new MappingList("documentmanifest.content.pattachment","PID-2"));
	mapping.add(new MappingList("documentmanifest.content.preference","PID-2"));
	mapping.add(new MappingList("documentmanifest.related","PID-2"));
	mapping.add(new MappingList("documentmanifest.related.identifier","PID-2"));
	mapping.add(new MappingList("documentmanifest.related.ref","PID-2"));

	mapping.add(new MappingList("immunizationrecommendation.identifier","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.patient","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.recommendation","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.date","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.vaccineCode","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.targetDisease","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.doseNumber","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.forecastStatus","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.dateCriterion","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.dateCriterion.Code","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.dateCriterion.Value","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.protocol","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.protocol.doseSequence","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.protocol.description","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.protocol.Authority","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.protocol.Series","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.supportingImmunization","PID-2"));
	mapping.add(new MappingList("immunizationrecommendation.supportingPatientInformation","PID-2"));

	//Vivek
	
	mapping.add(new MappingList("VisionPrescription.identifier","PID-2"));
	mapping.add(new MappingList("VisionPrescription.status","PID-2"));
	mapping.add(new MappingList("VisionPrescription.patient.reference","PID-3-1"));
	mapping.add(new MappingList("VisionPrescription.patient.identifier","PID-3-1"));
	mapping.add(new MappingList("VisionPrescription.patient.display","PID-2"));
	mapping.add(new MappingList("VisionPrescription.encounter.reference","PV1-19"));
	mapping.add(new MappingList("VisionPrescription.encounter.identifier","PID-2"));
	mapping.add(new MappingList("VisionPrescription.encounter.display","PID-2"));
	mapping.add(new MappingList("VisionPrescription.dateWritten","ORC-9"));
	mapping.add(new MappingList("VisionPrescription.prescriber.reference","RXE-13"));
	mapping.add(new MappingList("VisionPrescription.prescriber.identifier","PID-2"));
	mapping.add(new MappingList("VisionPrescription.prescriber.display","PID-2"));
	mapping.add(new MappingList("VisionPrescription.reason.reasoncodableconcept","ORC-16"));
	mapping.add(new MappingList("VisionPrescription.reason.reference","ORC-16"));
	mapping.add(new MappingList("VisionPrescription.reason.identifier","ORC-16"));
	mapping.add(new MappingList("VisionPrescription.reason.display","PID-2"));
	mapping.add(new MappingList("VisionPrescription.dispenseproduct","RXE-2"));
    		return mapping;
    }
	
	public static String getTestPayload2(){
    	String payload = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID||TEST|199^^^EHR^MR||JOHN^DOE||19751027|FEMALE|||street 53^^PHOENIX^AZ^85013^US||(111)222-3333||N|W|||001|||||false||||||false|||||PID.35\r"
                + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|Father||||||ORGANIZATION||Male\r"
                +"PV1||O|5501^0113^02|U|00060292||00276^DELBARE^POL^^DR.|00276^DELBARE^POL^^DR.||1901|||N|01|||||0161782703^^^EHR^ACCT\r"
                +"PD1|1|2|3|4|5|6|7|8|9|10\r"
                +"OBX|1|TX|3|4|5|6|7|8|9|10|FINAL||13|20060221061809|15|16|17|18|19|20|21|22|23|24|25|26\r"
                +"OBR|1|2156286|A140875|MRSHLR-C^MR Shoulder right wo/contrast|5||||9|10|11|12|13||15|16||18|19|20|21|20060221061809|23|24|25|26\r"
                +"NTE|1|2|3-comment|4\r"
                ;  
    	return payload;
    }
	
	public static String[] getVaribleArrayOrganization() {
		String[] varibleArray = {"identifier", "active","type","name", "alias", "telecomsystem", "telecomvalue", "addresstype", "addresstext", "addresscity", "addressstate", "addresspostalcode", "addresscountry","addresscountryperiodstart",
				"contactfamily", "contactgiven", "contactprefix", "contactsuffix", "contactperiodstart" , "contacttelecomvalue", "contactaddressuse", "contactaddressline", "contactaddresscity", "contactaddressdistrict", "contactaddressstate",
				"contactaddresspostalcode", "contactaddresscountry" , "contactaddressperiodstart", "contactaddressperiodend", "endpoint"};
		
		return varibleArray;
	}
	
	public static String[] getVaribleArrayDataElement() {
		String[] varibleArray = {"url","identifiervalue", "version","status","experimental", "date", "publisher", "name","title" ,"contactname", "contacttelecomvalue", "usecontextcodesystem", "usecontextcodeversion", "usecontextcodecode", "usecontextcodedisplay","usecontextcodeuserselected",
				"usecontextvaluecodebleconceptcodingsystem", "usecontextvaluecodebleconcepttext" , "usecontextvaluequantityunit" , "usecontextvaluequantitysystem" , "usecontextvaluequantitycode"};
		
		return varibleArray;
	}
}
