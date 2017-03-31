package com.depli.remote;

import com.depli.entity.AuthData;
import com.depli.entity.JMXNode;
import com.depli.service.AuthDataService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
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

    @Autowired
    private AuthDataService authDataService;

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

        // set credentials data if there exists
        if(jmxNode.isAuthRequired()) {
            AuthData authData = authDataService.findByAuthId(jmxNode.getAuthId());
            String[] credentials = new String[] {authData.getUsername(), authData.getPassword()};

            Hashtable<String, String[]> env = new Hashtable<String, String[]>();
            env.put(JMXConnector.CREDENTIALS, credentials);

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
