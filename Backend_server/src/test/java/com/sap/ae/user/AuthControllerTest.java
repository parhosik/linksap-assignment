package com.sap.ae.user;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ae.model.common.User;
import com.sap.ae.repository.common.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    UserRepository userRepository;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }





    @Test
    public void nameIsNotNull() throws Exception {
        User user = new User();
        user.setName("tempPassword");
        user.setPassword("tempPassword");

        setUp();
        String uri = "/auth/register";

        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);



    }

    @Test
    public void userNameIsNotNull() throws Exception {
        User user = new User();
        user.setName("tempPassword");
        user.setPassword("tempPassword");

        setUp();
        String uri = "/auth/register";

        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);



    }

    @Test
    public void passwordIsNotNull() throws Exception {
        User user = new User();
        user.setName("tempPassword");
        user.setPassword("tempPassword");

        setUp();
        String uri = "/auth/register";

        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);



    }

    @Test
    public void userAlreadyExistTest() throws Exception {
        User user = new User();
        user.setName("sikandar");
        user.setEmail("sikandar@gmail.com");
        user.setPassword("tempPassword");



        Mockito.when(userRepository.findOneByEmail("sikandar@gmail.com")).thenReturn(user);
        setUp();
        String uri = "/auth/register";

        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);


    }




    @Test
  public void registerUserTest() throws Exception {
        User user = new User();
        user.setName("sikandar");
        user.setEmail("sikandar@gmail.com");
        user.setPassword("tempPassword");



        Mockito.when(userRepository.findOneByEmail("sikandar@gmail.com")).thenReturn(null);
        setUp();
        String uri = "/auth/register";

        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);


    }















}
