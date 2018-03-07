package com.depli.store.cache.connector;

import com.depli.store.persistent.entity.JMXNode;
import java.io.IOException;
import java.util.HashMap;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

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


  public MainConnector(JMXNode jmxNode) {
    this.jmxNode = jmxNode;
    jmxConnector = null;
    serverConnection = null;
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
      jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, env);
    } else {
      jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, null);
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
