package in.sp.main.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import in.sp.main.filter.Jwtfilter;
import in.sp.main.services.Studentservice;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private Jwtfilter jwtfilter;
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
	return security
			.csrf(csrf->csrf.disable())
			.cors()
			.and()
			.authorizeHttpRequests(
					requestMatcher->requestMatcher
					.requestMatchers("/student/register","/student/login","/api/ai/chat").permitAll()
					.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					
					.anyRequest().authenticated())
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	
}
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }
    };
}

@Bean 
public UserDetailsService userDetailsService() {
	return new Studentservice();
}

@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	return configuration.getAuthenticationManager();
	
}
@Bean
public AuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
	daoAuthenticationProvider.setUserDetailsService(userDetailsService());
	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	return daoAuthenticationProvider;
}
}
