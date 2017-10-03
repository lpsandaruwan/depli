package com.depli.service.store.cache;

import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.spring.provider.SpringCache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public abstract class BaseCacheServiceImplTest<T> {

    private static final String GET_CACHE = "getCache";
    private static final String CLEAR_CACHE = "clearCache";

    @Mock
    private SpringEmbeddedCacheManager springEmbeddedCacheManager;

    private Cache mockCache;
    private SpringCache mockSpringCache;
    private EmbeddedCacheManager mockEmbeddedCacheManager;
    private T cacheService;
    private String cacheKey;

    @Before
    public void setup() {
        mockCache = mock(Cache.class);
        mockEmbeddedCacheManager = mock(EmbeddedCacheManager.class);
        mockSpringCache = mock(SpringCache.class);
        cacheService = cacheService();
        cacheKey = cacheKey();
    }

    @Test
    public void getCache_WithGoodState_ShouldReturn() throws Exception {
        baseGetCache();
    }

    @Test
    public void clearCache_WithGoodState_ShouldClear() throws Exception {
        baseClearCache();
    }

    @SuppressWarnings("unchecked")
    private void baseGetCache() throws Exception {
        when(springEmbeddedCacheManager.getNativeCacheManager()).thenReturn(mockEmbeddedCacheManager);
        when(mockEmbeddedCacheManager.getCache(cacheKey)).thenReturn(mockCache);

        assertEquals(mockCache, invokeGetCache());
    }

    private void baseClearCache() throws Exception {
        when(springEmbeddedCacheManager.getCache(cacheKey)).thenReturn(mockSpringCache);

        invokeClearCache();

        verify(mockSpringCache, times(1)).clear();
    }

    abstract protected T cacheService();
    abstract protected String cacheKey();

    private Cache invokeGetCache() throws Exception {
        Method getCacheMethod = cacheService.getClass().getMethod(GET_CACHE);
        return (Cache)getCacheMethod.invoke(cacheService);
    }

    private void invokeClearCache() throws Exception {
        Method clearCacheMethod = cacheService.getClass().getMethod(CLEAR_CACHE);
        clearCacheMethod.invoke(cacheService);
    }

}
