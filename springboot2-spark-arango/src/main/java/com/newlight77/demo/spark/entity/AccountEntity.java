package com.newlight77.demo.spark.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;
import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
@Document("accounts")
@HashIndex(fields = { "id", "name" }, unique = true)
public class AccountEntity {

  @Id
  private String id;
  private String name;

  @Relations(edges = UserAccountRelationEntity.class, lazy = true)
  private UserEntity user;

}
