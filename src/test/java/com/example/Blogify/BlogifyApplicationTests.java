package com.example.Blogify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Blogify.repositories.UserRepository;

@SpringBootTest
class BlogifyApplicationTests {
    private UserRepository userrepo;
	@Test
	void contextLoads() {
		String name= userrepo.getClass().getName();
		String pack=userrepo.getClass().getPackageName();
		System.out.println(name);
		System.out.println(pack);
	}

}
