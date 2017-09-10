package com.depli;

import com.depli.domain.holder.NodeDataMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class DepliApplication extends AsyncConfigurerSupport {

    public static NodeDataMap nodeDataMap = new NodeDataMap();
    public static boolean initializingFlag = false;
    public static boolean rebootTrigger = true;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DepliApplication.class, args);
    }

    @Override
    public Executor getAsyncExecutor() {
        // Configure and initialize async thread domain
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(1000);
        threadPoolTaskExecutor.setQueueCapacity(500);
        threadPoolTaskExecutor.setThreadNamePrefix("JMX Connector look ups");
        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor;
    }
}
