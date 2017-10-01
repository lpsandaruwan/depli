package com.depli.controller;

import com.depli.DepliApplication;
import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.github.benas.randombeans.api.EnhancedRandom;

import static org.mockito.ArgumentMatchers.any;

/**
 * Created by sfatali on October 1, 2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DepliApplication.class)
@SpringBootTest
public class JMXNodeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private JMXNodeService jmxNodeService;

    private MockMvc mockMvc;

    private JMXNode jmxNode;
    private List<JMXNode> jmxNodeList = new ArrayList<>();
    private EnhancedRandom enhancedRandom;
    private ObjectMapper mapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build(); // randomizer
        jmxNode = enhancedRandom.nextObject(JMXNode.class); // creating random node
        jmxNodeList.add(jmxNode); // adding random node to list of all nodes
        mapper = new ObjectMapper(); // needed for POST and PUT
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(jmxNodeService.findAll()).
                thenReturn(jmxNodeList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/nodes")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray())
                .andDo(print());
    }

    @Test
    public void getByNodeId() throws Exception {
        Mockito.when(jmxNodeService.findByNodeId(jmxNode.getNodeId()))
                .thenReturn(jmxNode);

        // success case
        RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.get("/nodes/"+jmxNode.getNodeId())
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilderForSuccess).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.nodeName").value(jmxNode.getNodeName()))
                .andDo(print());

        //error case
        RequestBuilder requestBuilderForError = MockMvcRequestBuilders.get("/nodes/"+Math.random())
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilderForError).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists())
                .andDo(print());
    }

    @Test
    public void saveNewNode() throws Exception {
        // success case - does not work
        Mockito.when(jmxNodeService.save(any()))
                .thenReturn(true); // mockito does not return true here, I don't know why! :((

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/nodes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(jmxNode));    // error 500, because mockito did not return true:(
        mockMvc.perform(requestBuilder).andExpect(status().isCreated())
                .andDo(print());

        //error case - seems to work
        Mockito.when(jmxNodeService.save(any()))
                .thenReturn(false);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError())
                .andDo(print());
    }

    @Test
    public void updateByNodeId() throws Exception {
        // success case - does not work
        Mockito.when(jmxNodeService.updateByNodeId(any(), any()))
                .thenReturn(true); // mockito does not return true here, I don't know why! :((

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/nodes/"+jmxNode.getNodeId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(jmxNode));    // error 400, because mockito did not return true :(
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andDo(print());

        //error case - seems to work
        Mockito.when(jmxNodeService.updateByNodeId(any(), any()))
                .thenReturn(false);
        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void deleteByNodeId() throws Exception {
        // success case
        Mockito.when(jmxNodeService.removeByNodeId(any()))
                .thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/nodes/"+jmxNode.getNodeId())
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andDo(print());

        //error case
        Mockito.when(jmxNodeService.removeByNodeId(any()))
                .thenReturn(false);
        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
                .andDo(print());
    }

}
