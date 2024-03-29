package ua.pp.disik.englishroulette.backend.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.pp.disik.englishroulette.backend.entity.JwtToken;
import ua.pp.disik.englishroulette.backend.service.AuthenticationService;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final AuthenticationService authenticationService;

    public AuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtToken token = (JwtToken) authentication.getCredentials();

        return authenticationService.findByToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
    }
}
