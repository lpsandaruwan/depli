package com.depli.controller;

import com.depli.service.store.descriptor.OperatingSystemDescriptorService;
import com.depli.store.cache.descriptor.OperatingSystemDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Operating system descriptor controller
 *
 * REST controller to expose Operating system descriptor data.
 *
 * @author lpsandaruwan
 * @since 3/25/17
 */

@RestController
@RequestMapping("/descriptors/systems")
public class OperatingSystemDescriptorController {

  @Autowired
  private OperatingSystemDescriptorService operatingSystemDescriptorService;

  @GetMapping("/{descriptorIndex}/statics")
  public OperatingSystemDescriptor findOperatingSystemDescriptorByNodeId(
      @PathVariable Long descriptorIndex) {
    return operatingSystemDescriptorService.getByNodeId(descriptorIndex);
  }
}
