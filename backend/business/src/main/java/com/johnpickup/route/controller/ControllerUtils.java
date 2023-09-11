package com.johnpickup.route.controller;

import com.johnpickup.route.authentication.IAuthenticationFacade;
import com.johnpickup.route.data.Route;
import com.johnpickup.route.data.Visibility;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControllerUtils {
    public static boolean routeIsVisible(Route route, IAuthenticationFacade authenticationFacade) {
        boolean isVisible = (route != null) && (
                Visibility.PUBLIC.equals(route.getVisibility()) ||
                ((route.getOwner() != null) && route.getOwner().equals(authenticationFacade.getAuthenticatedEmail())));

        if (route != null && log.isDebugEnabled()) {
            log.debug("Route {} visibility is {} for user {} as route is set to {} and owner is {}",
                    route.getId(), isVisible, authenticationFacade.getAuthenticatedEmail(), route.getVisibility(), route.getOwner());
        }
        return isVisible;
    }
}
