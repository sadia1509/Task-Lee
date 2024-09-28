package com.project.task.configs;

import com.project.task.services.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SecurityUserService securityUserService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detail service object
        daoAuthenticationProvider.setUserDetailsService(securityUserService);
        // password encoder object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // disabled csrf
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // controlling urls (assess / authorization)
        httpSecurity.authorizeHttpRequests(authorized -> {
            authorized.requestMatchers("/web/users/**").authenticated();
            authorized.anyRequest().permitAll();
        });
        // default login form
//        httpSecurity.formLogin(Customizer.withDefaults());
        // custom login form
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/web/login")
                    .loginProcessingUrl("/web/validateLogin")
                    .successForwardUrl("/web/home")
                    .failureForwardUrl("/web/users")
                    .failureUrl("/web/login?error=true")
                    .usernameParameter("email")
                    .passwordParameter("password");
        });

//        httpSecurity.logout(logout -> {
//            logout.logoutUrl("/web/logout")
//                    .logoutSuccessUrl("/web/login?logout=true")
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID");
//        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

/*
//    private InMemoryUserDetailsManager inMemoryUserDetailsManager;
@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .build();
        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
        return inMemoryUserDetailsManager;
    }
 */
