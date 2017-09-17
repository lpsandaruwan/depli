package com.depli.service.store.descriptor.impl;

import com.depli.service.store.cache.descriptor.MemoryDescriptorCacheService;
import com.depli.service.store.descriptor.MemoryDescriptorService;
import com.depli.store.cache.descriptor.MemoryDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Memory usage descriptor service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class MemoryDescriptorServiceImpl implements MemoryDescriptorService {

  @Autowired
  private MemoryDescriptorCacheService memoryDescriptorCacheService;

  @Override
  public MemoryDescriptor getByNodeId(Long nodeId) {
    return memoryDescriptorCacheService.getCache().get(nodeId);
  }

  @Override
  public void save(Long nodeId, MemoryDescriptor memoryDescriptor) {
    memoryDescriptorCacheService.getCache().put(nodeId, memoryDescriptor);
  }
}
