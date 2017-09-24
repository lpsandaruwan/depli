package com.depli.controller;

import com.depli.service.store.descriptor.ClassLoadingDescriptorService;
import com.depli.store.cache.descriptor.ClassLoadingDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Test ClassLoadingDescriptorController
 *
 * @author lpsandaruwan
 * @since 9/24/17
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClassLoadingDescriptorController.class, secure = false)
public class ClassLoadingDescriptorControllerTest {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ClassLoadingDescriptorControllerTest.class);
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private ClassLoadingDescriptorService classLoadingDescriptorService;
  private ClassLoadingDescriptor mockClassLoadingDescriptor = new ClassLoadingDescriptor();

  @Test
  public void findClassLoadingDescriptorByNodeIdTest() throws Exception {
    Mockito.when(
        classLoadingDescriptorService.getByNodeId(Mockito.anyLong())
    ).thenReturn(mockClassLoadingDescriptor);
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/descriptors/classes/1/dynamics")
        .accept(MediaType.APPLICATION_JSON);
    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    LOGGER.info("findClassLoadingDescriptorByNodeId():Response: " + mvcResult.getResponse()
        .getContentAsString());
    String expectedResult = "{\"loadedClassCount\": 0, \"totalLoadedClassCount\": 0, \"unloadedClassCount\": 0}";
    JSONAssert.assertEquals(expectedResult, mvcResult.getResponse().getContentAsString(), false);
  }

}