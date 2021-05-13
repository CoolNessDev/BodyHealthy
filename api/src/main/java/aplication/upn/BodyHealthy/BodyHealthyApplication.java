package aplication.upn.BodyHealthy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.http.HttpHeaders;

@SpringBootApplication
public class BodyHealthyApplication {

	public static void main(String[] args) {
//		System.out.println(HttpHeaders.AUTHORIZATION);
		SpringApplication.run(BodyHealthyApplication.class, args);
	}
}
