/**
 * 
 */
package com.nttdata.agni.resources.utils;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */
@Component @Getter @Setter
public class PropertyUtil {

    //@Value("#{PropertySplitter.map('${hl7.segment.tofhir.old}')}")
    //Map<String, String> hl7SegToFhirResources;
    
    @Value("#{PropertySplitter.mapOfList('${hl7.segment.tofhir}')}")
    Map<String, List<String>> hl7SegToFhirResources;
	
    //@Value("#{PropertySplitter.mapOfList('${hl7.msgtype.tofhir}')}")
    //Map<String, List<String>> hl7MsgTypeToFhirResources;

}
///hl7.segment.tofhir = PID:Patient,OBR:Observation