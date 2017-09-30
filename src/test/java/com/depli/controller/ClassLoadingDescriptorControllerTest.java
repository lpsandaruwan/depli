package com.depli.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.depli.DepliApplication;
import com.depli.service.store.descriptor.ClassLoadingDescriptorService;
import com.depli.store.cache.descriptor.ClassLoadingDescriptor;
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
 * Test ClassLoadingDescriptorController REST API.
 *
 * @author lpsandaruwan
 * @since 9/24/17
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DepliApplication.class)
@SpringBootTest
public class ClassLoadingDescriptorControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @MockBean
  private ClassLoadingDescriptorService classLoadingDescriptorService;

  private ClassLoadingDescriptor mockClassLoadingDescriptor = new ClassLoadingDescriptor();

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void findClassLoadingDescriptorByNodeId() throws Exception {
    Mockito.when(classLoadingDescriptorService.getByNodeId((long) 1))
        .thenReturn(mockClassLoadingDescriptor);

    RequestBuilder requestBuilderForSuccess = MockMvcRequestBuilders
        .get("/descriptors/classes/1/dynamics")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForSuccess).andExpect(status().isOk())
        .andExpect(jsonPath("$.loadedClassCount").exists())
        .andExpect(jsonPath("$.totalLoadedClassCount").exists())
        .andExpect(jsonPath("$.unloadedClassCount").exists())
        .andDo(print());

    RequestBuilder requestBuilderForError = MockMvcRequestBuilders
        .get("/descriptors/classes/2/dynamics")
        .accept(MediaType.APPLICATION_JSON);
    mockMvc.perform(requestBuilderForError).andExpect(status().isOk())
        .andExpect(jsonPath("$").doesNotExist())
        .andDo(print());
  }
}
