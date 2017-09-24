package com.depli.store.persistent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by lpsandaruwan on 3/22/17.
 */

@Entity
@Table(name = "jmx_node")
public class JMXNode {

  @Column(name = "node_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long nodeId;

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
  private int port;

  @Column(name = "auth_required")
  @NotNull
  private boolean authRequired;

  @Column(name = "username")
  @Size(max = 50)
  private String username;

  @Column(name = "password")
  @Size(max = 50)
  private String password;

  protected JMXNode() {
  }

  public JMXNode(String nodeName,
      String hostname,
      int port,
      boolean authRequired) {
    this.nodeName = nodeName;
    this.hostname = hostname;
    this.port = port;
    this.authRequired = authRequired;
  }

  public long getNodeId() {
    return nodeId;
  }

  public void setNodeId(long nodeId) {
    this.nodeId = nodeId;
  }

  public String getNodeName() {
    return nodeName;
  }

  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public boolean isAuthRequired() {
    return authRequired;
  }

  public void setAuthRequired(boolean authRequired) {
    this.authRequired = authRequired;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
