package com.nttdata.agni.resources.utils;

public class FHIRConstants extends GenericConstants{
	public static final String IDENTIFIER_SYSTEM_DEFAULT = "http://hl7.org/fhir/v2/0203";
	public static final String DataAbsentReasonCodeSystem = "http://hl7.org/fhir/data-absent-reason";
	public static final String ParticipationTypeSystem= "http://hl7.org/fhir/v3/ParticipationType";
	public static final String PatientContactRelationshipCodeSystem ="http://hl7.org/fhir/patient-contact-relationship";
	//public static final String DATE_FORMATS ="\"yyyyMMdd\",\"yyyy-MM-dd\"";
	public static  String[] DATE_FORMATS = new String[]{"yyyyMMdd","yyyy-MM-dd"};
    /*
     * values = DEFAULT, MINIMAL
     */
	public static final String MAPPING_STRATEGY_DEFAULT="DEFAULT";
	public static final String MAPPING_STRATEGY_MINIMAL="MINIMAL";
    /*
     * values = BUNDLE, INDIVIDUAL
     */
	
	public static final String CREATION_STRATEGY_BUNDLE="BUNDLE";
	public static final String CREATION_STRATEGY_INDIVIDUAL="INDIVIDUAL";
	public static final String DEFAULT_MAPPING_NAME = "default";
	public static final String LOCAL_SERVER_BASEURL = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3/";
	public static final String BUNDLE_TYPE_MESSAGE = "MESSAGE";

	}
