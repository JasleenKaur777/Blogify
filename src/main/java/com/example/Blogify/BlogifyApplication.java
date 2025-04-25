package com.example.Blogify;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogifyApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogifyApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(encoder.encode("Raman@2024"));
		
	}
	//rahul pw-Rahul@2024
	//liza pw-lizaA123@
    

}
