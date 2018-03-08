package com.depli.store.cache.connector;

import com.depli.store.persistent.entity.JMXNode;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.HashMap;

/**
 * Main connector
 * <p>
 * Provides methods to create, retrieve and close the connection for remote management bean server.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

public class MainConnector {

  private JMXNode jmxNode;
  private JMXConnector jmxConnector;
  private MBeanServerConnection serverConnection;
  private ConnectorFactory jmxFactory;


  public MainConnector(JMXNode jmxNode, ConnectorFactory jmxFactory) {
    this.jmxNode = jmxNode;
    jmxConnector = null;
    serverConnection = null;
    this.jmxFactory = jmxFactory;
  }

  public void openConnection() throws IOException {
    String serviceUrl = jmxNode.getServiceUrl();
    JMXServiceURL jmxServiceURL = new JMXServiceURL(serviceUrl);
    HashMap<String, Object> env = new HashMap<>();

    if (jmxNode.isAuthRequired()) {
      if (jmxNode.isAuthRequired()) {
        String[] credentials = new String[]{jmxNode.getUsername(), jmxNode.getPassword()};
        env.put(JMXConnector.CREDENTIALS, credentials);
      }
      jmxConnector = jmxFactory.connect(jmxServiceURL, env);
    } else {
      jmxConnector = jmxFactory.connect(jmxServiceURL, null);
    }

    this.serverConnection = jmxConnector.getMBeanServerConnection();
  }

  public void closeConnection() throws IOException {
    this.jmxConnector.close();
  }

  public JMXNode getJmxNode() {
    return jmxNode;
  }

  public void setJmxNode(JMXNode jmxNode) {
    this.jmxNode = jmxNode;
  }

  public MBeanServerConnection getServerConnection() {
    return serverConnection;
  }

  public void setServerConnection(MBeanServerConnection serverConnection) {
    this.serverConnection = serverConnection;
  }
}
