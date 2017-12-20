package com.nttdata.agni.api.rest;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.domain.TransformRequest;
import com.nttdata.agni.service.MappingService;
import com.nttdata.agni.transfomer.HL7Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/fhirtranslator/v1/hl72fhir")
@Api(tags = {"hl72fhir"})
public class HL72FHIRController extends AbstractRestHandler {

    @Autowired
    private HL7Transformer transformer;


    
    @RequestMapping(value = "/{mapname}",
            method = RequestMethod.POST,
            consumes = {"text/plain"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "HL7 to FHIR.", notes = "Returns FHIR resource bundle.")
    public String hl72fhir2(@RequestBody String payload,@PathVariable("mapname") String mapname,
                                 HttpServletRequest request, HttpServletResponse response) {
    	log.info("mapname"+mapname);
        return transformer.transform(mapname,payload);
        
    }

}



