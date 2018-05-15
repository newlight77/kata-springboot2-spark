package com.newlight77.demo.spark.config;

import com.newlight77.demo.spark.runner.UserJsonInjectionRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages = {
    "com.newlight77.demo.spark.repository" })
public class DataInjectionConfig {

  @Bean
  CommandLineRunner jsonInjectionRunner() {
    return new UserJsonInjectionRunner();
  }

}
