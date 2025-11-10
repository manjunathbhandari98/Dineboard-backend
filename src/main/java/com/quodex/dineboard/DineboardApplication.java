package com.quodex.dineboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DineboardApplication {

	public static void main(String[] args) {

        SpringApplication.run(DineboardApplication.class, args);
        System.out.println(">>> DATABASE_URL = " + System.getenv("DATABASE_URL"));
        System.out.println(">>> DATASOURCE_URL = " + System.getenv("DATASOURCE_URL"));

    }

}
