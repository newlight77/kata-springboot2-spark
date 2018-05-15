package com.newlight77.demo.location.csv;

import com.newlight77.demo.location.entity.LocationEntity;
import com.newlight77.demo.location.processor.LocationWorkflow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;

import java.util.List;

public class CsvLocationSparkDataMapper {

    public List<LocationEntity> parallelImport(JavaRDD<LocationEntity> dataset) {
        long start = System.currentTimeMillis();
        return dataset.filter(entity -> {
                new LocationWorkflow().doImport(start, entity);
                return true;
            }).collect();
    }

    public List<LocationEntity> monoImport(Dataset<Row> dataset) {
        long start = System.currentTimeMillis();
        return dataset.map(row -> {

                LocationEntity entity = new CsvLocationMapper().toLocation(row);

                LogManager.getRootLogger().setLevel(Level.DEBUG);
                Log log = LogFactory.getLog(CsvLocationSparkDataMapper.class);
                log.info("entity uid=" + entity.getOid());

                new LocationWorkflow().doImport(start, entity);

                return entity;
            },
            Encoders.javaSerialization(LocationEntity.class))
        .collectAsList();
    }

}
