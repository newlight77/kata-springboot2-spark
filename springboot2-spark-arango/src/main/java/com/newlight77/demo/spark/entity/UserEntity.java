package com.newlight77.demo.spark.entity;

import lombok.*;
import com.arangodb.springframework.annotation.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@Document("users")
@HashIndex(fields = { "username" }, unique = true)
public class UserEntity {

  @Id
  private String id;
  private String firstname;
  private String lastname;
  private String username;

  @Relations(edges = UserRoleRelationEntity.class, lazy = true)
  private Set<RoleEntity> roles;

  @Relations(edges = UserAccountRelationEntity.class, lazy = true)
  private Set<AccountEntity> accounts;

}
