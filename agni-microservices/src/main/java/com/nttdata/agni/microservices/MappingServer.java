package com.nttdata.agni.microservices;




import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;





/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link AccountsConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author harendra
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(MappingConfiguration.class)
public class MappingServer {

	@Autowired
	protected MappingListRepository accountRepository;

	protected Logger logger = Logger.getLogger(MappingServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for mapping-server.properties or
		// mapping-server.yml
		System.setProperty("spring.config.name", "mapping-server");

		SpringApplication.run(MappingServer.class, args);
	}
}

