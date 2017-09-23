package com.depli.controller;

import com.depli.service.store.descriptor.ThreadDescriptorService;
import com.depli.store.cache.descriptor.ThreadDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DThreadMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 *
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/tdobjects")
public class ThreadDescriptorController {

  @Autowired
  private ThreadDescriptorService threadDescriptorService;

  @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
  public ThreadDescriptor findClassLoadingDataById(@PathVariable long nodeId) {
    return threadDescriptorService.getByNodeId(nodeId);
  }
}
