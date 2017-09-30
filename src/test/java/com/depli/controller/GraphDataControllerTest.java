package com.depli.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.depli.DepliApplication;
import com.depli.service.store.graph.ClassLoadingGraphDataService;
import com.depli.service.store.graph.ProcessingUnitGraphDataService;
import com.depli.store.cache.graph.ClassLoadingGraphData;
import com.depli.store.cache.graph.ProcessingUnitGraphData;
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

/**
 * Test GraphDataController REST API.
 *
 * @author lpsandaruwan
 * @since 9/29/17
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DepliApplication.class)
@SpringBootTest
public class GraphDataControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @MockBean
  private ClassLoadingGraphDataService classLoadingGraphDataService;

  @MockBean
  private ProcessingUnitGraphDataService processingUnitGraphDataService;

  private ClassLoadingGraphData mockClassLoadingGraphData = new ClassLoadingGraphData(5);
  private ProcessingUnitGraphData mockProcessingUnitGraphData = new ProcessingUnitGraphData(5);

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void findProcessingUnitGraphDataByNodeId() throws Exception {
    Mockito.when(
        processingUnitGraphDataService.getByNodeId((long) 1)
    ).thenReturn(mockProcessingUnitGraphData);

    RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.get("/graphs/mainframes/1")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForSuccess).andExpect(status().isOk())
        .andExpect(jsonPath("$.hostCpuGraphData").isArray())
        .andExpect(jsonPath("$.jvmCpuGraphData").isArray())
        .andExpect(jsonPath("$.pointIndex").exists())
        .andDo(print());

    RequestBuilder requestBuilderForError = MockMvcRequestBuilders
        .get("/graphs/mainframes/2")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForError).andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist())
        .andDo(print());
  }

  @Test
  public void findClassLoadingGraphDataByNodeId() throws Exception {
    Mockito.when(
        classLoadingGraphDataService.getByNodeId((long) 1)
    ).thenReturn(mockClassLoadingGraphData);

    RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders.get("/graphs/classes/1")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForSuccess).andExpect(status().isOk())
        .andExpect(jsonPath("$.loadedClassCountGraphData").isArray())
        .andExpect(jsonPath("$.pointIndex").exists())
        .andDo(print());

    RequestBuilder requestBuilderForError = MockMvcRequestBuilders
        .get("/graphs/classes/2")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForError).andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist())
        .andDo(print());
  }
}
