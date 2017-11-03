package com.nttdata.agni.service;



import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleType;
import org.hl7.fhir.dstu3.model.Bundle.HTTPVerb;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.Enumerations;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.SimpleQuantity;
import org.hl7.fhir.dstu3.model.Observation.ObservationStatus;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Patient.ContactComponent;
import org.springframework.beans.factory.annotation.Autowired;

import com.nttdata.agni.dao.jpa.MappingListRepository;
import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.domain.TransformRequest;

import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Type;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.EncodingNotSupportedException;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.util.Terser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransformUtils
{
	
    /**
     * A simple example to convert hl7 to fhir
     * 
     * @throws HL7Exception
     * @throws EncodingNotSupportedException
     */
	
	@Autowired
    private static MappingService mappingService; 
 
    public static void main(String[] args) throws Exception {
    	
    	
    	String msg  = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID||001|199||JOHN^DOE||19751027|Female|||street 53^^PHOENIX^AZ^85013^US||(111)222-3333||N|W|||001|||||false||||||false|||||PID.35\r"
                + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|Father||||||ORGANIZATION||Male\r"
                +"PV1||O|5501^0113^02|U|00060292||00276^DELBARE^POL^^DR.|00276^DELBARE^POL^^DR.||1901|||N|01|||||0161782703\r"
                +"PD1|1|2|3|4|5|6|7|8|9|10\r"
                +"OBX|1|TX|3|4|5|6|7|8|9|10|FINAL||13|20170828043002|15|16|17|18|19|20|21|22|23|24|25|26\r"
                +"OBR|1|2|3|4|5||||9|10|11|12|13||15|16||18|19|20|21|20170828042512|23|24|25|26\r"
                +"NTE|1|2|3|4\r";
    	TransformUtils instance =  new TransformUtils();
        System.out.println(instance.convertHL72FHIR(msg,"default"));
        

    }
    

    
    public static String convertHL72FHIR(String hl7,String mapname) {
    	String fhir=null;
    	TransformVO trVO = null;
    	//String mapname = "dh_map_hl7_2_fhir";
        
        try {
        	trVO = HL7Parser(hl7,mapname);
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        fhir = createFHIR(trVO);
    	return fhir;
    }

    public static String convertHL72FHIR(TransformRequest transformRequest) {
    	String fhir=null;
    	TransformVO trVO = null;
    	//String mapname = "dh_map_hl7_2_fhir";
        
        try {
        	trVO = HL7Parser(transformRequest.getValue(),transformRequest.getMapname());
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        fhir = createFHIR(trVO);
    	return fhir;
    }

    
    public static TransformVO HL7Parser(String msg, String mapname) throws HL7Exception{ 
    	TransformVO trVO = new TransformVO();
        HapiContext context = new DefaultHapiContext();
        Parser p = context.getGenericParser();
        Message hapiMsg = p.parse(msg);
        
        Terser terser = new Terser(hapiMsg);
        HashMap<String, String> defaultMap =new HashMap(); 
        HashMap<String, String> tempMap =new HashMap<String, String>();
        
        List<MappingList> mappingItems = mappingService.findByMapname(mapname);
    	for (MappingList entity : mappingItems) {
    		
    		tempMap.put(entity.getFHIR(), entity.getHL7());
        }
    	List<MappingList> mappingItems2 = mappingService.findByMapname("default");
    	for (MappingList entity : mappingItems) {
    		
    		defaultMap.put(entity.getFHIR(), entity.getHL7());
        }
        //String var = null;
        //map.put("givenName_HL7Field",Optional.ofNullable( tempMap.get("patient.name.given") ).orElse( "" ));
        
        String givenName_HL7Field = Optional.ofNullable( tempMap.get("patient.name.given") ).orElse(defaultMap.get("patient.name.given"));
        String familyName_HL7Field = Optional.ofNullable( tempMap.get("patient.name.family") ).orElse(defaultMap.get("patient.name.family"));
        String ID_HL7Field = Optional.ofNullable( tempMap.get("patient.id") ).orElse(defaultMap.get("patient.id"));
        String Gender_HL7Field = Optional.ofNullable( tempMap.get("patient.gender") ).orElse(defaultMap.get("patient.gender"));
        String DOB_HL7Field = Optional.ofNullable( tempMap.get("patient.birthDate") ).orElse(defaultMap.get("patient.birthDate"));
        String AddressLine_HL7Field = Optional.ofNullable( tempMap.get("patient.address.line") ).orElse(defaultMap.get("patient.address.line"));
        String AddressCity_HL7Field = Optional.ofNullable( tempMap.get("patient.address.city") ).orElse(defaultMap.get("patient.address.city"));
        String AddressState_HL7Field = Optional.ofNullable( tempMap.get("patient.address.state") ).orElse(defaultMap.get("patient.address.state"));
        String AddressPostalCode_HL7Field = Optional.ofNullable( tempMap.get("patient.address.postalCode") ).orElse(defaultMap.get("patient.address.postalCode"));
        String AddressCountry_HL7Field = Optional.ofNullable( tempMap.get("patient.address.country") ).orElse(defaultMap.get("patient.address.country"));
        String Telecom_HL7Field = Optional.ofNullable( tempMap.get("patient.telecom.value") ).orElse(defaultMap.get("patient.telecom.value"));
        String MaritalStatus_HL7Field = Optional.ofNullable( tempMap.get("patient.maritalStatus") ).orElse(defaultMap.get("patient.maritalStatus"));
        String Deceased_HL7Field = Optional.ofNullable( tempMap.get("patient.deceased") ).orElse(defaultMap.get("patient.deceased"));
        String Birth_HL7Field = Optional.ofNullable( tempMap.get("patient.multipleBirth") ).orElse(defaultMap.get("patient.multipleBirth"));
        String ContactRel_HL7Field = Optional.ofNullable( tempMap.get("patient.contact.relationship") ).orElse(defaultMap.get("patient.contact.relationship"));
        String ContactName_HL7Field = Optional.ofNullable( tempMap.get("patient.contact.name") ).orElse(defaultMap.get("patient.contact.name"));
        String ContactTel_HL7Field = Optional.ofNullable( tempMap.get("patient.contact.telecom") ).orElse(defaultMap.get("patient.contact.telecom"));
        String ContactAddress_HL7Field = Optional.ofNullable( tempMap.get("patient.contact.address") ).orElse(defaultMap.get("patient.contact.address"));
        String ContactGender_HL7Field = Optional.ofNullable( tempMap.get("patient.contact.gender") ).orElse(defaultMap.get("patient.contact.gender"));
        String ContactOrg_HL7Field = Optional.ofNullable( tempMap.get("patient.contact.organization") ).orElse(defaultMap.get("patient.contact.organization"));
        String GeneralPractitioner_HL7Field = Optional.ofNullable( tempMap.get("patient.generalPractitioner") ).orElse(defaultMap.get("patient.generalPractitioner"));
        String Link_HL7Field = Optional.ofNullable( tempMap.get("patient.link.other") ).orElse(defaultMap.get("patient.link.other"));
        
        String ObservationID_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.identifier"));
        String ObservationStatus_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.status"));
        String ObservationCode_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.code"));
        String ObservationSubject_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.subject"));
        String ObservationEffective_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.effective"));
        String ObservationIssued_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.issued"));
        String ObservationPerformer_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.performer"));
        String ObservationValue_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.value"));
        String ObservationInterpretation_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.interpretation"));
        String ObservationComment_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.comment"));
        String ObservationBodySite_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.bodySite"));
        String ObservationMethod_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.method"));
        String ObservationDevice_HL7Field = Optional.ofNullable( tempMap.get("patient") ).orElse(defaultMap.get("observation.device"));             
        
        
        trVO.setFamilyName(terser.get(familyName_HL7Field));
        trVO.setGivenName(terser.get(givenName_HL7Field));
        trVO.setId(terser.get(ID_HL7Field));
        trVO.setgender(terser.get(Gender_HL7Field));
        trVO.setDOB(terser.get(DOB_HL7Field));
        trVO.setAddressLine(terser.get(AddressLine_HL7Field));
        trVO.setAddressCity(terser.get(AddressCity_HL7Field));
        trVO.setAddressState(terser.get(AddressState_HL7Field));
        trVO.setAddressPostalCode(terser.get(AddressPostalCode_HL7Field));
        trVO.setAddressCountry(terser.get(AddressCountry_HL7Field));
        trVO.setTelecom(terser.get(Telecom_HL7Field));
        trVO.setMaritalStatus(terser.get(MaritalStatus_HL7Field));
        trVO.setDeceased(terser.get(Deceased_HL7Field));
        trVO.setBirth(terser.get(Birth_HL7Field));
        trVO.setContactRel(terser.get(ContactRel_HL7Field));
        trVO.setContactName(terser.get(ContactName_HL7Field));
        trVO.setContactTel(terser.get(ContactTel_HL7Field));
        trVO.setContactAddress(terser.get(ContactAddress_HL7Field));
        trVO.setContactGender(terser.get(ContactGender_HL7Field));
        trVO.setContactOrg(terser.get(ContactOrg_HL7Field));
        trVO.setGeneralPractitioner(terser.get(GeneralPractitioner_HL7Field));
        trVO.setLink(terser.get(Link_HL7Field));
        
        trVO.setObservationID(terser.get(ObservationID_HL7Field));
        trVO.setObservationStatus(terser.get(ObservationStatus_HL7Field));
        trVO.setObservationCode(terser.get(ObservationCode_HL7Field));
        trVO.setObservationSubject(terser.get(ObservationSubject_HL7Field));    
        trVO.setObservationEffective(terser.get(ObservationEffective_HL7Field));
        trVO.setObservationIssued(terser.get(ObservationIssued_HL7Field));
        trVO.setObservationPerformer(terser.get(ObservationPerformer_HL7Field));
        trVO.setObservationValue(terser.get(ObservationValue_HL7Field));
        trVO.setObservationInterpretation(terser.get(ObservationInterpretation_HL7Field));
        trVO.setObservationComment(terser.get(ObservationComment_HL7Field));
        trVO.setObservationBodySite(terser.get(ObservationBodySite_HL7Field));
        trVO.setObservationMethod(terser.get(ObservationMethod_HL7Field));
        trVO.setObservationDevice(terser.get(ObservationDevice_HL7Field));
        
        return trVO;    
    }
        
    public static String createFHIR(TransformVO trVO)  {    
        FhirContext ctx = FhirContext.forDstu3();
        
        Patient patient = new Patient();
        Observation observation = new Observation();
       
        // you can use the Fluent API to chain calls
        // see http://hapifhir.io/doc_fhirobjects.html
        patient.addName().setUse(HumanName.NameUse.OFFICIAL)
                .addPrefix("Mr").setFamily(trVO.getFamilyName()).addGiven(trVO.givenName);
        patient.addIdentifier()
                .setSystem("http://ns.electronichealth.net.au/id/hi/ihi/1.0")
                .setValue(trVO.getId());
        patient.setGender(Enumerations.AdministrativeGender.valueOf(trVO.getgender().toUpperCase()));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
        try {
			patient.setBirthDate(formatter.parse(trVO.getDOB()));
		} catch (ParseException e) {
			e.printStackTrace();
		}       
        patient.addAddress().addLine(trVO.getAddressLine()).setCity(trVO.getAddressCity()).setState(trVO.getAddressState())
        		.setPostalCode(trVO.getAddressPostalCode()).setCountry(trVO.getAddressCountry());
        patient.addTelecom().setValue(trVO.getTelecom());
        
        CodeableConcept MaritalStatus = new CodeableConcept();
        MaritalStatus.setText(trVO.getMaritalStatus()); 
        patient.setMaritalStatus(MaritalStatus); 
                
		BooleanType Deceased = new BooleanType(Boolean.valueOf(trVO.getDeceased()));
		patient.setDeceased(Deceased);
		
		BooleanType Birth = new BooleanType(Boolean.valueOf(trVO.getBirth()));
		patient.setMultipleBirth(Birth);
		
		patient.addContact().addRelationship(new CodeableConcept().setText(trVO.getContactRel())).setName(new HumanName().addGiven(trVO.getContactName()))
		       .addTelecom(new ContactPoint().setValue(trVO.getContactTel())).setAddress(new ContactComponent().getAddress().addLine(trVO.getContactAddress()))
		       .setGender(Enumerations.AdministrativeGender.valueOf(trVO.getContactGender().toUpperCase())).setOrganization(new Reference().setReference(trVO.getContactOrg()));
		       
		patient.addGeneralPractitioner().setReference(trVO.getGeneralPractitioner());
		patient.addLink().setId(trVO.getLink());
		
		patient.setId(IdDt.newRandomUuid());
		
		observation.addIdentifier().setValue(trVO.getObservationID());
		observation.setStatus(ObservationStatus.valueOf(trVO.getObservationStatus()));
		observation.setCode(new CodeableConcept().setText(trVO.getObservationCode()));
		observation.setSubject(new Reference().setReference(trVO.getObservationSubject()));
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		try {
			observation.setEffective(new DateTimeType(formatter1.parse(trVO.getObservationEffective())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			observation.setIssued(formatter1.parse(trVO.getObservationIssued()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		observation.addPerformer().setReference(trVO.getObservationPerformer());
		observation.setValue(new CodeableConcept().setText(trVO.getObservationValue()));
		observation.setInterpretation(new CodeableConcept().setText(trVO.getObservationInterpretation()));
		observation.setComment(trVO.getObservationComment());
		observation.setBodySite(new CodeableConcept().setText(trVO.getObservationBodySite()));
		observation.setMethod(new CodeableConcept().setText(trVO.getObservationMethod()));
		observation.setDevice(new Reference().setReference(trVO.getObservationDevice()));
		
		Bundle bundle = new Bundle();
		bundle.setType(BundleType.TRANSACTION);
		
		bundle.addEntry()
		   .setFullUrl(patient.getId()).setResource(patient).getRequest().setUrl("Patient").setMethod(HTTPVerb.POST);
		
		bundle.addEntry()
		   .setResource(observation).getRequest().setUrl("Observation").setMethod(HTTPVerb.POST);
		
        // create a new XML parser and serialize our Patient object with it
        String encoded = ctx.newXmlParser().setPrettyPrint(true)
                .encodeResourceToString(bundle);

        System.out.println(encoded);
        return encoded;
        }
}
