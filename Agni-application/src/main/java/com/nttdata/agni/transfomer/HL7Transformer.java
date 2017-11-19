/**
 * 
 */
package com.nttdata.agni.transfomer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.util.Terser;

import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.domain.TransformRequest;
import com.nttdata.agni.resources.core.AbstractResource;
import com.nttdata.agni.resources.core.BundleImpl;
import com.nttdata.agni.resources.core.PropertyUtil;
import com.nttdata.agni.resources.core.ResourceFactory;
import com.nttdata.agni.resources.utils.TransformUtils;
import com.nttdata.agni.service.MappingService;



/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */
@Service
public class HL7Transformer extends AbstractTransformer {
	
	private static final Logger log = LoggerFactory.getLogger(HL7Transformer.class);
	
	@Autowired
    private MappingService mappingService; 
	
	@Autowired
    private PropertyUtil propertyUtil;
	
	public HL7Transformer() {
		
		// TODO Auto-generated constructor stub
	}
	
	public  String transform(String mapname, String value) {
		log.debug("Mapname: "+mapname);
		TransformRequest transformRequest = new TransformRequest(mapname);
		String response = transform(transformRequest);
		return response;
	}
	
	@Override
    public  String transform(TransformRequest transformRequest) {
    	String fhir=null;
    	HashMap<String, String> dataMap = null;
    	ArrayList<String> HL7SegmentList = null;
    	        
        try {
        	        	
        	Message hapiMsg = getHL7FromPayload(transformRequest.getValue());
        	HL7SegmentList = getHL7SegmentList(hapiMsg);
        	HashMap<String, String> tempMap = getMappingsFromDB(mappingService, transformRequest.getMapname());
        	dataMap = getHL7ValuesMap(hapiMsg, tempMap);
        	//printmap(tempMap);
        	//printmap(dataMap);
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        fhir = createFHIRFromMap(dataMap,HL7SegmentList);
    	return fhir;
    }

    public  Message getHL7FromPayload(String msg) throws HL7Exception{ 
    	@SuppressWarnings("resource")
		HapiContext context = new DefaultHapiContext();
        Parser p = context.getGenericParser();
        Message hapiMsg = p.parse(msg);
        return hapiMsg;
    }
    
    public  ArrayList<String> getHL7SegmentList(Message hapiMsg) throws HL7Exception { 
    	//Need to get segments in HL7 message 
        String strucHL7Segments[]=hapiMsg.getNames();
        ArrayList<String> segmentList = new ArrayList<String>();
        for (int i=0;i<strucHL7Segments.length;i++){
        	if (!(hapiMsg.get(strucHL7Segments[i]).isEmpty())){
        		segmentList.add(strucHL7Segments[i]);
        	}
        }
        return segmentList;
    }
    public  HashMap<String, String> getMappingsFromDB(MappingService mappingService, String mapname){    	
    	HashMap<String, String> mappingMap =new HashMap<String, String>();
        List<MappingList> mappingList = mappingService.findByMapname(mapname);
        List<MappingList> mappingListDefault = mappingService.findByMapname("default");
        
        if (mappingListDefault.size() > 0) {        	
	    	for (MappingList entity : mappingListDefault) {	    		
	    		mappingMap.put(entity.getFHIR(), entity.getHL7());
	        }
    	}
        if (mappingList.size() > 0) {
        	//log.debug("MappingList size is "+mappingList.size());
        	for (MappingList entity : mappingList) {	    		
        		mappingMap.put(entity.getFHIR(), entity.getHL7());	    		
	        }
        } 
    	return mappingMap;
    }
    
    public  HashMap<String, String> getHL7ValuesMap(Message hapiMsg, HashMap<String, String> tempMap) throws HL7Exception{ 
    	Terser terser = new Terser(hapiMsg);
    	HashMap<String, String> dataMap =new HashMap<String, String>();
    	for (Map.Entry<String, String> entry : tempMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String valueHL7= terser.get(value);
            dataMap.put(key, valueHL7);
        }
        
        return dataMap;
    }
    
    public  String createFHIRFromMap(HashMap<String, String> map,ArrayList<String> segmentList)  { 
    	//PropertyUtil propertyUtil =new PropertyUtil();
    	AbstractResource resource =  null;
    	String json = null;
    	ArrayList<AbstractResource> resourceList = new ArrayList<AbstractResource>();
    	for (int i=0;i<segmentList.size();i++){
/*        	if (segmentList.get(i)=="PID"){
        		resource = ResourceFactory.getResource("patient");        		
        	}*/
    		String resourceName = propertyUtil.getHl7SegToFhirResources().get(segmentList.get(i));
    		log.info("Creating resource from factory : "+ resourceName+" for segment "+segmentList.get(i));
    		if (segmentList.get(i).equalsIgnoreCase("MSH")){ resourceName ="messageheader";}
    		if (!((resourceName == null)|| (resourceName.isEmpty())) ){
	        	resource = ResourceFactory.getResource(resourceName);
	        	if (resource !=null){
	        		log.info("Creating resource from factory : "+ resourceName+" for segment "+segmentList.get(i));
	        		map.put(resourceName.toLowerCase()+".guid",resource.getResource().getId());
	        		System.out.println(resourceName.toLowerCase()+".guid="+resource.getResource().getId());
	        		resource.setResourceDataFromMap(map);
	        		resourceList.add(resource);
	        	}
    		}
    	}
    	AbstractResource bundle = (BundleImpl)ResourceFactory.getResource("bundle");
    	log.info("Creating Bundle : ");System.out.println("create bundle");
    	if (bundle !=null){
    	bundle.addResourcesFromList(resourceList);
		 json = ((BundleImpl) bundle).toJson();
    	}
		return json;
		
    }
    
    public void printmap(HashMap<String,String> tempMap){
    	for (Map.Entry<String, String> entry : tempMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Map:key:"+key+":value:"+value);
        }
    }
        
    

}
