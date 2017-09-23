package com.depli.controller;

import com.depli.service.store.descriptor.RuntimeDescriptorService;
import com.depli.store.cache.descriptor.RuntimeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DRuntimeMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 *
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/rdobjects")
public class RuntimeDescriptorController {

  @Autowired
  private RuntimeDescriptorService runtimeDescriptorService;

  @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
  public RuntimeDescriptor findClassLoadingDataById(@PathVariable long nodeId) {
    return runtimeDescriptorService.getByNodeId(nodeId);
  }
}