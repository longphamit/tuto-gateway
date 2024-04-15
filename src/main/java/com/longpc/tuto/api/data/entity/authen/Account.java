package com.longpc.tuto.api.data.entity.authen;

import com.longpc.tuto.api.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * Long PC
 * 28/12/2023| 15:35 | 2023
 **/
@Getter
@Setter
@Document
public class Account extends BaseEntity {
    protected String username;
    protected String partyId;
    protected String password;
    protected int status;
    protected Set<String> role;
}
