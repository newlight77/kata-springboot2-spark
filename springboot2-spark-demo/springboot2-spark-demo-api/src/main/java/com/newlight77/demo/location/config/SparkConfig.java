package com.newlight77.demo.location.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class SparkConfig {

    @Value("${app.name:importService}")
    private String appName;


    @Value("${master.uri:localhost}")
    private String masterUri;

    @Bean
    public SparkConf sparkConf() {
        return new SparkConf()
            .setAppName(appName)
            .setMaster(masterUri)
            .set("spark.executor.memory", "1g")
            .set("spark.driver.memory", "1g")
            .set("spark.driver.cores", "2")
            .set("spark.executor.instances", "4");
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
