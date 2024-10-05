package com.longpc.tuto.api.authen.gateway;


import java.util.List;
import java.util.Set;

import com.longpc.tuto.api.authen.model.ExceptionModel;
import com.longpc.tuto.api.authen.model.SignInRequestModel;
import com.longpc.tuto.api.authen.model.SignInResponseModel;
import com.longpc.tuto.api.authen.model.SignUpRequestModel;
import com.longpc.tuto.api.authen.service.AccountService;
import com.longpc.tuto.api.authen.service.AuthenService;
import com.longpc.tuto.api.constant.ExceptionEnum;
import com.longpc.tuto.api.data.entity.authen.Account;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private AccountService accountService;

    @PostMapping("/sign-in")
    public SignInResponseModel token(@RequestBody SignInRequestModel signInRequestModel) {
        return authenService.signInPassword(signInRequestModel.getUsername(), signInRequestModel.getPassword());
    }

    @PostMapping("/sign-up")
    @SneakyThrows
    public ResponseEntity token(@RequestBody SignUpRequestModel signUpRequestModel) {
        boolean existUsername = authenService.checkExistUsername(signUpRequestModel.getUsername());
        if (existUsername) {
            return ResponseEntity.badRequest().body(ExceptionModel.builder().exception(ExceptionEnum.USERNAME_EXIST.name()).build());
        }
        return ResponseEntity.ok(authenService.signUp(
                signUpRequestModel.getUsername(),
                signUpRequestModel.getPassword(),
                signUpRequestModel.getEmail(),
                signUpRequestModel.getPhone(),
                Set.of("ROLE_USER")
        ));
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }
}
