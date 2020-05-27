package code.DataBaseProject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(env.getProperty("jpa.user.UserName")).password("{noop}"+ env.getProperty("jpa.user.Password")+"").roles("USER")
		.and();
//		.withUser(env.getProperty("jpa.admin.UserName")).password("{noop}"+ env.getProperty("jpa.admin.Password")+"").roles("USER", "ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// To authorize any requests without Roles Consideration
		
		  http .csrf().disable() .authorizeRequests().anyRequest().authenticated()
		  .and() .httpBasic();
		 
//		To Authorize on basis Of Roles given to Api's
//		http.csrf().disable()
//        .authorizeRequests()
//        .antMatchers(HttpMethod.GET,"/user/**").hasRole("ADMIN")
//        .and().httpBasic();
		
	}

}
