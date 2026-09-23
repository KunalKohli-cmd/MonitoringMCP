package com.example.monitoringmcp.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MicroserviceMonitoringTools {

    @Tool(name = "list_services",
          description = "List all registered microservices with their current status summary")
    public List<Map<String, String>> listServices() {
        return List.of(
            Map.of("name", "order-service",    "status", "UP",   "version", "2.3.1"),
            Map.of("name", "payment-service",  "status", "UP",   "version", "1.8.4"),
            Map.of("name", "inventory-service","status", "DOWN", "version", "3.0.0"),
            Map.of("name", "user-service",     "status", "UP",   "version", "4.1.2")
        );
    }

    @Tool(name = "get_service_status",
          description = "Get the detailed health status of a specific microservice by name")
    public Map<String, Object> getServiceStatus(
            @ToolParam(description = "The name of the microservice (e.g. order-service)")
            String serviceName) {

        return switch (serviceName) {
            case "inventory-service" -> Map.of(
                "service", serviceName,
                "status", "DOWN",
                "reason", "Database connection timeout",
                "lastSeenUp", "2026-09-07T08:15:00Z",
                "restarts", 3
            );
            default -> Map.of(
                "service", serviceName,
                "status", "UP",
                "uptime", "14d 6h 23m",
                "lastChecked", "2026-09-07T12:00:00Z",
                "restarts", 0
            );
        };
    }

    @Tool(name = "get_service_metrics",
          description = "Retrieve CPU, memory, and request rate metrics for a microservice")
    public Map<String, Object> getServiceMetrics(
            @ToolParam(description = "The name of the microservice")
            String serviceName,
            @ToolParam(description = "Time window for metrics: '1m', '5m', '15m', '1h'. Defaults to '5m' if omitted.", required = false)
            String window) {

        String resolvedWindow = (window != null && !window.isBlank()) ? window : "5m";
        return Map.of(
            "service", serviceName,
            "window", resolvedWindow,
            "cpu_percent", 34.7,
            "memory_mb", 512,
            "requests_per_second", 142.3,
            "error_rate_percent", 0.4,
            "p99_latency_ms", 87
        );
    }
}
