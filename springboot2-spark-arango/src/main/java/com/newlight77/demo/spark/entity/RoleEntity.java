package com.newlight77.demo.spark.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@Document("roles")
@HashIndex(fields = { "name" }, unique = true)
public class RoleEntity {

  @Id
  private String name;
  private String description;
  private Set<String> rights;

  @Relations(edges = UserRoleRelationEntity.class, lazy = true)
  private Set<UserRoleRelationEntity> userRoles;

}
