package com.longpc.tuto.api.authen.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Long PC
 * 29/12/2023| 13:40 | 2023
 **/
@Getter
@Setter
@Builder
public class SignInResponseModel {
    private String partyId;
    private String token;
}
