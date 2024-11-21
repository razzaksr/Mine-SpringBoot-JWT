package contain.basic.crud_jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import contain.basic.crud_jwt.secure.OfficialsService;

@Configuration
public class SecurityConfiguration {
    AuthenticationManager authenticationManager;
    @Autowired
    OfficialsService officialsService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin();
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().requestMatchers("/signup").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(officialsService);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        return httpSecurity.build();
    }
}
