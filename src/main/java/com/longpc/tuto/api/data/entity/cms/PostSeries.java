package com.longpc.tuto.api.data.entity.cms;

import com.longpc.tuto.api.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Long PC
 * 28/12/2023| 15:38 | 2023
 **/
@Getter
@Setter
@Document
public class PostSeries extends BaseEntity {
    private String name;
    private String image;
}
