package com.newlight77.demo.spark.entity;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@Edge
public class UserRoleRelationEntity {

  @Id
  private String id;

  @From
  private UserEntity user;

  @To
  private RoleEntity role;

  public UserRoleRelationEntity(UserEntity user, RoleEntity role) {
    super();
    this.user = user;
    this.role = role;
  }
}
