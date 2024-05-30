package com.andre.forum_hub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ForumHubApplication{

	public static void main(String[] args) {
		SpringApplication.run(ForumHubApplication.class, args);
	}



}
