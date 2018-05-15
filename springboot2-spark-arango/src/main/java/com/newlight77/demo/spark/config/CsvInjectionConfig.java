package com.newlight77.demo.spark.config;

import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import com.newlight77.demo.spark.runner.UserCsvInjectionRunner;
import com.newlight77.demo.spark.runner.UserInjectionRunner;
import com.newlight77.demo.spark.runner.UserJsonInjectionRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;

@Configuration
@EnableArangoRepositories(basePackages = { "com.newlight77.demo.spark" })
public class CsvInjectionConfig extends AbstractArangoConfiguration {

  @Override
  public Builder arango() {
    return new ArangoDB.Builder().host("localhost", 8529).user("root").password(null);
  }

  @Override
  public String database() {
    return "demo";
  }

  @Bean
  CommandLineRunner userInjectionRunner() {
    return new UserInjectionRunner();
  }

//  @Bean
//  CommandLineRunner jsonInjectionRunner() {
//    return new UserJsonInjectionRunner();
//  }

}
