package com.openpayd.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan("com.openpayd.currency.domain")
@EnableSwagger2
public class CurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}

}
