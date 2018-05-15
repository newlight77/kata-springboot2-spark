package com.newlight77.demo.location.csv;

import org.apache.spark.sql.types.StructType;

public interface CsvSchema {

    public static final String STREET = "street";
    public static final String CITY = "city";
    public static final String POSTAL_CODE = "postal code";
    public static final String DEPARTMENT = "department";
    public static final String STATE = "state";
    public static final String COUNTRY = "country";

    static StructType SCHEMA = new StructType()
        .add(STREET, "string")
        .add(CITY, "string")
        .add(POSTAL_CODE, "string")
        .add(DEPARTMENT, "string")
        .add(STATE, "string")
        .add(COUNTRY, "string");
}
