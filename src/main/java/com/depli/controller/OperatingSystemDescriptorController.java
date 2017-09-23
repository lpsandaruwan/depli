package com.depli.controller;

import com.depli.service.store.descriptor.OperatingSystemDescriptorService;
import com.depli.store.cache.descriptor.OperatingSystemDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DOperatingSystemMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 *
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/osdobjects")
public class OperatingSystemDescriptorController {

  @Autowired
  private OperatingSystemDescriptorService operatingSystemDescriptorService;

  @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
  public OperatingSystemDescriptor findClassLoadingDataById(@PathVariable long nodeId) {
    return operatingSystemDescriptorService.getByNodeId(nodeId);
  }
}
