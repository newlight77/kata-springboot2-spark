package com.newlight77.demo.spark.runner;

import com.newlight77.demo.spark.entity.AccountEntity;
import com.newlight77.demo.spark.entity.RoleEntity;
import com.newlight77.demo.spark.entity.UserEntity;
import com.newlight77.demo.spark.repository.UserRepository;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spark_project.guava.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.HashSet;

public class UserInjectionRunner implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserInjectionRunner.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JavaSparkContext javaSparkContext;

  @Autowired
  private SparkSession sparkSession;

  @Override
  public void run(final String... args) throws Exception {
    RoleEntity role = RoleEntity.builder()
        .name("admin")
        .rights(Sets.newHashSet("read"))
        .build();
    AccountEntity account = AccountEntity.builder()
        .name("default")
        .roles(Sets.newHashSet(role))
        .build();
    UserEntity entity = UserEntity.builder()
        .lastname("Jean")
        .firstname("PLAMONDON")
        .username("jeanplamondon@gmail.com")
        .accounts(Sets.newHashSet(account))
        .build();
    userRepository.save(entity);
  }
}
