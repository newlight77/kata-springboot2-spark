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

public class CsvUserMapper implements MapFunction<Row, UserEntity> {

  @Override
  public UserEntity call(Row row) {

    java.util.Set<AccountEntity> accounts = accounts(row);
    java.util.Set<RoleEntity> roles = roles(row);
    return user(row, accounts, roles);
  }

  private UserEntity user(Row row, java.util.Set<AccountEntity> accounts, java.util.Set<RoleEntity> roles) {
    return UserEntity.builder()
        .lastname(((String)row.getAs("lastname")).trim())
        .firstname(((String)row.getAs("firstname")).trim())
        .username(((String)row.getAs("username")).trim())
        .accounts(accounts)
        .roles(roles)
        .build();
  }

  private java.util.Set<AccountEntity> accounts(Row row) {
    java.util.Set<AccountEntity> accounts = null;
    try {
      Set<Object> accountSet = ((WrappedArray<Object>) row.getAs("accounts")).toSet();
      accounts = JavaConverters.setAsJavaSetConverter(accountSet).asJava()
          .stream()
          .map(a -> {
            String name = ((GenericRowWithSchema)a).getAs("name");
            return AccountEntity.builder().name(name.trim()).build();
          })
          .collect(Collectors.toSet());
    } catch (Exception e) {

    }
    return accounts;
  }

  private java.util.Set<RoleEntity> roles(Row row) {
    java.util.Set<RoleEntity> roles = null;
    try {
      Set<Object> roleSet = ((WrappedArray<Object>) row.getAs("roles")).toSet();
      roles = JavaConverters.setAsJavaSetConverter(roleSet).asJava()
          .stream()
          .map(a -> {
            String name = ((GenericRowWithSchema)a).getAs("name");
            String description = ((GenericRowWithSchema)a).getAs("description");
            Set<Object> rightSet = ((WrappedArray<Object>)((GenericRowWithSchema)a).getAs("rights")).toSet();
            java.util.Set<String> rights = JavaConverters.setAsJavaSetConverter(rightSet).asJava()
                .stream().map(r -> r.toString()).collect(Collectors.toSet());
            return RoleEntity.builder().name(name.trim()).description(description.trim()).rights(rights).build();
          })
          .collect(Collectors.toSet());
    } catch (Exception e) {

    }
    return roles;
  }
}
