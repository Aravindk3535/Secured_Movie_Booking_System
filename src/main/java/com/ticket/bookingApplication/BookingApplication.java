package com.ticket.bookingApplication;

import com.ticket.bookingApplication.model.User;
import com.ticket.bookingApplication.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {
			User user = new User();
			user.setFirstName("Aravind");
			user.setLastName("Vardhan");
			user.setName("Aravind Vardhan");
			user.setGender("Male");
			user.setEmail("aravindk3535@gmail.com");
			user.setPassword("Aravind@1234");
			user.setRole("Admin");

			userRepository.save(user);
			System.out.println("user Saved Successfully");
		};
	}*/

}
