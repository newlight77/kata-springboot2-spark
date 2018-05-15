package com.newlight77.demo.spark.entity;

import lombok.*;
import org.neo4j.ogm.annotation.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@RelationshipEntity(type = "USER_ACCOUNT")
public class UserAccountRelationEntity {

  @Id @GeneratedValue
  private Long id;

  @StartNode
  private UserEntity user;

  @EndNode
  private AccountEntity account;


}
