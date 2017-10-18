package com.depli.utility;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable {

  private static final long serialVersionUID = -3301695478208950415L;

  public Date now() {
    return new Date();
  }
}