package com.longpc.tuto.api.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Long PC
 * 28/12/2023| 15:40 | 2023
 **/
@Getter
@Setter
@Builder
public class Log extends BaseEntity{
    private String content;
    private String action;
    private String partyId;
    private String referId;
}
