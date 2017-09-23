package com.depli.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * MainController
 *
 * Expose services for application through REST.
 *
 * @author lpsandaruwan
 * @since 4/1/17
 */

@RestController
@RequestMapping("/apptools")
public class MainController {

  @GetMapping("/reboot")
  public boolean setRebootTrigger() {
    return true;
  }

  @GetMapping("/stats")
  public int getDepliStatus() {
    return 1;
  }
}
