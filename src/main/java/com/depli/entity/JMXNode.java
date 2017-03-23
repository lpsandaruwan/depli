package com.depli.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by lpsandaruwan on 3/22/17.
 */

@Entity
@Table(name = "jmx_nodes")
public class JMXNode {

    @Column(name = "node_id")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    @Size(max = 20)
    private String nodeId;

    @Column(name = "node_name")
    @NotNull
    @Size(max = 30)
    private String nodeName;

    @Column(name = "hostname")
    @NotNull
    @Size(max = 50)
    private String hostname;

    @Column(name = "port")
    @NotNull
    private Integer port;

    @Column(name="username")
    @Size(max = 50)
    private String username;

    @Column(name="password")
    @Size(max = 50)
    private String password;

    @Column(name="auth_required")
    @NotNull
    private boolean authRequired;

    @Column(name = "ssl_required")
    @NotNull
    private boolean sslRequired;


    protected JMXNode() {}

    public JMXNode(String nodeName,
                   String hostname,
                   int port,
                   String username,
                   String password,
                   boolean authRequired,
                   boolean sslRequired) {
        this.nodeName = nodeName;
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = port;
        this.authRequired = authRequired;
        this.sslRequired = sslRequired;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getHostname() {
        return hostname;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAuthRequired() {
        return authRequired;
    }

    public boolean isSslRequired() {
        return sslRequired;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthRequired(boolean authRequired) {
        this.authRequired = authRequired;
    }

    public void setSslRequired(boolean sslRequired) {
        this.sslRequired = sslRequired;
    }
}
