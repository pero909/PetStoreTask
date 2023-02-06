package com.example.petstore;

import com.example.petstore.Service.impl.PetServiceImp;
import com.example.petstore.Service.impl.UserServiceImp;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PetStoreApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    PetServiceImp petServiceImp;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    @Order(1)
    public void testCreateUsers() throws Exception {
       MockHttpServletRequestBuilder createUsersReq=MockMvcRequestBuilders
               .get("/api/createUsers").accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(createUsersReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.length()").value(10));


    }
    @Test
    @Order(2)
    public void testCreatePets() throws Exception{
        MockHttpServletRequestBuilder createUsersReq=MockMvcRequestBuilders
                .get("/api/createPets").accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(createUsersReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.length()").value(20));
    }
    @Test
    @Order(3)
    public void testListUsers() throws Exception {
        MockHttpServletRequestBuilder createUsersReq=MockMvcRequestBuilders
                .get("/api/listUsers").accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(createUsersReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.length()").value(10));
    }
    @Test
    @Order(4)
    public void testListPets() throws Exception {
        MockHttpServletRequestBuilder createUsersReq=MockMvcRequestBuilders
                .get("/api/listPets").accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(createUsersReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.length()").value(20));
    }

}
