package com.longpc.tuto.api.authen.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Long PC
 * 29/12/2023| 14:45 | 2023
 **/
@Getter
@Setter
public class SignUpRequestModel {
    private String username;
    private String email;
    private String phone;
    private String password;
}
