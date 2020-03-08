package Code.DataBaseProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DataBaseProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DataBaseProjectApplication.class, args);

//		Getting all Created Bean names
		/*
		 * for(String bean: context.getBeanDefinitionNames()) {
		 * System.out.println(bean); }
		 */

	}

}
