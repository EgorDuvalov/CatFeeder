package com.bsu.catfeeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class CatFeederApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatFeederApplication.class, args);
	}

}
