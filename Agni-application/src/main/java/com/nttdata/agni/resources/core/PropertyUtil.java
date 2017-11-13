/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */
@Component
public class PropertyUtil {

    @Value("#{PropertySplitter.map('${hl7.segment.tofhir}')}")
    Map<String, String> hl7SegToFhirResources;

	/**
	 * @return the hl7SegToFhirResources
	 */
	public Map<String, String> getHl7SegToFhirResources() {
		return hl7SegToFhirResources;
	}

	/**
	 * @param hl7SegToFhirResources the hl7SegToFhirResources to set
	 */
	public void setHl7SegToFhirResources(Map<String, String> hl7SegToFhirResources) {
		this.hl7SegToFhirResources = hl7SegToFhirResources;
	}

}
///hl7.segment.tofhir = PID:Patient,OBR:Observation