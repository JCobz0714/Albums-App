package com.jacobo.springboot.albums_app.albums_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AlbumsAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AlbumsAppApplication.class, args);
	}

}
