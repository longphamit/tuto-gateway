package com.longpc.tuto.api.authen.gateway;

import com.longpc.tuto.api.authen.service.AccountService;
import com.longpc.tuto.api.data.entity.authen.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

/**
 * Long PC
 * 29/4/24| 20:48 | 2024
 **/
@RestController
@RequestMapping("accounts")
public class AccountGateway {
    @Autowired
    private AccountService accountService;
    @Autowired
    JwtDecoder decoder;

    @GetMapping
    public ResponseEntity getAllAccounts(@RequestHeader("Authorization") String token) {
        if (ObjectUtils.isEmpty(token)) {
            return ResponseEntity.badRequest().body("Authorization is required");
        } else {
            try {
                if (!token.startsWith("Bearer")) {
                    return ResponseEntity.badRequest().body("Token is invalid");
                }
                decoder.decode(token.substring(7));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Token is invalid");
            }
        }
        return ResponseEntity.ok(accountService.getAccounts());
    }
}
