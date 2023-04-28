package com.backendnotebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BackendNotebookApplication {



	public static void main(String[] args) {
		SpringApplication.run(BackendNotebookApplication.class, args);
	}



/*	@GetMapping("/products/welcome")
	public String welocome (){
		return "welcome";
	}

	@GetMapping("/products/order")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String order (){
		return "order";
	}

	@GetMapping("/products/product")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String product (){
		return "product";
	}*/
}
