package br.com.onofrestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "br.com.onofrestore.infrastructure.jpa")
public class OnofreStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnofreStoreApplication.class, args);
	}

}
