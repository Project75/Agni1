/**
 * 
 */
package com.nttdata.agni.resources.utils;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javafx.util.Pair;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */



@Component @Getter @Setter
public class PropertyUtil {
	
	
	PropertySplitter propertySplitter;
	

	
	
	
    //@Value("#{PropertySplitter.map('${hl7.segment.tofhir.old}')}")
    //Map<String, String> hl7SegToFhirResources;
    
    @Value("#{PropertySplitter.mapOfList('${hl7.segment.tofhir}')}")
    //@Value("${hl7.segment.tofhir}")
    Map<String, List<String>> hl7SegToFhirResources;

    @Value("#{PropertySplitter.mapOfListOfProperties('${test.resources.files}')}")
    Map<String, List<Pair<String,String>>> resourceTestFiles;
    
    @Value("#{PropertySplitter.list('${test.resources.folder}')}")
    List<String> resourceTestFolder;

}
