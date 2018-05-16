package de.hska.iwi.vslab.category.categoryservice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CategoryServiceApplication {

	@Autowired
	private CategoryRepo categoryRepository;

	@PostConstruct
	public void generateTestData() {
		categoryRepository.save(new Category(1000L,"Gemüse"));
		categoryRepository.save(new Category(1001L,"Obst"));
	}

    public static void main(String[] args) {
        SpringApplication.run(CategoryServiceApplication.class, args);
    }

}
