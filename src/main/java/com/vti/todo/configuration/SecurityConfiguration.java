package com.vti.todo.configuration;

import com.vti.todo.security.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration {
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    @Primary
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().disable()
                .csrf().disable()
                .authorizeRequests(auth -> auth
                        .antMatchers("/api/v1/accounts/login",
                                "/api/v1/accounts/register",
                                "/swagger-ui/**",
                                "/api/v1/accounts/forgot", "/api/v1/accounts/forgot/**"
                        ).permitAll() //cho phep cac URL cos pattern nhu tren truy cap ma khong can authentication
                        //.antMatchers(HttpMethod.GET).permitAll()
                        //cho phep cac URL cos pattern nhu tren voi method GET truy cap ma khong can authentication
                        .anyRequest().authenticated() // cac URL con lai phai xac thuc (authentication)
                ).httpBasic(Customizer.withDefaults());

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


//    private JwtTokenFilter jwtTokenFilter;
//@Bean
//@Primary
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//    http.csrf().disable() // de tranh loi 403
//            .cors().disable() // de web goi duoc API
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeHttpRequests()
//            .antMatchers(HttpMethod.POST, "/accounts/login", "/accounts/register").permitAll()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .httpBasic(Customizer.withDefaults());
//
//    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
//}
}
