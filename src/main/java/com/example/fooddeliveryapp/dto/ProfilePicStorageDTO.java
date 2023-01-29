package com.example.fooddeliveryapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "file")
public class ProfilePicStorageDTO {
    private String uploadDir;
}
