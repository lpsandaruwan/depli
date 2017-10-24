package com.depli.controller;

import com.depli.DepliApplication;
import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
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

  private JMXNode mockJMXnode = new JMXNode("nodeName", "hostname",4, false);

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
        .andExpect(jsonPath("$.hostname").isString())
        .andExpect(jsonPath("$.port").isNumber())
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

}
