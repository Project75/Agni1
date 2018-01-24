package com.nttdata.agni.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;
import com.nttdata.agni.domain.MappingList;

public class TestUtils {
	
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
	public static List<MappingList> nullMappings() {
    	List<MappingList> mapping =  new ArrayList<MappingList>();
    	mapping.add(new MappingList("patient.identifier.value.none","PID-3"));
    	return mapping;
	} 
	public static List<MappingList> mockMappings() {
    	List<MappingList> mapping =  new ArrayList<MappingList>();
		
		mapping.add(new MappingList("patient.identifier","PID-3-1"));
		mapping.add(new MappingList("patient.name.family","PID-5-1"));
		mapping.add(new MappingList("patient.name.given","PID-5-2"));
		mapping.add(new MappingList("patient.gender","PID-8-1"));
		mapping.add(new MappingList("patient.birthdate","PID-7"));
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
		mapping.add(new MappingList("MedicationStatement.identifier","OBR-2-1"));  
		mapping.add(new MappingList("MedicationStatement.subject.reference","PID-3-1"));
		mapping.add(new MappingList("MedicationStatement.subject.identifier.value","PID-3-1"));
		mapping.add(new MappingList("MedicationStatement.subject.display","PID-5-1"));
		   
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
		mapping.add(new MappingList("DiagnosticReport.performerRoleCodingDisplay","PRT-8-1"));   
		mapping.add(new MappingList("DiagnosticReport.performerActorReference","PRT-8-3"));   
		mapping.add(new MappingList("DiagnosticReport.PRT4","PRT-4"));   
		 
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

		mapping.add(new MappingList("practitioner.identifier","PRD-7"));
		mapping.add(new MappingList("practitioner.active","PID-2"));
		mapping.add(new MappingList("practitioner.name","PRD-2"));
		mapping.add(new MappingList("practitioner.telecom","PRT-15"));
		mapping.add(new MappingList("practitioner.address.line","ORC-24-1"));
		mapping.add(new MappingList("practitioner.address.city","ORC-24-3"));
		mapping.add(new MappingList("practitioner.address.state","ORC-24-4"));
		mapping.add(new MappingList("practitioner.address.postalCode","ORC-24-5"));
		mapping.add(new MappingList("practitioner.address.country","ORC-24-6"));
		mapping.add(new MappingList("practitioner.gender","STF-5"));
		mapping.add(new MappingList("practitioner.birthDate","STF-6"));
		mapping.add(new MappingList("practitioner.photo","PID-2"));
		mapping.add(new MappingList("practitioner.qualification","PID-2"));
		mapping.add(new MappingList("practitioner.communication","PID-15"));
		mapping.add(new MappingList("practitionerrole.identifier","PRD-7"));
		mapping.add(new MappingList("practitionerrole.active","STF-7"));
		mapping.add(new MappingList("practitionerrole.period","PRD-8"));
		mapping.add(new MappingList("practitionerrole.practitioner","PID-2"));
		mapping.add(new MappingList("practitionerrole.observation","PID-2"));
		mapping.add(new MappingList("practitionerrole.code","PRD-1"));
		mapping.add(new MappingList("practitionerrole.specialty","PRA-5"));
		mapping.add(new MappingList("practitionerrole.location","PID-2"));
		mapping.add(new MappingList("practitionerrole.healthcareService","EDU-2"));
		mapping.add(new MappingList("practitionerrole.telecom","PID-2"));
		mapping.add(new MappingList("practitionerrole.availableTime","PID-2"));
		mapping.add(new MappingList("practitionerrole.notAvailable","PID-2"));
		mapping.add(new MappingList("practitionerrole.availabilityExceptions","PID-2"));
		mapping.add(new MappingList("practitionerrole.endpoint","PID-2"));
		mapping.add(new MappingList("procedure.identifier","ORC-2"));
		mapping.add(new MappingList("procedure.definition","PID-2"));
		mapping.add(new MappingList("procedure.basedOn","PID-2"));
		mapping.add(new MappingList("procedure.partOf","PID-2"));
		mapping.add(new MappingList("procedure.status","PID-2"));
		mapping.add(new MappingList("procedure.notDone","PID-2"));
		mapping.add(new MappingList("procedure.notDoneReason","PID-2"));
		mapping.add(new MappingList("procedure.category","PID-2"));
		mapping.add(new MappingList("procedure.code","OBR-44"));
		mapping.add(new MappingList("procedure.subject","PID-3"));
		mapping.add(new MappingList("procedure.context","PV1-19"));
		mapping.add(new MappingList("procedure.performed","OBR-7-1"));
		mapping.add(new MappingList("procedure.performer.role","STF-18"));
		mapping.add(new MappingList("procedure.performer.actor","ORC-19"));
		mapping.add(new MappingList("procedure.location","PID-2"));
		mapping.add(new MappingList("procedure.reasonCode","PID-2"));
		mapping.add(new MappingList("procedure.reasonReference","PID-2"));
		mapping.add(new MappingList("procedure.bodySite","OBX-20"));
		mapping.add(new MappingList("procedure.outcome","PID-2"));
		mapping.add(new MappingList("procedure.report","PID-2"));
		mapping.add(new MappingList("procedure.complication","PID-2"));
		mapping.add(new MappingList("procedure.complicationDetail","PID-2"));
		mapping.add(new MappingList("procedure.followUp","PID-2"));
		mapping.add(new MappingList("procedure.note","NTE"));
		mapping.add(new MappingList("procedure.focalDevice","PID-2"));
		mapping.add(new MappingList("procedure.usedReference","PID-2"));
		mapping.add(new MappingList("procedure.usedCode","PID-2"));
		mapping.add(new MappingList("procedurerequest.identifier","ORC-2"));
		mapping.add(new MappingList("procedurerequest.definition","PID-2"));
		mapping.add(new MappingList("procedurerequest.basedOn","ORC-8"));
		mapping.add(new MappingList("procedurerequest.replaces","PID-2"));
		mapping.add(new MappingList("procedurerequest.requisition","ORC-4"));
		mapping.add(new MappingList("procedurerequest.status","ORC-5"));
		mapping.add(new MappingList("procedurerequest.intent","PID-2"));
		mapping.add(new MappingList("procedurerequest.priority","TQ1-9"));
		mapping.add(new MappingList("procedurerequest.doNotPerform","PID-2"));
		mapping.add(new MappingList("procedurerequest.category","PID-2"));
		mapping.add(new MappingList("procedurerequest.code","PID-2"));
		mapping.add(new MappingList("procedurerequest.subject","PID-2"));
		mapping.add(new MappingList("procedurerequest.context","PID-2"));
		mapping.add(new MappingList("procedurerequest.occurrence","PID-2"));
		mapping.add(new MappingList("procedurerequest.asNeeded","PID-2"));
		mapping.add(new MappingList("procedurerequest.authoredOn","ORC-9"));
		mapping.add(new MappingList("procedurerequest.requester.agent","ORC-12"));
		mapping.add(new MappingList("procedurerequest.performerType","PID-2"));
		mapping.add(new MappingList("procedurerequest.performer","PID-2"));
		mapping.add(new MappingList("procedurerequest.reasonCode","ORC-16"));
		mapping.add(new MappingList("procedurerequest.reasonReference","ORC-16"));
		mapping.add(new MappingList("procedurerequest.supportingInfo","PID-2"));
		mapping.add(new MappingList("procedurerequest.specimen","SPM"));
		mapping.add(new MappingList("procedurerequest.bodySite","SPM"));
		mapping.add(new MappingList("procedurerequest.note","NTE"));
		mapping.add(new MappingList("procedurerequest.relevantHistory","PID-2"));
		mapping.add(new MappingList("referralrequest.identifier","RF1-6"));
		mapping.add(new MappingList("referralrequest.definition","PID-2"));
		mapping.add(new MappingList("referralrequest.basedOn","PID-2"));
		mapping.add(new MappingList("referralrequest.replaces","PID-2"));
		mapping.add(new MappingList("referralrequest.groupIdentifier","PID-2"));
		mapping.add(new MappingList("referralrequest.status","RF1-1"));
		mapping.add(new MappingList("referralrequest.intent","PID-2"));
		mapping.add(new MappingList("referralrequest.type","RF1-10"));
		mapping.add(new MappingList("referralrequest.priority","RF1-2"));
		mapping.add(new MappingList("referralrequest.serviceRequested","PR1-3"));
		mapping.add(new MappingList("referralrequest.subject","PID-3-1"));
		mapping.add(new MappingList("referralrequest.context","PV1-19"));
		mapping.add(new MappingList("referralrequest.occurrence","ORC-7"));
		mapping.add(new MappingList("referralrequest.authoredOn","RF1-7"));
		mapping.add(new MappingList("referralrequest.requester","PID-2"));
		mapping.add(new MappingList("referralrequest.specialty","RF1-3"));
		mapping.add(new MappingList("referralrequest.recipient","PRD-2"));
		mapping.add(new MappingList("referralrequest.reasonCode","RF1-10"));
		mapping.add(new MappingList("referralrequest.reasonReference","PID-2"));
		mapping.add(new MappingList("referralrequest.description","PID-2"));
		mapping.add(new MappingList("referralrequest.supportingInfo","PID-2"));
		mapping.add(new MappingList("referralrequest.note","PID-2"));
		mapping.add(new MappingList("referralrequest.relevantHistory","PID-2"));
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
		mapping.add(new MappingList("Organization.telecomsystem","ORC-23-3"));
		mapping.add(new MappingList("Organization.telecomvalue","ORC-23-1"));
		mapping.add(new MappingList("Organization.addresstype","ORC-22-7"));
		mapping.add(new MappingList("Organization.addresstext","ORC-22-1"));
		mapping.add(new MappingList("Organization.addresscity","ORC-22-3"));
		mapping.add(new MappingList("Organization.addressstate","ORC-22-4"));
		mapping.add(new MappingList("Organization.addresspostalcode","ORC-22-5"));
		mapping.add(new MappingList("Organization.addresscountry","ORC-22-6"));
		mapping.add(new MappingList("Organization.addresscountryperiodstart","ORC-22-12"));
		mapping.add(new MappingList("Organization.contactfamily","PID-5-1"));
		mapping.add(new MappingList("Organization.contactgiven","PID-5-2"));
		mapping.add(new MappingList("Organization.contactprefix","PID-5-5"));
		mapping.add(new MappingList("Organization.contactsuffix","PID-5-4"));
		mapping.add(new MappingList("Organization.contactperiodstart","PID-5-10"));
		mapping.add(new MappingList("Organization.contacttelecomvalue","PID-13-7"));
		mapping.add(new MappingList("Organization.contactaddressuse","PID-11-7"));
		mapping.add(new MappingList("Organization.contactaddressline","PID-11-1"));
		mapping.add(new MappingList("Organization.contactaddresscity","PID-11-3"));
		mapping.add(new MappingList("Organization.contactaddressdistrict","PID-11-9"));
		mapping.add(new MappingList("Organization.contactaddressstate","PID-11-4"));
		mapping.add(new MappingList("Organization.contactaddresspostalcode","PID-11-5"));
		mapping.add(new MappingList("Organization.contactaddresscountry","PID-11-6"));
		mapping.add(new MappingList("Organization.contactaddressperiodstart","PID-11-12-1"));
		mapping.add(new MappingList("Organization.contactaddressperiodend","PID-11-12-2"));
		mapping.add(new MappingList("DataElement.url","PID-2"));
		mapping.add(new MappingList("DataElement.","OM1-2"));
		mapping.add(new MappingList("DataElement.identifiervalue","PID-2"));
		mapping.add(new MappingList("DataElement.","MFE-1"));
		mapping.add(new MappingList("DataElement.version","PID-2"));
		mapping.add(new MappingList("DataElement.status","OM1-21"));
		mapping.add(new MappingList("DataElement.experimental","OM1-16"));
		mapping.add(new MappingList("DataElement.publisher","PID-2"));
		mapping.add(new MappingList("DataElement.date","OM1-11"));
		mapping.add(new MappingList("DataElement.name","PID-2"));
		mapping.add(new MappingList("DataElement.title","OM1-17"));
		mapping.add(new MappingList("DataElement.contactname","PID-2"));
		mapping.add(new MappingList("DataElement.contacttelecomvalue","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextcodesystem","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextcodeversion","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextcodecode","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextcodedisplay","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextcodeuserselected","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextvaluecodebleconceptcodingsystem","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextvaluecodebleconcepttext","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextvaluequantityunit","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextvaluequantitysystem","PID-2"));
		mapping.add(new MappingList("DataElement.usecontextvaluequantitycode","PID-2"));


		    


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
	public static String generateFlatFileTestPayload(int num){
		
		String output =""; 
		 		 
        	for (int i=0;i<num;i++){
        		Faker faker = new Faker();

        		String name = faker.name().fullName(); // Miss Samanta Schmidt
        		String firstName = faker.name().firstName(); // Emory
        		String lastName = faker.name().lastName(); // Barton

        		String streetAddress = faker.address().streetAddress();
        		String city = faker.address().city();
        		String state = faker.address().state();
        		Date dob=faker.date().birthday();
        		String phone=faker.phoneNumber().cellPhone();
        		int randomNum = ThreadLocalRandom.current().nextInt(100, 1000000);
        		String gender="Female";
        		if (i%2==0){
        			 gender="Male";
        		}
        		String data=randomNum+","+firstName+","+lastName+","+gender+","+randomBirthDate()+","+city+","+state+","+phone+",Married\r";
                
        		output = output+data;
        	}
		
		return output;
		
	}
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	public static String randomBirthDate() {
		Random  rnd;
		Date    dt;
		long    ms;
	
		// Get a new random instance, seeded from the clock
		rnd = new Random();
	
		// Get an Epoch value roughly between 1940 and 2010
		// -946771200000L = January 1, 1940
		// Add up to 70 years to it (using modulus on the next long)
		ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
	
		// Construct a date
		dt = new Date(ms);
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(dt);

		return date;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		String content = TestUtils.generateFlatFileTestPayload(10);
		try {
			Files.write(Paths.get("c:/poc/output1.txt"), content.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
