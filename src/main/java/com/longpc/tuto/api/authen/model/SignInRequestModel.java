package com.longpc.tuto.api.authen.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Long PC
 * 29/12/2023| 13:39 | 2023
 **/
@Setter
@Getter
public class SignInRequestModel {
    private String username;
    private String password;
}
