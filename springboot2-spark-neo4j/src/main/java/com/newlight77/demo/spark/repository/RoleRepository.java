package com.newlight77.demo.spark.repository;

import com.newlight77.demo.spark.entity.RoleEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RoleRepository extends Neo4jRepository<RoleEntity, Long> {
}
