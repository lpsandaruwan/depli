package com.depli.store.cache.connector;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.HashMap;

interface ConnectorFactory {
    JMXConnector connect(JMXServiceURL jmxServiceURL, HashMap<String, Object> env)
            throws IOException;
}
