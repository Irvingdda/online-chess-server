package com.irvingdda.onlinechess;

import org.springframework.boot.SpringApplication;

public class TestOnlinechessApplication {

	public static void main(String[] args) {
		SpringApplication.from(OnlinechessApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
