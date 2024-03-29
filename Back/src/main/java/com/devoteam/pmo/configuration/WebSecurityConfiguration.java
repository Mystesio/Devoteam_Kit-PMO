package com.devoteam.pmo.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.devoteam.pmo.service.JwtService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAthenticationEntryPoint jwtAthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private JwtService jwtService;

	@Bean
	@Override

	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();

	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/authenticate", "/createUserWithRole","/deleteUser/{userName}", "/refreshToken", "/createNewRole", 
	    "/projects","/project/{projectId}","/addNewProject","/project/{id}/delete","/project/{projectId}/update",
		"/{projectId}/phases","/phase/{phaseId}","/{projectId}/addNewPhase","/phase/{phaseId}/delete","/phase/{phaseId}/update",
		"/{phaseId}/steps","/step/{stepId}","/{phaseId}/addNewStep","/step/{stepId}/delete","/step/{stepId}/update",
		"/{stepId}/tasks","/task/{taskId}","/{stepId}/addNewTask","/task/{taskId}/delete","/task/{taskId}/update",
		"/all", "/allUsers").permitAll()
		.antMatchers(HttpHeaders.ALLOW).permitAll()
		.antMatchers("/swagger-ui.html#/**","/swagger-ui.html/**", "/swagger-ui/**", "/v3/api-docs/**","/v2/api-docs/**").permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling().authenticationEntryPoint(jwtAthenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
	}
}

