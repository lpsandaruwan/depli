package com.depli.controller;

import com.depli.service.store.descriptor.RuntimeDescriptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Runtime Descriptor Controller
 *
 * REST controller to expose Runtime Descriptors.
 *
 * @author lpsandaruwan
 * @since 3/25/17
 */

@RestController
@RequestMapping("/descriptors/runtimes/")
public class RuntimeDescriptorController {

  @Autowired
  private RuntimeDescriptorService runtimeDescriptorService;

  @GetMapping("/{descriptorIndex}/dynamics")
  public ResponseEntity findRuntimeDescriptorDynamicDataById(@PathVariable Long descriptorIndex) {
    return new ResponseEntity<>(
        runtimeDescriptorService.getByNodeId(descriptorIndex).dynamicsToJson(), HttpStatus.OK);
  }

  @GetMapping("/{descriptorIndex}/statics")
  public ResponseEntity findRuntimeDescriptorStaticDataById(@PathVariable Long descriptorIndex) {
    return new ResponseEntity<>(
        runtimeDescriptorService.getByNodeId(descriptorIndex).staticsToJson(), HttpStatus.OK);
  }
}