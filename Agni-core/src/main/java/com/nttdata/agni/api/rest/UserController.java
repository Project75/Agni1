package com.nttdata.agni.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.nttdata.agni.domain.User;
import com.nttdata.agni.exception.DataFormatException;
import com.nttdata.agni.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/fhirtranslator/v1/users")
@Api(tags = {"users"})
public class UserController extends AbstractRestHandler {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a user resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createUser(@RequestBody User user,
                                 HttpServletRequest request, HttpServletResponse response) {
        User createdUser = this.userService.createUser(user);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdUser.getId()).toString());
    
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all users.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<User> getAllUser(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.userService.getAllUsers(page, size);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single user.", notes = "You have to provide a valid user ID.")
    public
    @ResponseBody
    User getUser(@ApiParam(value = "The ID of the user.", required = true)
                             @PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = this.userService.getUser(id);
        checkResourceFound(user);
        //todo: http://goo.gl/6iNAkz
        return user;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a user resource.", notes = "You have to provide a valid user ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateUser(@ApiParam(value = "The ID of the existing user resource.", required = true)
                                 @PathVariable("id") Long id, @RequestBody User user,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.userService.getUser(id));
        if (id != user.getId()) throw new DataFormatException("ID doesn't match!");
        this.userService.updateUser(user);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a user resource.", notes = "You have to provide a valid user ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteUser(@ApiParam(value = "The ID of the existing user resource.", required = true)
                                 @PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.userService.getUser(id));
        this.userService.deleteUser(id);
    }
}
