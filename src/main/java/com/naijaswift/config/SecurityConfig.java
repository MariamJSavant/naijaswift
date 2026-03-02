package com.naijaswift.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.naijaswift.entity.User;
import com.naijaswift.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   private final UserRepository userRepository;

   public SecurityConfig(UserRepository userRepository){
      this.userRepository=userRepository;
   }
   @Bean
   public UserDetailsService userDetailsService(){
      return email->{
         User user =userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User with email: "+email+" not found."));
         return 
         org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .roles("USER")
            .build();

       

   };
      }
   @Bean
   public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
   }
   
    @Bean
   public AuthenticationManager 
   authenticationManager(AuthenticationConfiguration config) throws
   Exception{
      return config.getAuthenticationManager();
   }

   @Bean
   public AuthenticationProvider 
   authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
     authProvider.setPasswordEncoder(passwordEncoder);
     return authProvider;
   }
   
  
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProvider authenticationProvider) throws Exception{
    http
    .csrf(csrf -> csrf.disable())
     .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/auth/''","/api/users/register").permitAll()
      .anyRequest().authenticated()
     )
     .sessionManagement(session -> session
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
     ).authenticationProvider(authenticationProvider);
     return http.build();
   }
  
}
