package com.med.book;

import com.med.book.role.Role;
import com.med.book.role.RoleRepository;
import com.med.book.user.User;
import com.med.book.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class BookNetworkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookNetworkApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository, UserRepository userRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				Role role = Role.builder()
						.name("USER")
						.createdDate(LocalDateTime.now())
						.build();
				roleRepository.save(role);
			}

			if (userRepository.findByEmail("med@edd.com").isEmpty()) {
				User user = User.builder()
						.firstName("med")
						.lastName("edd")
						.email("med@edd.com")
						.password("123456789")
						.createdDate(LocalDateTime.now()) // Set the createdDate field
						.build();
				userRepository.save(user);
			}
		};
	}



}
