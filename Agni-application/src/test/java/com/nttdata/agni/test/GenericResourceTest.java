package com.nttdata.agni.test;


import com.nttdata.agni.resources.utils.PropertyUtil;
import com.nttdata.agni.resources.utils.ResourceFactory;
import com.nttdata.agni.resources.utils.TransformMap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.resources.core.*;
import com.nttdata.agni.resources.utils.TransformUtils;
import com.nttdata.agni.transfomer.*;

import org.junit.Before;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import javafx.util.Pair;
import static org.junit.Assert.assertEquals;
import org.slf4j.LoggerFactory;

public class GenericResourceTest extends TestUtils{
	
	 
    private HL7Transformer hl7Transformer;
    String propertyFile = "testdata.conf"; 
    
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
     
    public  String transformFromFiles(String resourceName) {
        String fileHl7="",fileFhir="",result="Success";
        List<Pair<String, String>> files = null;
		try {
			files = getTestFilesList2(resourceName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for (Pair<String, String> file:files){
        	//String folder = "testdata/"; 
        	System.out.println("pair:"+file.getKey()+file.getValue());
        	fileHl7= file.getKey();
        	fileFhir = file.getValue();
        	try {
        		System.out.println("fileHl7:"+fileHl7);
				String fhirOutput = transform(resourceName, getPayloadFromFile(fileHl7));
				
				String fhirInput = getPayloadFromFile(fileFhir);
				System.out.println("fhirOutput:"+fhirOutput);
				savePayloadToFile(fileHl7,fhirOutput);
				assertEquals("File is not equal", fhirInput,fhirOutput);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	return result;
    	
    }
    
    public  String transform(String resourceName) {
    	              
    	return transform(resourceName, getTestPayload());
    	
    }
    public  String transform(String resourceName,String payload) {
    	System.out.println("***************************");
    	System.out.println("Processing for "+resourceName);
    	TransformMap hL7Map = null, dataMap = null;
    	    	  
        try {        	        	
        	
			Message hapiMsg = hl7Transformer.getHL7FromPayload(payload);
 
        	ListMultimap<String, String> tempMap = getMappings();
        	hL7Map = hl7Transformer.getHL7ValuesMap(hapiMsg);
        	dataMap = hl7Transformer.buildFHIRKeystoHL7ValuesMap(hL7Map,tempMap);
        	
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        AbstractResource res = createFHIRResource(dataMap,resourceName);
        //print json 
        System.out.println(res.toString());
        return getResourceAsJson(res);
        
    }
	private AbstractResource createFHIRResource(TransformMap dataMap,String resourceName) {
		// TODO Auto-generated method stub
    	AbstractResource resource =  null;
    	if (resourceName !=null){
        	resource = ResourceFactory.getResource(resourceName);
        	if (resource !=null){
        		resource.setResourceName(resourceName);
        		resource.setResourceDataFromMap(dataMap);
        		//resourceList.add(resource);
        	}else
        		System.out.println("<ERROR>"+resourceName);

     	}
     		
     	return resource;

	}
	private String getResourceAsJson(AbstractResource resource) {
		return TransformUtils.resourceToJson(resource);
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
	public  String getPayloadFromFile(String fileName) throws IOException{
        String filePath = new File(getClass().getClassLoader().getResource(fileName).getFile()).getAbsolutePath();

        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return content ;
	}	
	public List<Pair<String,String>> getTestFilesList(String resourceName){
		//Map<String,String> map = null;
		PropertyUtil propertyUtil=  new PropertyUtil();
		 List<Pair<String, String>> listofPairs = propertyUtil.getResourceTestFiles().get(resourceName);
				
		return listofPairs;
	}
	public  void savePayloadToFile(String fileName,String payload) throws IOException {
        
        String parentFilePath = new File(getClass().getClassLoader().getResource(fileName).getFile()).getAbsolutePath();
        //File parentDirectory = new File(getClass().getClassLoader().getResource(fileName).getFile()).getParentFile();
        //File outfile=new File(parentDirectory, fileName+"output.txt");
        //String filePath = outfile.getAbsolutePath();

        String filePath = parentFilePath+"_output.txt";
        System.out.println("filePath:"+filePath);
        Files.write(Paths.get(filePath), payload.getBytes());
			
		
        
	}
	public  List<Pair<String, String>> getTestFilesList2(String resourceName) throws IOException{
		  
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(propertyFile)) {
		    props.load(resourceStream);
		}
		Set<String> keys = props.stringPropertyNames();
	    for (String key : keys) {
	      System.out.println(key + " : " + props.getProperty(key));
	    }	
    	Pair<String, String> pair = null;
    	List<Pair<String,String>> listOut = new ArrayList<Pair<String,String>>();
    	String propertyValue=props.getProperty(resourceName);
    	if (propertyValue !=null){
    	List<String> list1=Splitter.on(Pattern.quote(";")).omitEmptyStrings().trimResults().splitToList(propertyValue);
    	for (String item : list1) {
    		//String item=props.getProperty(key);
    		//System.out.println(key + " : " + item);
    		String[] arr = item.split(Pattern.quote(","));
    		if (arr.length >=2){
    			System.out.println( " zz: " + arr[0]+arr[1]);
    			pair = new Pair<String,String>(arr[0],arr[1]);
    		}
    		listOut.add(pair);
    	}
    	}
    	return listOut;
        
	}
	
}
