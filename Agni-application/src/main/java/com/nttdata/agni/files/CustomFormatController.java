package com.nttdata.agni.files;


import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nttdata.agni.api.rest.AbstractRestHandler;
import com.nttdata.agni.transfomer.HL7Transformer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */
@RestController
@RequestMapping(value = "/fhirtranslator/v1/custom2fhir")
@Api(tags = {"custom2fhir"})
public class CustomFormatController extends AbstractRestHandler{
	@Autowired
    private CustomTransformer transformer;
    

    @RequestMapping(value = "/demo/{mapname}",
            method = RequestMethod.POST,
            consumes = {"text/plain"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "HL7 to FHIR.", notes = "Returns FHIR resource bundle.")
    public String custom2fhir(@RequestBody String payload,@PathVariable("mapname") String mapname,
                                 HttpServletRequest request, HttpServletResponse response) {
    	//log.info("mapname"+mapname);
        return transformer.transform(mapname,payload);
        
    }
 
    @RequestMapping(value = "/etl/{mapname}",
            method = RequestMethod.POST,
            consumes = {"text/plain"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "HL7 to FHIR.", notes = "Returns FHIR resource bundle.")
    public String custom2fhirServer(@RequestBody String payload,@PathVariable("mapname") String mapname,
                                 HttpServletRequest request, HttpServletResponse response) {
    	//log.info("mapname"+mapname);
        return transformer.transform(mapname,payload);
        
    }
  
    //TODO: FILE Upload Controller
    //@Autowired
    //ServletContext context;
    //private final StorageService storageService;
    /*@Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }*/
    
    /*@RequestMapping(value = "/fileupload", headers=("content-type=multipart/*"), method = RequestMethod.POST)
    public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile) {
     FileInfo fileInfo = new FileInfo();
     HttpHeaders headers = new HttpHeaders();
     if (!inputFile.isEmpty()) {
      try {
       String originalFilename = inputFile.getOriginalFilename();
       File destinationFile = new File(context.getRealPath("/WEB-INF/uploaded")+  File.separator + originalFilename);
       inputFile.transferTo(destinationFile);
       fileInfo.setFileName(destinationFile.getPath());
       fileInfo.setFileSize(inputFile.getSize());
       headers.add("File Uploaded Successfully - ", originalFilename);
       return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
      } catch (Exception e) {    
       return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
      }
     }else{
      return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
     }
    }*/
}
