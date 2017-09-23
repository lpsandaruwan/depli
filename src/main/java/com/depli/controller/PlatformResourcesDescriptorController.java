package com.depli.controller;

import com.depli.service.store.descriptor.PlatformResourcesDescriptorService;
import com.depli.store.cache.descriptor.PlatformResourcesDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DOperatingSystemMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 *
 * Created by lpsandaruwan on 3/29/17.
 */

@RestController
@RequestMapping("/peosdobjects")
public class PlatformResourcesDescriptorController {

  @Autowired
  private PlatformResourcesDescriptorService platformResourcesDescriptorService;

  @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
  public PlatformResourcesDescriptor findClassLoadingDataById(@PathVariable long nodeId) {
    return platformResourcesDescriptorService.getByNodeId(nodeId);
  }
}