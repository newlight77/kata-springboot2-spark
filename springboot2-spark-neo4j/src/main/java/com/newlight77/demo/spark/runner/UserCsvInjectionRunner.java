package com.newlight77.demo.spark.runner;

import com.newlight77.demo.spark.csv.CsvSchema;
import com.newlight77.demo.spark.csv.SparkUserMapper;
import com.newlight77.demo.spark.entity.UserEntity;
import com.newlight77.demo.spark.repository.UserRepository;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.net.URL;
import java.util.List;

public class UserCsvInjectionRunner implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserCsvInjectionRunner.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JavaSparkContext javaSparkContext;

  @Autowired
  private SparkSession sparkSession;

  @Override
  public void run(final String... args) throws Exception {
    URL url = this.getClass().getResource("/data/user.csv");

    Dataset<UserEntity> ds = sparkSession.sqlContext().read()
        .option("mode", "DROPMALFORMED")
        .option("header", "true")
        .option("charset", "UTF-8")
        .schema(CsvSchema.SCHEMA)
        .csv(url.getPath())
        .map(new SparkUserMapper(),
            Encoders.kryo(UserEntity.class));

    List<UserEntity> users = ds.collectAsList();

    userRepository.saveAll(users);

    LOGGER.info("total={}", ds.count());
  }
}
