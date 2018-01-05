/**
 * 
 */
package com.nttdata.agni.transfomer;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.EncodingNotSupportedException;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import ca.uhn.hl7v2.validation.builder.support.NoValidationBuilder;
import ca.uhn.hl7v2.validation.impl.NoValidation;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.domain.TransformRequest;
import com.nttdata.agni.resources.core.AbstractResource;
import com.nttdata.agni.resources.core.BundleImpl;
import com.nttdata.agni.resources.core.ResourceFactory;
import com.nttdata.agni.resources.utils.HL7Parser;
import com.nttdata.agni.resources.utils.MapConfig;
import com.nttdata.agni.resources.utils.PropertyUtil;
import com.nttdata.agni.resources.utils.TransformMap;
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
	String errortext;
	@Autowired
    private MappingService mappingService; 
	
	@Autowired
    private PropertyUtil propertyUtil;
	
	public HL7Transformer() {
		
		// TODO Auto-generated constructor stub
	}
	
	public  String transform(String mapname, String value) {
		log.debug("Mapname: "+mapname);
		TransformRequest transformRequest = new TransformRequest(mapname,value);
		String response = transform(transformRequest);
		return response;
	}
	
	@Override
    public  String transform(TransformRequest transformRequest) {
    	String fhir=null;
    	TransformMap dataMap = null;
    	ArrayList<String> HL7SegmentList = null;
    	     
        try {
        	        	
        	Message hapiMsg = getHL7FromPayload(transformRequest.getValue());
        	if (hapiMsg != null){
        		HL7SegmentList = getHL7SegmentList(hapiMsg);
        		ListMultimap<String, String> tempMap = getMappingsFromDB(mappingService, transformRequest.getMapname(),transformRequest.getMappingStrategy());
        		dataMap = getHL7ValuesMap(hapiMsg, tempMap);
        		fhir = createFHIRFromMap(dataMap,HL7SegmentList);
        		}
        	
        } catch (EncodingNotSupportedException e) {
            e.printStackTrace();
            errortext = "HL7 Validation failure - "+e.getMessage();
        
        } catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errortext = e.getMessage();
		}
        if (fhir == null)
        	fhir = errortext;
    	return fhir;
    }

    public  Message getHL7FromPayload(String msg) throws EncodingNotSupportedException,HL7Exception{ 
    	@SuppressWarnings("resource")
		HapiContext context = new DefaultHapiContext();
    	//context.setValidationRuleBuilder(new NoValidationBuilder());
    	context.setValidationContext(new NoValidation());
        Parser p = context.getGenericParser();
        Message hapiMsg =  p.parse(msg);
        
        //PipeParser pipeParser = new PipeParser();
        //Message hapiMsg = pipeParser.parse(msg);
        
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
    public  ListMultimap<String, String> getMappingsFromDB(MappingService mappingService, String mapname,String mappingStrategy){
		if (mappingStrategy=="MINIMAL")
			return getMappingsFromDBWithMiniMal(mappingService, mapname);
		else
			return getMappingsFromDBWithDefault(mappingService, mapname);  
    
    }
    public  ListMultimap<String, String> getMappingsFromDBWithMiniMal(MappingService mappingService, String mapname){    	
    	ListMultimap<String, String> newMappings = ArrayListMultimap.create();
        List<MappingList> mappingList = mappingService.findByMapname(mapname);
        if (mappingList.size() > 0) {
        	//log.debug("MappingList size is "+mappingList.size());
        	for (MappingList entity : mappingList) {	    		
        		newMappings.put(entity.getFhir(), entity.getHl7());	    		
	        }
        }
        return newMappings;
     }	   
    public  ListMultimap<String, String> getMappingsFromDBWithDefault(MappingService mappingService, String mapname){    	
    	ListMultimap<String, String> mappingMap = ArrayListMultimap.create();
    	ListMultimap<String, String> defMappings = ArrayListMultimap.create();
    	ListMultimap<String, String> newMappings = ArrayListMultimap.create();
        List<MappingList> mappingList = mappingService.findByMapname(mapname);
        List<MappingList> mappingListDefault = mappingService.findByMapname("default");
        
        if (mappingListDefault.size() > 0) {        	
	    	for (MappingList entity : mappingListDefault) {	    		
	    		defMappings.put(entity.getFhir(), entity.getHl7());
	        }
    	}
        if (mappingList.size() > 0) {
        	//log.debug("MappingList size is "+mappingList.size());
        	for (MappingList entity : mappingList) {	    		
        		newMappings.put(entity.getFhir(), entity.getHl7());	    		
	        }
        } 
        //newMappings.putAll(arg0)
        Map<String, Collection<String>> defMap = defMappings.asMap();
        Map<String, Collection<String>> newMap = newMappings.asMap();
        //newMap.putAll(defMap);
              	
    	for (Map.Entry<String, Collection<String>> entry : defMap.entrySet()) {	    		
    		String key = entry.getKey();
    		Collection<String> value = entry.getValue();
            //List<String> valueHL7 = hL7Map.getAll(value);
    		newMap.putIfAbsent(key, value);
        }
	
    
        
        for (Map.Entry<String, Collection<String>> entry : newMap.entrySet()) {	    		
    		String key = entry.getKey();
    		Collection<String> value = entry.getValue();
            //List<String> valueHL7 = hL7Map.getAll(value);
    		mappingMap.putAll(key, value);
    		
        }
        
        
    	return mappingMap;
    }
    
    public  TransformMap getHL7ValuesMap(Message hapiMsg, ListMultimap<String, String> mappingMap) throws HL7Exception{ 
    	HL7Parser HL7Parser =new HL7Parser();
    	TransformMap hL7Map =  null;
    	try{
    		hL7Map = HL7Parser.getHL7Map(hapiMsg);
        } catch (HL7Exception e) {
			// TODO Auto-generated catch block
        	errortext = e.getMessage();        	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errortext = e.getMessage();
			e.printStackTrace();
		}
    	
    	
    	TransformMap dataMap =new TransformMap();
    	for (String key : mappingMap.keySet()) {
            List<String> values = mappingMap.get(key);
            //String value = entry.getValue();
            List<String> valueHL7 = hL7Map.getAll(values);
            dataMap.putAll(key, valueHL7);
        }
        //print maps
    	System.out.println("hL7Map:"+hL7Map.getMap().toString());
    	//System.out.println("dataMap:"+dataMap.getMap().toString());
    	
        return dataMap;
    }
    
    public  String createFHIRFromMap(TransformMap map,ArrayList<String> segmentList)  { 
    	//PropertyUtil propertyUtil =new PropertyUtil();
    	AbstractResource resource =  null;
    	ArrayList<AbstractResource> resourceList = new ArrayList<AbstractResource>();
    	for (int i=0;i<segmentList.size();i++){
/*        	if (segmentList.get(i)=="PID"){
        		resource = ResourceFactory.getResource("patient");        		
        	}*/
    		//String resourceName = propertyUtil.getHl7SegToFhirResources().get(segmentList.get(i));
    		List<String> resourceNames = propertyUtil.getHl7SegToFhirResources().get(segmentList.get(i));
    		//log.info
    		for (String resourceName:Optional.ofNullable(resourceNames).orElse(Collections.emptyList())){
	    		System.out.println("Creating resource from factory : "+ resourceName+" for segment "+segmentList.get(i));
	    		if (segmentList.get(i).equalsIgnoreCase("MSH")){ resourceName ="messageheader";}
	    		if (!((resourceName == null)|| (resourceName.isEmpty())) ){
		        	resource = ResourceFactory.getResource(resourceName);
		        	if (resource !=null){
		        		System.out.println("Creating resource from factory : "+ resourceName+" for segment "+segmentList.get(i));
		        		resource.setResourceDataFromMap(map);
		        		resourceList.add(resource);
		        		//System.out.println("Haren1"+resourceName+"\n"+ TransformUtils.resourceToJson(resource));
		        	}
	    		}
    		}
    	}
    	BundleImpl bundle = (BundleImpl)ResourceFactory.getResource("bundle");
    	System.out.println("Creating Bundle : ");
    	bundle.addResourcesFromList(resourceList);
		String json = bundle.toJson();//TransformUtils.resourceToJson(bundle);
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
