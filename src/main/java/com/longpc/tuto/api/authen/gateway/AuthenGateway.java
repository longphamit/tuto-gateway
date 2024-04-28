package com.longpc.tuto.api.authen.gateway;


import java.util.Set;

import com.longpc.tuto.api.authen.model.SignInRequestModel;
import com.longpc.tuto.api.authen.model.SignInResponseModel;
import com.longpc.tuto.api.authen.model.SignUpRequestModel;
import com.longpc.tuto.api.authen.service.AuthenService;
import com.longpc.tuto.api.constant.ExceptionEnum;
import com.longpc.tuto.api.data.entity.authen.Account;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Long PC
 * 28/12/2023| 15:56 | 2023
 **/
@RestController
@RequestMapping("authen")
public class AuthenGateway {
    @Autowired
    private AuthenService authenService;

    @PostMapping("/sign-in")
    public SignInResponseModel token(@RequestBody SignInRequestModel signInRequestModel) {
        return authenService.signInPassword(signInRequestModel.getUsername(), signInRequestModel.getPassword());
    }

    @PostMapping("/sign-up")
    @SneakyThrows
    public Account token(@RequestBody SignUpRequestModel signUpRequestModel) {
        boolean existUsername = authenService.checkExistUsername(signUpRequestModel.getUsername());
        if (existUsername) {
            throw new IllegalArgumentException(ExceptionEnum.USERNAME_EXIST.name());
        }
        return authenService.signUp(
                signUpRequestModel.getUsername(),
                signUpRequestModel.getPassword(),
                signUpRequestModel.getEmail(),
                signUpRequestModel.getPhone(),
                Set.of("ROLE_USER")
        );
    }

    @GetMapping("/token")
    public String hello() {
        return "Hello";
    }
}
