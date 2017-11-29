package com.nttdata.agni.microservices.test;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nttdata.agni.microservices.MappingConfiguration;




@SpringBootApplication
@Import(MappingConfiguration.class)
class MappingMain {
	public static void main(String[] args) {
		// Tell server to look for mapping-server.properties or
		// mapping-server.yml
		System.setProperty("spring.config.name", "mapping-server");
		SpringApplication.run(MappingMain.class, args);
	}
}


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MappingMain.class)
public class MappingControllerIntegrationTests extends AbstractMappingControllerTests {

}
