package com.example.G_Books;

import com.example.G_Books.entity.Role;
import com.example.G_Books.repository.RoleDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class GBooksApplication {

	public static void main(String[] args) { SpringApplication.run(GBooksApplication.class, args);}

	@Bean
	public CommandLineRunner runner (RoleDao roleDao){
		return args -> {
			if(roleDao.findByName("USER").isEmpty()){
				roleDao.save(Role.builder().name("USER").build());
			}
		};
	}
}
