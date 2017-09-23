package com.depli.controller;

import com.depli.service.store.descriptor.MemoryDescriptorService;
import com.depli.store.cache.descriptor.MemoryDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * MemoryDescriptorController
 *
 * REST API to expose memory descriptor data.
 *
 * @author lpsandaruwan
 * @since 3/25/17
 */

@RestController
@RequestMapping("/mdobjects")
public class MemoryDescriptorController {

  @Autowired
  private MemoryDescriptorService memoryDescriptorService;

  @GetMapping("/{nodeId}")
  public MemoryDescriptor findByNodeId(@PathVariable long nodeId) {
    return memoryDescriptorService.getByNodeId(nodeId);
  }
}
