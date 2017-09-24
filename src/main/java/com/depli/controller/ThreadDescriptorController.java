package com.depli.controller;

import com.depli.service.store.descriptor.ThreadDescriptorService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Thread Descriptor Controller
 *
 * REST controller to expose thread descriptor objects.
 *
 * @author lpsandaruwan
 * @since 3/25/17
 */

@RestController
@RequestMapping("/descriptors/threads")
public class ThreadDescriptorController {

  @Autowired
  private ThreadDescriptorService threadDescriptorService;

  @GetMapping("/{descriptorIndex}/dynamics/counts")
  public ResponseEntity<Map> findThreadDescriptorCountDataById(@PathVariable Long descriptorIndex) {
    return new ResponseEntity<>(threadDescriptorService.getByNodeId(descriptorIndex).countsToMap(),
        HttpStatus.OK);
  }

  @GetMapping("/{descriptorIndex}/dynamics/dumps")
  public ResponseEntity<Map> findThreadDescriptorDumpDataById(@PathVariable Long descriptorIndex) {
    return new ResponseEntity<>(threadDescriptorService.getByNodeId(descriptorIndex).dumpsToMap(),
        HttpStatus.OK);
  }
}
