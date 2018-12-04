package com.sistemadeeventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemadeeventosApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SistemadeeventosApplication.class, args);
		//System.out.print(new BCryptPasswordEncoder().encode("123"));
	}
}
