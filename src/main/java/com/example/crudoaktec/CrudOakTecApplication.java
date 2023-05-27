package com.example.crudoaktec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public abstract class CrudOakTecApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudOakTecApplication.class, args);
	}

}
