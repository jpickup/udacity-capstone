package com.johnpickup.route.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFacade implements IAuthenticationFacade {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getAuthenticatedEmail() {
        Authentication authentication = getAuthentication();
        if (!authentication.isAuthenticated()) {
            log.info("User not authenticated");
            return null;
        }
        if (!(authentication.getCredentials() instanceof Jwt)) {
            log.warn("User credentials not expected type: {}", authentication.getCredentials());
            return null;
        }
        return ((Jwt) (authentication.getCredentials())).getClaim("email");
    }
}
