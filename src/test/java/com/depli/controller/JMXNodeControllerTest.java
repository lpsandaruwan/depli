package com.depli.controller;

import com.depli.DepliApplication;
import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test JMXNodeController REST API.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DepliApplication.class)
@SpringBootTest
public class JMXNodeControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @MockBean
  private JMXNodeService jmxNodeService;

  private List<JMXNode> nodes;
  private Map server01expectedJSON;
  private Map server02expectedJSON;

  private JMXNode mockJMXnode = new JMXNode("nodeName", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false);

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    JMXNode server01 = new JMXNode("master", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false);
    JMXNode server02 = new JMXNode("slave", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", true);
    server02.setPassword("s3cr3t");
    server02.setUsername("toor");
    this.nodes = Arrays.asList(server01, server02);

    server01expectedJSON = createExpectedJSON(server01);
    server02expectedJSON = createExpectedJSON(server02);
  }

    @Test
    public void listNodes()
            throws Exception {


        when(jmxNodeService.findAll()).thenReturn(nodes);
        RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.get("/nodes")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilderForSuccess)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(nodes.size()))
                .andExpect(jsonPath("$.[?(@.nodeName == 'slave')]").value(server02expectedJSON))
                .andExpect(jsonPath("$.[?(@.nodeName == 'master')]").value(server01expectedJSON))
                .andDo(print());

    }

  @Test
  public void findJMXNodeByNodeId() throws Exception {
    Mockito.when(
            jmxNodeService.findByNodeId((long) 1)
    ).thenReturn(mockJMXnode);

    RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.get("/nodes/1")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForSuccess).andExpect(status().isOk())
        .andExpect(jsonPath("$.nodeId").isNumber())
        .andExpect(jsonPath("$.nodeName").isString())
        .andExpect(jsonPath("$.serviceUrl").isString())
        .andExpect(jsonPath("$.authRequired").isBoolean())
        .andExpect(jsonPath("$.username").isEmpty())
        .andExpect(jsonPath("$.password").isEmpty())
        .andDo(print());

    RequestBuilder requestBuilderForError = MockMvcRequestBuilders
        .get("/nodes/2")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForError).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").exists())
        .andDo(print());
  }

    @Test
    public void getSomeNodeAndFails()
            throws Exception {

        when(jmxNodeService.findByNodeId(0L)).thenReturn(null);
        RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.get("/nodes/0")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilderForSuccess)
                .andExpect(status().isBadRequest());
    }


    @Test
    public void addNodeOk()
            throws Exception {

        when(jmxNodeService.save(any())).thenReturn(Boolean.TRUE);
        JMXNode newServer =  new JMXNode("undefined", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false);
        RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.post("/nodes")
                .content(asJsonString(newServer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilderForSuccess)
                .andExpect(status().isCreated());

        verify(jmxNodeService, times(1)).save(any(JMXNode.class));
    }

    @Test
    public void addNodeFails()
            throws Exception {

        when(jmxNodeService.save(any())).thenReturn(Boolean.FALSE);
        JMXNode newServer =  new JMXNode("undefined", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false);
        RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.post("/nodes")
                .content(asJsonString(newServer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilderForSuccess)
                .andExpect(status().isInternalServerError());

        verify(jmxNodeService, times(1)).save(any(JMXNode.class));
    }

    @Test
    public void updateNodeOk()
            throws Exception {

        when(jmxNodeService.updateByNodeId(eq(0L), any())).thenReturn(Boolean.TRUE);
        JMXNode update =  new JMXNode("undefined", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false);
        RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.put("/nodes/0")
                .content(asJsonString(update))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilderForSuccess)
                .andExpect(status().isOk());

    }

    @Test
    public void updateNodeFails()
            throws Exception {

        when(jmxNodeService.updateByNodeId(eq(0L), any())).thenReturn(Boolean.FALSE);
        JMXNode update =  new JMXNode("undefined", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false);
        RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.put("/nodes/0")
                .content(asJsonString(update))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilderForSuccess)
                .andExpect(status().isBadRequest());
    }

    @Test
  public void deleteJMXNodeByNodeId() throws Exception {
    Mockito.when(
        jmxNodeService.removeByNodeId((long) 1)
    ).thenReturn(true);

    RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.delete("/nodes/1")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForSuccess).andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist())
        .andDo(print());

    RequestBuilder requestBuilderForError = MockMvcRequestBuilders
        .delete("/nodes/2")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForError).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").exists())
        .andDo(print());
  }

    private String asJsonString(JMXNode newServer) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(newServer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private Map createExpectedJSON(JMXNode node) {
        Map map = new HashMap<String, Object>();
        map.put("nodeId", (int) node.getNodeId());
        map.put("nodeName", node.getNodeName());
        map.put("serviceUrl", node.getServiceUrl());
        map.put("authRequired", node.isAuthRequired());
        map.put("username", node.getUsername());
        map.put("password", node.getPassword());
        return map;
    }

}
