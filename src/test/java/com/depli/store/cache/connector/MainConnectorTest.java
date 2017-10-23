package com.depli.store.cache.connector;

import com.depli.store.persistent.entity.JMXNode;
import org.junit.Before;
import org.junit.Test;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainConnectorTest {

    private JMXNode jmxNode;
    private ConnectorFactory connectorFactory;
    private MBeanServerConnection serverConnection;
    private JMXConnector jmxConnector;
    private MainConnector mainConnector;

    @Before
    public void setup() {
        this.jmxNode = mock(JMXNode.class);
        this.connectorFactory = mock(ConnectorFactory.class);
    }

    @Test
    public void openConnectionSetsServerConnectionWhenConnectDoesNotFails()
            throws IOException {
        givenServerConnection();
        givenJmxConnector();
        givenUnfailingConnection();
        givenMainConnector(jmxNode, connectorFactory);

        whenConnectionIsOpen(mainConnector);

        assertEquals(serverConnection, mainConnector.getServerConnection());
    }

    @Test
    public void openConnectionRequiringAuthConnectionDoesntFails()
            throws IOException {
        givenServerConnection();
        givenJmxConnector();
        givenUnfailingConnection();
        givenAuthIsRequired();
        givenMainConnector(jmxNode, connectorFactory);

        whenConnectionIsOpen(mainConnector);

        assertEquals(serverConnection, mainConnector.getServerConnection());
    }

    private void givenAuthIsRequired() {
        when(jmxNode.isAuthRequired()).thenReturn(Boolean.TRUE);
    }

    @Test(expected = IOException.class)
    public void openConnectionFailsOnConnectorFactoryConnectionFailure()
            throws IOException {
        givingFailingConnection();
        givenMainConnector(jmxNode, connectorFactory);

        whenConnectionIsOpen(mainConnector);
    }

    private void givingFailingConnection()
            throws IOException {
        when(connectorFactory.connect(any(), any())).thenThrow(IOException.class);
    }

    private void whenConnectionIsOpen(MainConnector mainConnector)
            throws IOException {
        mainConnector.openConnection();
    }

    private void givenMainConnector(JMXNode jmxNode, ConnectorFactory connectorFactory) {
        this.mainConnector = new MainConnector(jmxNode, connectorFactory);
    }

    private void givenUnfailingConnection()
            throws IOException {
        when(connectorFactory.connect(any(), any())).thenReturn(jmxConnector);
    }

    private void givenServerConnection() {
        this.serverConnection = mock(MBeanServerConnection.class);
    }

    private void givenJmxConnector()
            throws IOException {
        this.jmxConnector = mock(JMXConnector.class);
        when(jmxConnector.getMBeanServerConnection()).thenReturn(serverConnection);
    }

}
