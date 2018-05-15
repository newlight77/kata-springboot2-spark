package com.newlight77.demo.location.service;


import com.newlight77.demo.location.entity.LocationEntity;
import com.newlight77.demo.location.csv.CsvLocationMapper;
import com.newlight77.demo.location.csv.CsvSchema;
import com.newlight77.demo.location.csv.CsvLocationSparkDataMapper;
import com.newlight77.demo.location.processor.LocationWorkflow;
import com.newlight77.demo.location.repository.LocationRepository;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.List;

public class LocationService {

    private final Logger LOGGER = LoggerFactory.getLogger(LocationService.class);

    private final LocationRepository locationRepository;

    private final JavaSparkContext javaSparkContext;
    private final SparkSession sparkSession;

    public LocationService(LocationRepository locationRepository, JavaSparkContext javaSparkContext, SparkSession sparkSession) {
        this.locationRepository = locationRepository;
        this.javaSparkContext = javaSparkContext;
        this.sparkSession = sparkSession;
    }

    public long importFromFile(File file) {

        LOGGER.info("start upload with fileName={}", file.getName());

        Dataset<Row> df = sparkSession.read()
            .option("mode", "DROPMALFORMED")
            .schema(CsvSchema.SCHEMA)
            .option("header", "true")
            .option("delimiter", ";")
            .csv(file.getAbsolutePath());

        //Register a table in spark sql metastore to perform SQL operation
        df.createOrReplaceTempView("importService");

        df.show();

        LOGGER.info("upload with columns={}", df.columns());
        LOGGER.info("upload with size={}", df.count());

        long total = df.count();
        if (total <= 0 ) {
            return total;
        }

//        reactor(df);
//        monoSpark(df);
        parallelSpark(df);

        return total;
    }


    private void reactor(Dataset<Row> df) {
        List<LocationEntity> entities = df
            .map(row -> new CsvLocationMapper().toLocation(row),
                Encoders.javaSerialization(LocationEntity.class))
            .collectAsList();
        Flux<LocationEntity> flux = locationRepository.saveAll(entities);

        new LocationWorkflow().reactor(flux);
    }

    private void monoSpark(Dataset<Row> df) {
        List<LocationEntity> entities =
            new CsvLocationSparkDataMapper().monoImport(df);
        locationRepository.saveAll(entities);
    }

    private void parallelSpark(Dataset<Row> df) {
        List<LocationEntity> entities = df
            .map(row -> new CsvLocationMapper().toLocation(row),
                Encoders.javaSerialization(LocationEntity.class))
            .collectAsList();

        Flux<LocationEntity> flux = locationRepository.saveAll(entities);

        JavaRDD<LocationEntity> rdd = javaSparkContext.parallelize(entities);

        new CsvLocationSparkDataMapper().parallelImport(rdd);

    }
}
