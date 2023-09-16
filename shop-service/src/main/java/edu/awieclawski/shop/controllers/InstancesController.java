package edu.awieclawski.shop.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
public class InstancesController {

    private final DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String serverName;

    @PostConstruct
    void init() {
        if (Objects.nonNull(serverName)) {
            log.info("Found this server: {} name registered at Discovery Server", serverName);
        } else {
            log.warn("No server name found!");
        }
    }

    @GetMapping("/test/discovery_instances")
    public List<ServiceInstance> discoverInstances() {
        List<ServiceInstance> instances = discoveryClient.getInstances(serverName);
        logServiceInstances(instances);
        return instances;
    }

    @GetMapping("/test/request")
    public String testRequest(HttpServletRequest request) {
        log.warn("Received request: {}", request.getRequestURL().toString());
        return request.getRequestURL().toString();
    }

    private void logServiceInstances(List<ServiceInstance> instances) {
        instances.stream().filter(Objects::nonNull)
                .forEach(s -> log.info("instanceId: {} | serviceId: {} | host: {} | port: {} ",
                        s.getInstanceId(), s.getServiceId(), s.getHost(), s.getPort()));
    }
}
