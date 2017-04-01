package com.depli.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.initializingFlag;
import static com.depli.DepliApplication.rebootTrigger;


/** DepliToolsController
 * Manage and control exclusive resources in the application.
 *
 * Created by lpsandaruwan on 4/1/17.
 */

@RestController
@RequestMapping("/apptools")
public class DepliToolsController {

    @RequestMapping(value = "/reboot", method = RequestMethod.GET)
    public boolean setRebootTrigger() {
        rebootTrigger = true;

        return true;
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public int getDepliStatus() {
        if(rebootTrigger) {
            return -1;
        }

        if(initializingFlag) {
            return 0;
        }

        return 1;
    }
}
