package com.depli.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lpsandaruwan on 5/21/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmailService() {
        emailService.sendMail(
                "lpsandaruwan@gmail.com",
                "Test mail from depli",
                "Test mail from depli, Please ignore."
        );
    }
}
