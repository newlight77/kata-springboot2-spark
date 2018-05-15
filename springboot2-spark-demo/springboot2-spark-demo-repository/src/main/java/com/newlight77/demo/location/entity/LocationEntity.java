package com.newlight77.demo.location.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class LocationEntity implements Serializable {

    private static final long serialVersionUID = -8294034605234040754L;

    @Id
    private Integer id;
    private String oid;
    private String data;
    private Date creationDate;
    private Date modificationDate;
}
