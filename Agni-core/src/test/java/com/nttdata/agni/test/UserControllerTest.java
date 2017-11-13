package com.nttdata.agni.test;

/**
 * Uses JsonPath: http://goo.gl/nwXpb, Hamcrest and MockMVC
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.agni.Application;
import com.nttdata.agni.api.rest.UserController;
import com.nttdata.agni.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class UserControllerTest {

    private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/fhirtranslator/v1/users/[0-9]+";

    @InjectMocks
    UserController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //@Test
    public void shouldHaveEmptyDB() throws Exception {
        mvc.perform(get("/fhirtranslator/v1/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldCreateRetrieveDelete() throws Exception {
        User r1 = mockUser("shouldCreateRetrieveDelete");
        byte[] r1Json = toJson(r1);

        //CREATE
        MvcResult result = mvc.perform(post("/fhirtranslator/v1/users")
                .content(r1Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern(RESOURCE_LOCATION_PATTERN))
                .andReturn();
        long id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());

        //RETRIEVE
        mvc.perform(get("/fhirtranslator/v1/users/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) id)))
                .andExpect(jsonPath("$.name", is(r1.getName())))
                .andExpect(jsonPath("$.city", is(r1.getCity())))
                .andExpect(jsonPath("$.description", is(r1.getDescription())))
                .andExpect(jsonPath("$.rating", is(r1.getRating())));

        //DELETE
        mvc.perform(delete("/fhirtranslator/v1/users/" + id))
                .andExpect(status().isNoContent());

        //RETRIEVE should fail
        mvc.perform(get("/fhirtranslator/v1/users/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        //todo: you can test the 404 error body too.

/*
JSONAssert.assertEquals(
  "{foo: 'bar', baz: 'qux'}",
  JSONObject.fromObject("{foo: 'bar', baz: 'xyzzy'}"));
 */
    }

    @Test
    public void shouldCreateAndUpdateAndDelete() throws Exception {
        User r1 = mockUser("shouldCreateAndUpdate");
        byte[] r1Json = toJson(r1);
        //CREATE
        MvcResult result = mvc.perform(post("/fhirtranslator/v1/users")
                .content(r1Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern(RESOURCE_LOCATION_PATTERN))
                .andReturn();
        long id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());

        User r2 = mockUser("shouldCreateAndUpdate2");
        r2.setId(id);
        byte[] r2Json = toJson(r2);

        //UPDATE
        result = mvc.perform(put("/fhirtranslator/v1/users/" + id)
                .content(r2Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        //RETRIEVE updated
        mvc.perform(get("/fhirtranslator/v1/users/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) id)))
                .andExpect(jsonPath("$.name", is(r2.getName())))
                .andExpect(jsonPath("$.city", is(r2.getCity())))
                .andExpect(jsonPath("$.description", is(r2.getDescription())))
                .andExpect(jsonPath("$.rating", is(r2.getRating())));

        //DELETE
        mvc.perform(delete("/fhirtranslator/v1/users/" + id))
                .andExpect(status().isNoContent());
    }


    /*
    ******************************
     */

    private long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }


    private User mockUser(String prefix) {
        User r = new User();
        r.setCity(prefix + "_city");
        r.setDescription(prefix + "_description");
        r.setName(prefix + "_name");
        r.setRating(new Random().nextInt(6));
        return r;
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

    // match redirect header URL (aka Location header)
    private static ResultMatcher redirectedUrlPattern(final String expectedUrlPattern) {
        return new ResultMatcher() {
            public void match(MvcResult result) {
                Pattern pattern = Pattern.compile("\\A" + expectedUrlPattern + "\\z");
                assertTrue(pattern.matcher(result.getResponse().getRedirectedUrl()).find());
            }
        };
    }

}
