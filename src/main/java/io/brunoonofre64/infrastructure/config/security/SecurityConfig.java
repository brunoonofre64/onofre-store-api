package io.brunoonofre64.infrastructure.config.security;

import io.brunoonofre64.domain.enums.Profiles;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends AbstractHttpConfigurer<SecurityConfig, HttpSecurity> {

    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("bruno")
                .password(bCryptPasswordEncoder.encode("1234"))
                .roles(Profiles.ADMIN.getRoles())
                .and()
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void init(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
                .antMatchers("/api/v1/cliente")
                  .hasAnyRole("ADMIN", "EMPLOYEE", "MANAGER")
                .antMatchers("/api/v1/produto")
                  .hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/api/v1/pedido")
                  .hasAnyRole("ADMIN", "EMPLOYEE", "MANAGER")
                .antMatchers("/api/v1/funcionario")
                  .hasAnyRole("ADMIN", "MANAGER")
            .anyRequest().permitAll()
            .and()
        .formLogin();
    }

}
