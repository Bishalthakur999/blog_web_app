package com.bishal.blog.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bishal.blog.security.CustomUserDetailService;
import com.bishal.blog.security.JwtAuthenticationEntryPoint;
import com.bishal.blog.security.JwtAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	 @Autowired
	    private CustomUserDetailService customUserDetailService;
    
	 @Autowired
	    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	    @Autowired
	    private JwtAuthenticationFilter jwtAuthenticationFilter;
	

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        
	            http.csrf()
	                .disable()   
	                .cors(Customizer.withDefaults())
	                .authorizeHttpRequests()
	                .requestMatchers("/api/v1/auth/**").permitAll()
	                .requestMatchers(HttpMethod.GET,"/api/**").permitAll()
	                .anyRequest()
	                .authenticated()
	                .and()
	                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and()
	                .addFilterBefore(this.jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
	                .authenticationProvider(daoAuthenticationProvider());
	        
			return http.build();

	    }
	    @Bean
	    public AuthenticationProvider daoAuthenticationProvider(){
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(customUserDetailService);
	        provider.setPasswordEncoder(bCryptPasswordEncoder());
	        return provider;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }

	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder(){
	        return new BCryptPasswordEncoder();
	    }

//	    @Bean
//	    public FilterRegistrationBean coresFilter() {
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        
//	        
//	        CorsConfiguration corsConfiguration = new CorsConfiguration();
//	        corsConfiguration.setAllowCredentials(true);
//	        corsConfiguration.addAllowedOriginPattern("*");
//	        corsConfiguration.addAllowedHeader("Authorization");
//	        corsConfiguration.addAllowedHeader("Content-Type");
//	        corsConfiguration.addAllowedHeader("Accept");
//	        corsConfiguration.addAllowedMethod("POST");
//	        corsConfiguration.addAllowedMethod("GET");
//	        corsConfiguration.addAllowedMethod("DELETE");
//	        corsConfiguration.addAllowedMethod("PUT");
//	        corsConfiguration.addAllowedMethod("OPTIONS");
//	        corsConfiguration.setMaxAge(3600L);
//	        
//	        source.registerCorsConfiguration("/**", corsConfiguration);
//	        
//	        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//	        
//	        return bean;
//	        
//	    }
	    
	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("http://localhost:3000")
	                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
	                .allowedHeaders("Authorization", "Content-Type", "Accept")
	                .allowCredentials(true).maxAge(3600);
	                
	                
	           
	            }
	        };
	    }
	    
	    

	   
}
