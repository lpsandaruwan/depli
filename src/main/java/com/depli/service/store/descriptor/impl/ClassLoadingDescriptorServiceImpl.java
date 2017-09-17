package com.depli.service.store.descriptor.impl;

import com.depli.service.store.cache.descriptor.ClassLoadingDescriptorCacheService;
import com.depli.service.store.descriptor.ClassLoadingDescriptorService;
import com.depli.store.cache.descriptor.ClassLoadingDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class loading data descriptor service implementation.
 *
 * @author lpsandaruwan
 * @since 9/12/17
 */

@Service
public class ClassLoadingDescriptorServiceImpl implements ClassLoadingDescriptorService {

  @Autowired
  private ClassLoadingDescriptorCacheService classLoadingDescriptorCacheService;

  @Override
  public ClassLoadingDescriptor getByNodeId(Long nodeId) {
    return classLoadingDescriptorCacheService.getCache().get(nodeId);
  }

  @Override
  public void save(Long nodeId, ClassLoadingDescriptor classLoadingDescriptor) {
    classLoadingDescriptorCacheService.getCache().put(nodeId, classLoadingDescriptor);
  }
}
