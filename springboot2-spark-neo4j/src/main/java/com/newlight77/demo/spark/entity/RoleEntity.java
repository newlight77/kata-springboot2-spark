package com.newlight77.demo.spark.entity;

import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@NodeEntity(label = "Role")
public class RoleEntity {

  @Id
  private String name;
  private Set<String> rights;

  private RoleEntity() {}

  @Relationship(type = "HAS_ROLE", direction = Relationship.INCOMING)
  private Set<AccountRoleRelationEntity> userRoles;

}
