package com.longpc.tuto.api.authen.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.*;

import java.net.URI;

/**
 * Long PC
 * 15/4/24| 17:22 | 2024
 **/
@Configuration
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class GatewayConfig {
    @Autowired
    JwtDecoder decoder;
    private static final String API_GATEWAY = "https://api.longpc.site/gateway";
    //private static final String API_GATEWAY = "http://localhost:8080/gateway";

    @Bean
    public RouterFunction<ServerResponse> getRoute() {
        RouterFunctions.Builder route = RouterFunctions.route();
        return route.GET("/quiz-api/**", request -> {
            String token = "";
            if (ObjectUtils.isEmpty(request.headers().header("Authorization"))) {
                return ServerResponse.badRequest().body("Authorization is required");
            } else {
                try {
                    token = request.headers().header("Authorization").get(0);
                    if (!token.startsWith("Bearer")) {
                        return ServerResponse.badRequest().body("Token is invalid");
                    }
                    Jwt jwt = decoder.decode(token.substring(7));
                    log.info("check jwt: {}", jwt.getClaims());
                } catch (Exception e) {
                    return ServerResponse.badRequest().body("Token is invalid");
                }
            }
            return ServerResponse.permanentRedirect(URI.create(API_GATEWAY + request.path().substring(9))).build();
        }).build();
    }


    @Bean
    public RouterFunction<ServerResponse> putRoute() {
        RouterFunctions.Builder route = RouterFunctions.route();
        RestTemplate restTemplate = new RestTemplate();
        return route.PUT("/quiz-api/**", request -> {
            String token = "";
            if (ObjectUtils.isEmpty(request.headers().header("Authorization"))) {
                return ServerResponse.badRequest().body("Authorization is required");
            } else {
                try {
                    token = request.headers().header("Authorization").get(0);
                    if (!token.startsWith("Bearer")) {
                        return ServerResponse.badRequest().body("Token is invalid");
                    }
                    Jwt jwt = decoder.decode(token.substring(7));
                    log.info("check jwt: {}", jwt.getClaims());
                } catch (Exception e) {
                    return ServerResponse.badRequest().body("Token is invalid");
                }
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(request.body(Object.class), headers);

            restTemplate.put(API_GATEWAY + request.path().substring(9), requestEntity);
            return ServerResponse.ok().build();
        }).build();
    }

    @Bean
    public RouterFunction<ServerResponse> postRoute() {
        RouterFunctions.Builder route = RouterFunctions.route();
        RestTemplate restTemplate = new RestTemplate();
        return route.POST("/quiz-api/**", request -> {
            String token = "";
            if (ObjectUtils.isEmpty(request.headers().header("Authorization"))) {
                return ServerResponse.badRequest().body("Authorization is required");
            } else {
                try {
                    token = request.headers().header("Authorization").get(0);
                    if (!token.startsWith("Bearer")) {
                        return ServerResponse.badRequest().body("Token is invalid");
                    }
                    Jwt jwt = decoder.decode(token.substring(7));
                    log.info("check jwt: {}", jwt.getClaims());
                } catch (Exception e) {
                    return ServerResponse.badRequest().body("Token is invalid");
                }
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(request.body(Object.class), headers);

            Object o = restTemplate.postForObject(API_GATEWAY + request.path().substring(9), requestEntity, Object.class);
            return ServerResponse.ok().body(o);
        }).build();
    }


}
