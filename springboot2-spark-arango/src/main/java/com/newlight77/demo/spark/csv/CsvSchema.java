package com.newlight77.demo.spark.csv;

import org.apache.spark.sql.types.StructType;

public interface CsvSchema {

    StructType SCHEMA = new StructType()
        .add("lastname", "string")
        .add("firstname", "string")
        .add("username", "string");
}
