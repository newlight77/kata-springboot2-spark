package com.newlight77.demo.location.config;

import com.newlight77.demo.location.repository.LocationRepository;
import com.newlight77.demo.location.service.LocationService;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportConfig {

  @Autowired
  private LocationRepository locationRepository;

  @Autowired
  private JavaSparkContext javaSparkContext;

  @Autowired
  private SparkSession sparkSession;

  @Bean
  public LocationService locationService() {
    return new LocationService(locationRepository, javaSparkContext, sparkSession);
  }

}
