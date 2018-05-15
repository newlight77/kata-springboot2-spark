package com.newlight77.demo.spark.csv;

import com.newlight77.demo.spark.entity.AccountEntity;
import com.newlight77.demo.spark.entity.RoleEntity;
import com.newlight77.demo.spark.entity.UserEntity;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import scala.collection.JavaConverters;
import scala.collection.Set;
import scala.collection.mutable.WrappedArray;

import java.util.stream.Collectors;

public class SparkUserMapper implements MapFunction<Row, UserEntity> {

  @Override
  public UserEntity call(Row row) {
    Set<Object> accountSet = ((WrappedArray<Object>) row.getAs("accounts")).toSet();
    java.util.Set<AccountEntity> accounts = accounts(accountSet);
    return UserEntity.builder()
        .lastname(((String)row.getAs("lastname")).trim())
        .firstname(((String)row.getAs("firstname")).trim())
        .username(((String)row.getAs("username")).trim())
        .accounts(accounts)
        .build();
  }

  private java.util.Set<AccountEntity> accounts(Set<Object> accountSet) {
    return JavaConverters.setAsJavaSetConverter(accountSet)
        .asJava()
        .stream()
        .map(a -> {
          Row account = (GenericRowWithSchema)a;
          String name = (account).getAs("name");
          Set<Object> roleSet = ((WrappedArray<Object>) account.getAs("roles")).toSet();
          java.util.Set<RoleEntity> roles = roles(roleSet);
          return AccountEntity.builder().name(name.trim()).roles(roles).build();
        })
        .collect(Collectors.toSet());
  }

  private java.util.Set<RoleEntity> roles(Set<Object> roleSet) {
    return JavaConverters.setAsJavaSetConverter(roleSet)
        .asJava()
        .stream()
        .map(r -> {
          Row role = (GenericRowWithSchema)r;
          String name = (role).getAs("name");
          Set<Object> rightSet = ((WrappedArray<Object>)(role).getAs("rights")).toSet();
          java.util.Set<String> rights = JavaConverters.setAsJavaSetConverter(rightSet).asJava()
              .stream().map(right -> right.toString()).collect(Collectors.toSet());
          return RoleEntity.builder().name(name.trim()).rights(rights).build();
        })
        .collect(Collectors.toSet());
  }
}
