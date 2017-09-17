package com.depli.utility;

import com.depli.utility.initializer.InitializerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class TaskRunnerComponent implements CommandLineRunner {

  @Autowired
  private InitializerFactory initializerFactory;

  @Override
  public void run(String... strings) throws Exception {
    initializerFactory.initialize();
  }
}
