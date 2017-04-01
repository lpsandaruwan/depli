package com.depli.remote;

import com.depli.entity.JMXNode;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;
import java.io.IOException;
import java.util.Hashtable;

/** DJMXConnection
 *
 * Depli implementation for initialize remote JMX Connection
 * Make connection to the JMX node.
 *
 * Created by lpsandaruwan on 3/22/17.
 */

public class DJMXConnection {

    private final JMXNode jmxNode;
    private JMXConnector jmxConnector;
    private MBeanServerConnection mBeanServerConnection;


    public DJMXConnection(JMXNode jmxNode) {
        this.jmxNode = jmxNode;
    }

    public JMXNode getJmxNode() {
        return jmxNode;
    }

    public JMXConnector getJmxConnector() {
        return jmxConnector;
    }

    public MBeanServerConnection getmBeanServerConnection() {
        return mBeanServerConnection;
    }

    // Create connection to a JMX remote and returns MBeanServerConnection object
    public MBeanServerConnection getConnection() throws IOException {
        // set JMX remote URL
        String serviceUrl = "service:jmx:rmi:///jndi/rmi://" + jmxNode.getHostname() + ":" + jmxNode.getPort() + "/jmxrmi";
        JMXServiceURL jmxServiceURL = new JMXServiceURL(serviceUrl);
        Hashtable<String, Object> env = new Hashtable<String, Object>();

        // set credentials data if there exists
        if(jmxNode.isAuthRequired() || jmxNode.isSslRequired()) {
            if(jmxNode.isAuthRequired()) {
                String[] credentials = new String[] {jmxNode.getUsername(), jmxNode.getPassword()};
                env.put(JMXConnector.CREDENTIALS, credentials);
            }

            if(jmxNode.isSslRequired()) {
                env.put("jmx.remote.rmi.client.socket.factory", new SslRMIClientSocketFactory());
                env.put("jmx.remote.rmi.client.socket.factory", new SslRMIServerSocketFactory());
            }

            jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, env);
        }

        else {
            jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, null);
        }

        mBeanServerConnection = jmxConnector.getMBeanServerConnection();
        return mBeanServerConnection;
    }

    // Close JMX remote connection
    public void closeConnection() throws IOException {
        this.jmxConnector.close();
    }
}
