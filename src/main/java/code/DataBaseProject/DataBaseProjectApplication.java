package code.DataBaseProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories
@EntityScan("code.DataBaseProject.models")
public class DataBaseProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DataBaseProjectApplication.class, args);
		
		/*
		 * for(String beans : context.getBeanDefinitionNames()) {
		 * System.out.println(beans); }
		 */
		 

		

	}

}
