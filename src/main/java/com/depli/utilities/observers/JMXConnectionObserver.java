package com.depli.utilities.observers;

import com.depli.entities.JMXNode;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Hashtable;

/** JMXConnectionObserver
 *
 * Depli implementation for initialize observers JMX Connection
 * Make connection to the JMX node.
 *
 * Created by lpsandaruwan on 3/22/17.
 */

public class JMXConnectionObserver {

    private final JMXNode jmxNode;
    private JMXConnector jmxConnector;
    private MBeanServerConnection mBeanServerConnection;


    public JMXConnectionObserver(JMXNode jmxNode) {
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

    // Create connection to a JMX observers and returns MBeanServerConnection object
    public MBeanServerConnection getConnection() throws IOException {
        // set JMX observers URL
        String serviceUrl = "service:jmx:rmi:///jndi/rmi://" + jmxNode.getHostname() + ":" + jmxNode.getPort() + "/jmxrmi";
        JMXServiceURL jmxServiceURL = new JMXServiceURL(serviceUrl);
        Hashtable<String, Object> env = new Hashtable<String, Object>();

        // set credentials data if there exists
        if(jmxNode.isAuthRequired() || jmxNode.isSslRequired()) {
            if(jmxNode.isAuthRequired()) {
                String[] credentials = new String[] {jmxNode.getUsername(), jmxNode.getPassword()};
                env.put(JMXConnector.CREDENTIALS, credentials);
            }

            jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, env);
        }

        else {
            jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, null);
        }

        mBeanServerConnection = jmxConnector.getMBeanServerConnection();
        return mBeanServerConnection;
    }

    // Close JMX observers connection
    public void closeConnection() throws IOException {
        this.jmxConnector.close();
    }
}
