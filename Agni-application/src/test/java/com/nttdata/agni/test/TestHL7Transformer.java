package com.nttdata.agni.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;

import com.google.common.base.Splitter;
import com.nttdata.agni.dao.jpa.MappingListRepository;
import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.files.CustomTransformer;
import com.nttdata.agni.resources.utils.PropertyUtil;
import com.nttdata.agni.service.MappingService;
import com.nttdata.agni.transfomer.HL7Transformer;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class TestHL7Transformer {
	   private HL7Transformer hL7Transformer;
	    @Mock
	    MappingListRepository MappingListRepository;
	    
	    @InjectMocks
	    MappingService mappingService;
	    
	    @Mock
	    private PropertyUtil propertyUtil;
	    
	    //@InjectMocks
	    //private com.nttdata.agni.resources.utils.PropertySplitter PropertySplitter;
	    
	    private String mapName="test1";
	    
	    List<MappingList> mapping;

	    @Before
	    public void initTests() {
	        MockitoAnnotations.initMocks(this);
	        mapName = "test1";

    		hL7Transformer = new HL7Transformer();
    		hL7Transformer.setMappingService(mappingService);
    		hL7Transformer.setPropertyUtil(propertyUtil);
    		
    	  	Logger Logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ca.uhn.fhir");    	  			
    	    Logger.setLevel(Level.WARN);
    	    Logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("org.springframework.web.client");  			
    	    Logger.setLevel(Level.WARN);
//org.springframework.web.client.
	    }
	@Test
    public void testPatient() throws Exception {
    	String out=transform();
    	//System.out.println("FHIR OUTPUT \n:"+out);
     }

	private String transform() {
		// TODO Auto-generated method stub
		List<MappingList> mapping = TestUtils.mockMappings();
				//new ArrayList<MappingList>();
		System.out.println("Start Test ===============");

		when(MappingListRepository.findByMapname(mapName)).thenReturn(mapping);
		//String key = null;
		Map<String, List<String>> mapOfList = mapOfList();
		System.out.println("mapOfList size = "+mapOfList.size());
		String payload = TestUtils.getTestPayload2();
		
		ArrayList<String> hL7SegmentList = null;
		try {
			Message hapiMsg = hL7Transformer.getHL7FromPayload(payload);
			hL7SegmentList = hL7Transformer.getHL7SegmentList(hapiMsg);
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hL7SegmentList size = "+hL7SegmentList.size());
		//for (String key:hL7SegmentList){
		when(propertyUtil.getHl7SegToFhirResources()).thenReturn(mapOfList);
		//}

		String fhir = hL7Transformer.transform(mapName, payload);
		System.out.println("Result==================");
		System.out.println(fhir);
		return fhir;
	}

	
	public Map<String, List<String>> mapOfList() {
		String property = "A60:AllergyIntolerance;AL1:AllergyIntolerance;CON:Consent;DFT:ChargeItem;ERR:OperationOutcome;FT1:ChargeItem;GOL:Goal;IAM:AllergyIntolerance,DetectedIssue;IN1:Coverage;MDM:DocumentManifest,DocumentReference;MFE:DataElement;MFN:DataElement;MSH:MessageHeader;NK1:RelatedPerson;O11:VisionPrescription;OBR:Observation;OBX:DiagnosticReport;ORC:SupplyRequest;P01:Account;P03:Account;P05:Account;P10:Account;PC1:Condition;PID:Patient;PR1:Procedure,ProcedureRequest;PRB:Condition;PRD:Practitioner,PractitionerRole;PV1:Encounter;R01:Media;RAS:MedicationAdministration;RDE:Medication,MedicationRequest;RDS:MedicationStatement;RF1:ReferralRequest;ROL:Condition;RXA:Medication,MedicationAdministration;RXE:MedicationRequest;SCH:Appointment;SIU:Appointment;SPM:Specimen;SRR:AppointmentResponse;V04:Immunization,ImmunizationRecommendation";
        Map<String, String> map = this.map(property, ";");

        Map<String, List<String>> mapOfList = new HashMap<>();
        for (Entry<String, String> entry : map.entrySet()) {
            mapOfList.put(entry.getKey(), this.list(entry.getValue(),","));
        }

        return mapOfList;
    }
    private List<String> list(String property, String splitter) {
        return Splitter.on(splitter).omitEmptyStrings().trimResults().splitToList(property);
    }

    private Map<String, String> map(String property, String splitter) {
        return Splitter.on(splitter).omitEmptyStrings().trimResults().withKeyValueSeparator(":").split(property);
    }
}
