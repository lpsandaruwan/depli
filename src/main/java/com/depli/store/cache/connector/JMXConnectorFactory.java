package com.depli.store.cache.connector;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.HashMap;

public class JMXConnectorFactory implements ConnectorFactory {
    @Override
    public JMXConnector connect(JMXServiceURL jmxServiceURL, HashMap<String, Object> env)
            throws IOException {
        return javax.management.remote.JMXConnectorFactory.connect(jmxServiceURL, env);
    }
}