package com.newlight77.demo.location.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Location implements Serializable {

    private static final long serialVersionUID = -4035787833875501110L;

    private String oid;
    private String street;
    private String city;
    private String postCode;
    private String department;
    private String state;
    private String country;

}
