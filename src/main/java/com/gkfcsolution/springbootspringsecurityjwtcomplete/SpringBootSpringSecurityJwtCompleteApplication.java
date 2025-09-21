package com.gkfcsolution.springbootspringsecurityjwtcomplete;

import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.entity.User;
import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootSpringSecurityJwtCompleteApplication {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void initUsers(){
		List<User> users = Stream.of(
				new User(101, "admin", passwordEncoder.encode("admin123"), "admin@gmail.com"),
				new User(102, "frank",  passwordEncoder.encode("frank123"), "frank@gmail.com"),
				new User(103, "user1",  passwordEncoder.encode("password1"), "frank@gmail.com"),
				new User(104, "user2",  passwordEncoder.encode("password2"), "frank@gmail.com"),
				new User(105, "user3",  passwordEncoder.encode("password3"), "frank@gmail.com")
		).collect(Collectors.toList());

		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringSecurityJwtCompleteApplication.class, args);
	}

}
