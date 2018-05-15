package com.newlight77.demo.spark.repository;

import com.newlight77.demo.spark.entity.AccountEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AccountRepository extends Neo4jRepository<AccountEntity, Long> {
}
