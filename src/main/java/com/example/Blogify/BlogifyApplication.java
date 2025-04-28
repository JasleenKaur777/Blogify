package com.example.Blogify;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Blogify.config.AppConstants;
import com.example.Blogify.entities.Role;
import com.example.Blogify.repositories.RoleRepository;

@SpringBootApplication
public class BlogifyApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepository repo;
	
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
		 try {
		    	Role role=new Role();
		    	role.setId(AppConstants.NORMAL_USER);
		    	role.setName("NORMAL_USER");
		    	
		    	Role role1=new Role();
		    	role1.setId(AppConstants.ADMIN_USER);
		    	role1.setName("ADMIN_USER");
		    	
		    	List<Role> roles=List.of(role,role1);
		    	List<Role> results=repo.saveAll(roles);
		    	results.forEach(r->{
		    		System.out.println(r.getName());
		    	});
		    	
		    }
		    catch(Exception ex) {
		    	ex.printStackTrace();
		    }
		
	}
	//rahul pw-Rahul@2024
	//liza pw-lizaA123@
    

}
