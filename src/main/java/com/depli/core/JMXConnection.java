package com.depli.core;

import com.depli.entity.JMXNode;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

/**
 * JMXConnection
 * Make connection to JMX node.
 *
 * Created by lpsandaruwan on 3/22/17.
 */

public class JMXConnection {

    private JMXNode jmxNode;

    public JMXConnection(JMXNode jmxNode) {
        this.jmxNode = jmxNode;
    }

    public JMXNode getJmxNode() {
        return jmxNode;
    }

    public void setJmxNode(JMXNode jmxNode) {
        this.jmxNode = jmxNode;
    }

    // create connection to a JMX node
    public MBeanServerConnection getConnection() throws IOException {
        // set JMX remote URL
        String serviceUrl = "service:jmx:rmi:///jndi/rmi://" + jmxNode.getHostname() + ":" + jmxNode.getPort() + "/jmxrmi";

        JMXServiceURL jmxServiceURL = new JMXServiceURL(serviceUrl);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, null);

        return jmxConnector.getMBeanServerConnection();
    }
}
