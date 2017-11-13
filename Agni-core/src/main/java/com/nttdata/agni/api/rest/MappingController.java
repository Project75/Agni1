package com.nttdata.agni.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.nttdata.agni.domain.MappingList;

import com.nttdata.agni.exception.DataFormatException;
import com.nttdata.agni.service.MappingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/fhirtranslator/v1/mappings")
@Api(tags = {"mappings"})
public class MappingController extends AbstractRestHandler {

    @Autowired
    private MappingService mappingService;

    
    @RequestMapping(value = "/savelist/{mapname}",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a mapping resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createMappingList(@RequestBody List<MappingList> mapping,@PathVariable("mapname") String mapname,
                                 HttpServletRequest request, HttpServletResponse response) {
        //Mapping createdMapping = null;
        		this.mappingService.createMapping(mapname,mapping);
        		//response.setHeader("Location","" );
        response.setHeader("Location", request.getRequestURL().append("/mapping/").append(mapname).toString());
        		//createdMapping.getId()).toString());
        
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all mappings.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<MappingList> getAllMapping(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.mappingService.getAllMappings(page, size);
    }

    @RequestMapping(value = "/{name}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single mapping.", notes = "You have to provide a valid mapping ID.")
    public
    @ResponseBody
    List<MappingList> getMapping(@ApiParam(value = "The name of the mapping.", required = true)
                             @PathVariable("name") String name,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<MappingList> mapping = this.mappingService.findByMapname(name);
        checkResourceFound(mapping);
        //todo: http://goo.gl/6iNAkz
        return mapping;
    }

    @RequestMapping(value = "/{name}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a mapping resource.", notes = "You have to provide a valid mapping ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateMapping(@ApiParam(value = "The name of the existing mapping resource.", required = true)
                                 @PathVariable("name") String name, @RequestBody List<MappingList> mapping,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.mappingService.getMapping(name));
        
        this.mappingService.updateMapping(mapping);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{name}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a mapping resource.", notes = "You have to provide a valid mapping ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteMapping(@ApiParam(value = "The ID of the existing mapping resource.", required = true)
                                 @PathVariable("name") String name, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.mappingService.getMapping(name));
        this.mappingService.deleteMapping(name);
    }
}



