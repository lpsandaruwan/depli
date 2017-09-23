package com.depli.controller;

import com.depli.service.store.descriptor.ClassLoadingDescriptorService;
import com.depli.store.cache.descriptor.ClassLoadingDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/descriptors/classes")
public class ClassLoadingDescriptorController {

  @Autowired
  private ClassLoadingDescriptorService classLoadingDescriptorService;

  @GetMapping("/{descriptorIndex}/dynamics")
  public ResponseEntity<ClassLoadingDescriptor> findClassLoadingDescriptorByNodeId(
      @PathVariable Long descriptorIndex) {
    return new ResponseEntity<>(classLoadingDescriptorService.getByNodeId(descriptorIndex),
        HttpStatus.OK);
  }
}
