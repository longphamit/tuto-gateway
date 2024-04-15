package com.longpc.tuto.api.data.entity.cms;

import com.longpc.tuto.api.data.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Long PC
 * 28/12/2023| 15:38 | 2023
 **/
@Getter
@Setter
@Document
public class Post extends BaseEntity {
    private String name;
    private String content;
    private String image;
    private String author;
    private String source;
    private List<String> tagIds;
    private String seriesId;
    private Date publishTime;
    private int status;
}
