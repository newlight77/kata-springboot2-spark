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
public class UserAccountRelationEntity {

  @Id
  private String id;

  @From
  private UserEntity user;

  @To
  private AccountEntity account;

  public UserAccountRelationEntity(UserEntity user, AccountEntity account) {
    this.user = user;
    this.account = account;
  }

}
