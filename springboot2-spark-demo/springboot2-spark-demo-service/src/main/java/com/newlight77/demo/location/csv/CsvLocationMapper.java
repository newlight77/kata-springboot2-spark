package com.newlight77.demo.location.csv;

import com.newlight77.demo.location.entity.LocationEntity;
import com.newlight77.demo.location.model.Location;
import com.newlight77.demo.utils.JsonUtils;
import org.apache.spark.sql.Row;

import java.util.Date;
import java.util.UUID;

public class CsvLocationMapper {

    public LocationEntity toLocation(Row row) {

        Location location = Location.builder()
            .street(row.getString(0))
            .city(row.getString(1))
            .postCode(row.getString(2))
            .department(row.getString(3))
            .state(row.getString(4))
            .country(row.getString(5))
            .build();

        return LocationEntity.builder()
            .modificationDate(new Date())
            .oid(UUID.randomUUID().toString())
            .data(JsonUtils.toJson(location))
            .creationDate(new Date())
            .build();
    }

}
