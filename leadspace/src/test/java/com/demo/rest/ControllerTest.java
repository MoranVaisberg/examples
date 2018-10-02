package com.demo.rest;

import com.demo.rest.controller.UserService;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserService.class)
@TestPropertySource("classpath:application-test.yml")
public class ControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Gson gson = new Gson();

    public static final String TEST_RESOURCES_FOLDER = "src/test/resources/";

    @Test
    public void whenUploadProjectTopicsIsLoaded_thenOKmessageIsReturned() throws Exception {

        String customerName = "customerName";
        String projectName = "projectName";
        MockMultipartFile multipartFile = new MockMultipartFile("file", new FileInputStream(TEST_RESOURCES_FOLDER + "topicWithCorrectData.csv"));
       // when(intentModelingService.uploadProjectTopics(customerName, projectName, multipartFile)).thenReturn("OK");

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/uploadprojecttopicsfile")
                        .file(multipartFile)
                        .param("customerName",customerName)
                        .param("projectName", projectName))
                        .andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        Assertions.assertThat(contentAsString).isEqualTo("OK");

    }


}