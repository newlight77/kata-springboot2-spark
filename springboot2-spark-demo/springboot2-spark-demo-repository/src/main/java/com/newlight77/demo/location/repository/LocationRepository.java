package com.newlight77.demo.location.repository;

import com.newlight77.demo.location.entity.LocationEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LocationRepository extends ReactiveMongoRepository<LocationEntity, Long> {


}
