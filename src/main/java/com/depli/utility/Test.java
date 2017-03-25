package com.depli.utility;

import com.depli.entity.JMXNode;
import com.depli.remote.DJMXConnection;
import com.depli.remote.DThreadMXBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.management.MBeanServerConnection;
import java.io.IOException;

/**
 * Created by lpsandaruwan on 3/23/17.
 */

@Service
public class Test {

    Test() {}

    @Async
    public void test() throws IOException, InterruptedException {
        JMXNode jmxNode = new JMXNode("2", "localhost", 9999, false, false);

        final DJMXConnection djmxConnection = new DJMXConnection(jmxNode);
        MBeanServerConnection mBeanServerConnection = djmxConnection.getConnection();

        final DThreadMXBean dThreadMXBean = new DThreadMXBean(djmxConnection);
        dThreadMXBean.initialize();

        while (true) {
            System.out.println(dThreadMXBean.getThreadMXBean().getAllThreadIds());
            Thread.sleep(3000);
        }

    }
}
