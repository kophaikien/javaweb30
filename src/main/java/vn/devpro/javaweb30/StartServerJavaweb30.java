package vn.devpro.javaweb30;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartServerJavaweb30 {

	public static void main(String[] args) {
//		SpringApplication.run(StartServerJavaweb30.class, args);

		SpringApplication start = new SpringApplication(StartServerJavaweb30.class);
		start.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
		start.run(args);
	}

}
