package com.longpc.tuto.api;

import com.longpc.tuto.api.authen.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TutoGatewayApplication {

	@Autowired
	AuthenService authenService;
	public static void main(String[] args) {
		SpringApplication.run(TutoGatewayApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		authenService.createAdminAccount();
	}
}
