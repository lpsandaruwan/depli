package com.depli.utility.initializer.cache.impl;

import com.depli.service.store.descriptor.ClassLoadingDescriptorService;
import com.depli.store.cache.descriptor.ClassLoadingDescriptor;
import com.depli.utility.initializer.cache.CacheInitializer;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class Loading Descriptor Cache Initializer implementation
 *
 * Initializes and store ClassLoadingDescriptor objects in ClassLoadingDescriptor cache store.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class ClassLoadingDescriptorCacheInitializer implements CacheInitializer {

  @Autowired
  private ClassLoadingDescriptorService classLoadingDescriptorService;

  @Override
  public void initialize(Long nodeId) throws IOException {
    classLoadingDescriptorService.save(nodeId, new ClassLoadingDescriptor());
  }
}
