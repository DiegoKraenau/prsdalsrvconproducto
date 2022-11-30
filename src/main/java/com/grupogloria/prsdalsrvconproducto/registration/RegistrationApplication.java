package com.grupogloria.prsdalsrvconproducto.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan("pr.sd.al.srv.conexion.producto")
@EnableDiscoveryClient
@SpringBootApplication
public class RegistrationApplication {
	// private static final Logger logger =
	// LogManager.getLogger(RegistrationApplication.class);

	public static void main(String[] args) {
		// logger.trace("Holaaaaaaaaaaaaa");
		SpringApplication.run(RegistrationApplication.class, args);
	}

}
