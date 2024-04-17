package com.example.notif.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "gcp.firebase")
public class FireBaseProp {
    private Resource serviceAgent;

}
