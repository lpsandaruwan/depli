package com.depli.controller;

import com.depli.service.store.descriptor.ClassLoadingDescriptorService;
import com.depli.store.cache.descriptor.ClassLoadingDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassLoadingDescriptorController
 *
 * REST API to expose class loading descriptor data.
 *
 * @author lpsandaruwan
 * @since 3/25/17
 */

@RestController
@RequestMapping("/cdobjects")
public class ClassLoadingDescriptorController {

  @Autowired
  private ClassLoadingDescriptorService classLoadingDescriptorService;

  @GetMapping("/{nodeId}")
  public ClassLoadingDescriptor findByNodeId(@PathVariable long nodeId) {
    return classLoadingDescriptorService.getByNodeId(nodeId);
  }
}
