package com.depli.service.store.persistent;

import com.depli.store.persistent.entity.JMXNode;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

/**
 * Created by Lahiru Pathirage on 8/6/2017.
 */

public interface JMXNodeService {

  public ResponseEntity<List<JMXNode>> findAll();

  public ResponseEntity findByNodeId(Long nodeId);

  public ResponseEntity<Map> updateByNodeId(Long nodeId, JMXNode jmxNode);

  public ResponseEntity<Map> removeByNodeId(Long nodeId);

  public ResponseEntity<Map> save(JMXNode jmxNode) throws IOException;
}
