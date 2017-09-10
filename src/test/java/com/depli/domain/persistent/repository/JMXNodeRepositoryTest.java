package com.depli.domain.persistent.repository;

import com.depli.domain.persistent.entity.JMXNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lpsandaruwan on 5/21/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class JMXNodeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private JMXNodeRepository jmxNodeRepository;

    @Test
    public void testJmxNodeRepository() {
        this.testEntityManager.persist(new JMXNode(
                "Localhost", "127.0.0.1", 9999, false, false)
        );

        JMXNode jmxNode = this.jmxNodeRepository.findByHostname("127.0.0.1");
        assertThat(jmxNode.getNodeName()).isEqualTo("Localhost");
        assertThat(jmxNode.getPort()).isEqualTo(9999);
    }
}
