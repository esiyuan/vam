package com.vam.service;

import com.vam.DaoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.vam"})
@Import(DaoConfig.class)
public class ServiceConfig {
}
