package com.depli.service.store.persistent.impl;

import com.depli.store.persistent.entity.JMXNode;
import com.depli.store.persistent.repository.JMXNodeRepository;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JMXNodeServiceImplTest {

  @Autowired
  private JMXNodeServiceImpl nodeServiceImpl;

  @MockBean
  private JMXNodeRepository jmxNodeRepository;

  @Test
  public void findTwoNodes() {
    Mockito.when(jmxNodeRepository.findAll()).then(new Answer<List<JMXNode>>() {
      @Override
      public List<JMXNode> answer(InvocationOnMock invocation) throws Throwable {
        return Arrays.asList(new JMXNode("node1", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false),
            new JMXNode("node2", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false));
      }
    });

    List<JMXNode> nodes = nodeServiceImpl.findAll();
    Assert.assertThat(nodes, Matchers.hasSize(2));
  }

  @Test
  public void findNodeWithId1() {
    Mockito.when(jmxNodeRepository.findByNodeId(1L)).then(new Answer<JMXNode>() {
      @Override
      public JMXNode answer(InvocationOnMock invocation) throws Throwable {
        return new JMXNode("node1", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi",false);
      }
    });

    JMXNode node = nodeServiceImpl.findByNodeId(1L);
    Assert.assertThat(node.getServiceUrl(), Matchers.is(("service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi")));
    Assert.assertThat(node.getUsername(), Matchers.isEmptyOrNullString());
  }


  @Test
  public void updateNodeWithId1() {
    JMXNode node = new JMXNode("node1", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false);
    Mockito.when(jmxNodeRepository.existsByNodeId(1L)).then(new Answer<Boolean>() {
      @Override
      public Boolean answer(InvocationOnMock invocation) throws Throwable {
        return true;
      }
    });

    boolean result = nodeServiceImpl.updateByNodeId(1L, node);
    Assert.assertThat(result, Matchers.is(true));
  }

  @Test
  public void removeNodewithId1() {
    Mockito.when(jmxNodeRepository.existsByNodeId(1L)).then(new Answer<Boolean>() {
      @Override
      public Boolean answer(InvocationOnMock invocation) throws Throwable {
        return true;
      }
    });

    boolean result = nodeServiceImpl.removeByNodeId(1L);
    Assert.assertThat(result, Matchers.is(true));
  }

  @Test
  public void saveANode() throws IOException {
    JMXNode node = new JMXNode("node1", "service:jmx:rmi:///jndi/rmi://localhost:9024/jmxrmi", false);
    Mockito.when(jmxNodeRepository.save(node)).then(new Answer<JMXNode>() {
      @Override
      public JMXNode answer(InvocationOnMock invocation) throws Throwable {
        node.setNodeId(1L);
        return node;
      }
    });

    boolean result = nodeServiceImpl.save(node);
    Assert.assertThat(result, Matchers.is(true));
  }

}
