package com.longpc.tuto.api.data.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Long PC
 * 28/12/2023| 15:34 | 2023
 **/
@Getter
@Setter
public class BaseEntity {
    private String id;
    private Date createdTime;
    private Date updatedTime;
}
