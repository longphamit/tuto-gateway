package com.longpc.tuto.api.data.entity.cms;

import com.longpc.tuto.api.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Long PC
 * 28/12/2023| 15:39 | 2023
 **/
@Document
@Setter
@Getter
public class PostTopic extends BaseEntity {
    private String name;
}
