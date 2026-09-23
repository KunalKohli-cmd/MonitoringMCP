package com.example.monitoringmcp;

import com.example.monitoringmcp.tools.MicroserviceMonitoringTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonitoringMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringMcpApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider monitoringToolsProvider(MicroserviceMonitoringTools tools) {
        return MethodToolCallbackProvider.builder().toolObjects(tools).build();
    }
}
