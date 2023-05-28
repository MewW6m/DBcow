package com.dbcow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
  @PropertySource(value = "routes.properties")
})
public class DbcowApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbcowApplication.class, args);
	}

}
