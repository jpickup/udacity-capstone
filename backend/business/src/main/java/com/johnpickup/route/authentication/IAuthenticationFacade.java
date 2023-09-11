package com.johnpickup.route.authentication;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();

    String getAuthenticatedEmail();
}
