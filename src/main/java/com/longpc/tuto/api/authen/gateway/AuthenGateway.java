package com.longpc.tuto.api.authen.gateway;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.longpc.tuto.api.authen.model.SignInRequestModel;
import com.longpc.tuto.api.authen.model.SignInResponseModel;
import com.longpc.tuto.api.authen.model.SignUpRequestModel;
import com.longpc.tuto.api.authen.service.AuthenService;
import com.longpc.tuto.api.data.entity.authen.Account;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Long PC
 * 28/12/2023| 15:56 | 2023
 **/
@RestController
@RequestMapping("authen")
@CrossOrigin("http://localhost:3000")
public class AuthenGateway {
    @Autowired
    private AuthenService authenService;
    @PostMapping("/sign-in")
    public SignInResponseModel token(@RequestBody SignInRequestModel signInRequestModel) {
        return authenService.signInPassword(signInRequestModel.getUsername(), signInRequestModel.getPassword());
    }
    @PostMapping("/sign-up")
    public Account token(@RequestBody SignUpRequestModel signUpRequestModel) {
        return authenService.signUp(
                signUpRequestModel.getUsername(),
                signUpRequestModel.getPassword(),

                signUpRequestModel.getEmail(),
                signUpRequestModel.getPhone(),
                Set.of("ROLE_USER")
        );
    }
}
