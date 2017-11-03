package com.nttdata.agni.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.nttdata.agni.domain.TransformRequest;


import com.nttdata.agni.service.TransformUtils;

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

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "HL7 to FHIR.", notes = "Returns FHIR resource.")
    public String hl72fhir(@RequestBody TransformRequest transformRequest,
                                 HttpServletRequest request, HttpServletResponse response) {
        return TransformUtils.convertHL72FHIR(transformRequest);
        
    }



}



