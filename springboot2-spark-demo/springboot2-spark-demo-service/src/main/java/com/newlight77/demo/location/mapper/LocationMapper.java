package com.newlight77.demo.location.mapper;

import com.newlight77.demo.location.model.Location;
import com.newlight77.demo.location.entity.LocationEntity;
import com.newlight77.demo.utils.JsonUtils;

import java.util.Date;

public class LocationMapper {

    public LocationEntity toLocation(Location location) {
        return LocationEntity.builder()
            .modificationDate(new Date())
            .oid(location.getOid())
            .data(JsonUtils.toJson(location))
            .creationDate(new Date())
            .build();
    }

    public Location toLocation(LocationEntity entity) {
        return JsonUtils.fromJson(entity.getData(), Location.class);
    }
}
