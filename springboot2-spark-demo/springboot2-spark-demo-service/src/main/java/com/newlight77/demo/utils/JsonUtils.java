package com.newlight77.demo.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author jpasseron
 * @author Mathieu Nantern
 * @author Yannick Lacaute
 */
public final class JsonUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

  public static final ObjectMapper MAPPER = initFlatObjectMapper();

  private static ObjectMapper initFlatObjectMapper() {
    ObjectMapper objectMapper = initObjectMapper();
    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
    return objectMapper;
  }

  private JsonUtils() {
    // Prevent class instanciation
  }

  public static ObjectMapper initObjectMapper() {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                .setDateFormat(new ISO8601DateFormat())
                .setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false))
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.findAndRegisterModules();
    return objectMapper;
  }

  public static ObjectMapper getMapper() {
    return MAPPER;
  }

  public static String toJson(final Object o) {
    try {
      return MAPPER.writeValueAsString(o);
    } catch (IOException ioe) {
      String msg = "Error convert to json from object " + o.toString();
      throw new IllegalStateException(msg, ioe);
    }
  }

  public static <T> T fromJson(final String input, final Class<T> resourceClass) {
    try {
      return MAPPER.readValue(input, resourceClass);
    } catch (IOException ioe) {
      String msg = "Error converting from json {" + input + "} " + "to object " + resourceClass.getName();
      throw new IllegalStateException(msg, ioe);
    }
  }


}
