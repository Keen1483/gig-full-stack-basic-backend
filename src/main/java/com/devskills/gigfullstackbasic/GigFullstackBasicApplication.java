package com.devskills.gigfullstackbasic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.devskills.gigfullstackbasic.model.User;
import com.devskills.gigfullstackbasic.service.UserService;
import com.github.javafaker.Faker;
*/

@SpringBootApplication
public class GigFullstackBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(GigFullstackBasicApplication.class, args);
	}
	/*
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			Faker faker = new Faker();
			AtomicLong serialNumber = new AtomicLong(000_000_000_001L);
			for (int i = 0; i < 100; i++) {
				userService.saveUser(new User(
						null,
						faker.internet().emailAddress(),
						faker.name().username(),
						"1234",
						serialNumber.getAndIncrement()
						));
			}
		};
	}
	*/
}
