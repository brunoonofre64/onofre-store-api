package br.com.onofrestore.infrastructure.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class OnofreSecurity {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    public String getUserUuid() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("uuid_usuario");
    }
}
