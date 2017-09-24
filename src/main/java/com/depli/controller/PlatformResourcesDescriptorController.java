package com.depli.controller;

import com.depli.service.store.descriptor.PlatformResourcesDescriptorService;
import com.depli.store.cache.descriptor.PlatformResourcesDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Platform Resources Descriptor Controller
 *
 * REST controller to expose platform descriptor data.
 *
 * @author lpsandaruwan
 * @since 3/29/17
 */

@RestController
@RequestMapping("/descriptors/platforms")
public class PlatformResourcesDescriptorController {

  @Autowired
  private PlatformResourcesDescriptorService platformResourcesDescriptorService;

  @GetMapping("/{descriptorIndex}/dynamics")
  public ResponseEntity<PlatformResourcesDescriptor> findPlatformResourcesDescriptorByNodeId(
      @PathVariable Long descriptorIndex) {
    return new ResponseEntity<>(platformResourcesDescriptorService.getByNodeId(descriptorIndex),
        HttpStatus.OK);
  }
}
