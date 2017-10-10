package com.depli.service.builder.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.depli.store.cache.connector.ConnectionTree;
import com.depli.store.cache.connector.MainConnector;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.utility.connector.ManagementBeanServerConnector;
import com.depli.utility.connector.proxy.ClassLoadingMXBeanProxyConnector;
import com.depli.utility.connector.proxy.MemoryMXBeanProxyConnector;
import com.depli.utility.connector.proxy.OperatingSystemMXBeanProxyConnector;
import com.depli.utility.connector.proxy.PlatformResourcesMXBeanProxyConnector;
import com.depli.utility.connector.proxy.RuntimeMXBeanProxyConnector;
import com.depli.utility.connector.proxy.ThreadMXBeanProxyConnector;
import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import javax.management.MBeanServerConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ConnectionTreeBuilderServiceImplTest {

  JMXNode mockJMXNode;
  MainConnector mockMainConnector;
  MBeanServerConnection mockMBeanServerConnection;
  @Mock
  private ManagementBeanServerConnector managementBeanServerConnector;
  @Mock
  private ClassLoadingMXBeanProxyConnector classLoadingMXBeanProxyConnector;
  @Mock
  private MemoryMXBeanProxyConnector memoryMXBeanProxyConnector;
  @Mock
  private OperatingSystemMXBeanProxyConnector operatingSystemMXBeanProxyConnector;
  @Mock
  private PlatformResourcesMXBeanProxyConnector platformResourcesMXBeanProxyConnector;
  @Mock
  private RuntimeMXBeanProxyConnector runtimeMXBeanProxyConnector;
  @Mock
  private ThreadMXBeanProxyConnector threadMXBeanProxyConnector;
  @InjectMocks
  private ConnectionTreeBuilderServiceImpl connectionTreeBuilderService;

  @Before
  public void setup() {
    mockMainConnector = mock(MainConnector.class);
    mockJMXNode = mock(JMXNode.class);
    mockMBeanServerConnection = mock(MBeanServerConnection.class);
  }

  @Test
  public void getTree_WithGoodState_ShouldReturn() throws Exception {
    ClassLoadingMXBean classLoadingMXBean = mock(ClassLoadingMXBean.class);
    when(classLoadingMXBeanProxyConnector.getConnection(mockMBeanServerConnection))
        .thenReturn(classLoadingMXBean);
    when(managementBeanServerConnector.getConnection(mockJMXNode)).thenReturn(mockMainConnector);
    when(mockMainConnector.getServerConnection()).thenReturn(mockMBeanServerConnection);

    ConnectionTree connectionTree = connectionTreeBuilderService.getTree(mockJMXNode);

    assertEquals(classLoadingMXBean, connectionTree.getClassLoadingMXBean());
  }

  @Test(expected = IOException.class)
  public void getTree_WithExceptionThrown_ShouldThrowException() throws Exception {
    when(managementBeanServerConnector.getConnection(mockJMXNode)).thenReturn(mockMainConnector);
    when(mockMainConnector.getServerConnection()).thenReturn(mockMBeanServerConnection);
    when(classLoadingMXBeanProxyConnector.getConnection(mockMBeanServerConnection))
        .thenThrow(new IOException("Test getTree"));

    connectionTreeBuilderService.getTree(mockJMXNode);
  }
}
