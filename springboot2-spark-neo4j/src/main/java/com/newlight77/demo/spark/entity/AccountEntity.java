package com.newlight77.demo.spark.entity;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.id.UuidStrategy;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@NodeEntity(label = "Account")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = UuidStrategy.class)
  private UUID id;
  private String name;

  @Relationship(type = "USER_ACCOUNT", direction = Relationship.INCOMING)
  private UserEntity user;

  @Relationship(type = "HAS_ROLE", direction = Relationship.OUTGOING)
  private Set<RoleEntity> roles;
}
