package com.newlight77.demo.spark.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.newlight77.demo.spark.entity.RoleEntity;

public interface RoleRepository extends ArangoRepository<RoleEntity> {
}
