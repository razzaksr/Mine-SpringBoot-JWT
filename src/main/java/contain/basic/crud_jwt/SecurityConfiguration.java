package contain.basic.crud_jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    // AuthenticationManager authenticationManager;
    @Autowired
    contain.basic.crud_jwt.secure.OfficialsUserDetailsService OfficialsUserDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(OfficialsUserDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // httpSecurity.formLogin();
        // httpSecurity.httpBasic();
        //httpSecurity.csrf().disable();
        httpSecurity.csrf(customizer->customizer.disable());
        // httpSecurity.authorizeRequests().requestMatchers("/signup").permitAll();
        httpSecurity.authorizeHttpRequests(request->request.requestMatchers("signup","login").permitAll());
        //httpSecurity.authorizeRequests().anyRequest().authenticated();
        httpSecurity.authorizeHttpRequests(request->request.anyRequest().authenticated());
        // AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        // builder.userDetailsService(officialsService);
        // authenticationManager = builder.build();
        // httpSecurity.authenticationManager(authenticationManager);
        return httpSecurity.build();
    }


}
