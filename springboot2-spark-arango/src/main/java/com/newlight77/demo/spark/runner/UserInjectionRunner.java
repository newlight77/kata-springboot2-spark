package com.newlight77.demo.spark.runner;

import com.arangodb.springframework.core.ArangoOperations;
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
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;

@ComponentScan("com.newlight77.demo.spark")
public class UserInjectionRunner implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserInjectionRunner.class);

  @Autowired
  private ArangoOperations operations;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JavaSparkContext javaSparkContext;

  @Autowired
  private SparkSession sparkSession;

  @Override
  public void run(final String... args) throws Exception {
    operations.dropDatabase();

    AccountEntity account = AccountEntity.builder()
        .id(UUID.randomUUID().toString())
        .name("default")
        .build();
    RoleEntity role = RoleEntity.builder()
        .name("admin")
        .description("administration")
        .rights(Sets.newHashSet("read"))
        .build();
    UserEntity entity = UserEntity.builder()
        .id(UUID.randomUUID().toString())
        .lastname("Jean")
        .firstname("PLAMONDON")
        .username("jeanplamondon@gmail.com")
        .accounts(Sets.newHashSet(account))
        .roles(Sets.newHashSet(role))
        .build();
    userRepository.save(entity);

    long count = operations.collection(UserEntity.class).count();

    LOGGER.info("count={}", count);
  }
}
