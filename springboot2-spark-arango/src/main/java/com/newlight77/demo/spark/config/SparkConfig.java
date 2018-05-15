package com.newlight77.demo.spark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
public class SparkConfig {

    @Value("${app.name:core-data}")
    private String appName;

    @Value("${master.uri:localhost}")
    private String masterUri;

    @Bean
    public SparkConf sparkConf() {
        return new SparkConf()
            .setAppName(appName)
            .setMaster(masterUri)
            .set("spark.executor.memory", "512m")
            .set("spark.driver.memory", "512m")
            .set("spark.driver.cores", "2")
            .set("spark.executor.instances", "2");
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }

    @Bean
    public SparkSession sparkSession() {
        return SparkSession
            .builder()
            .sparkContext(javaSparkContext().sc())
            .getOrCreate();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
