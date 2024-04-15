package com.longpc.tuto.api.data.entity.authen;

import com.longpc.tuto.api.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Long PC
 * 28/12/2023| 15:34 | 2023
 **/
@Document
@Getter
@Setter
public class Party extends BaseEntity {
    private int status;
    private String name;
    private String phone;
    private String email;
}
