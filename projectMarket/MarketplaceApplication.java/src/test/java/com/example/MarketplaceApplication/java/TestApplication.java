package com.example.MarketplaceApplication.java;

import com.example.MarketplaceApplication.java.marketplace.MarketPlaceApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.from(MarketPlaceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
