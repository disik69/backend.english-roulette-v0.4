package ua.pp.disik.englishroulette.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.pp.disik.englishroulette.backend.exception.HttpErrorException;
import ua.pp.disik.englishroulette.backend.exception.HttpErrorExceptionAdvice;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            // swagger paths
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/v3/api-docs/**"),

            new AntPathRequestMatcher("/actuator/**"),

            new AntPathRequestMatcher("/"),
            new AntPathRequestMatcher("/signup"),
            new AntPathRequestMatcher("/signin"),
            new AntPathRequestMatcher("/check-token"),
            new AntPathRequestMatcher("/error")
    );

    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

    private static final RequestMatcher USER_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/user/*", RequestMethod.GET.name()),
            new AntPathRequestMatcher("/user/*", RequestMethod.PUT.name()),
            new AntPathRequestMatcher("/word/**", RequestMethod.GET.name()),
            new AntPathRequestMatcher("/word", RequestMethod.POST.name()),
            new AntPathRequestMatcher("/exercise/**")
    );

    private static final RequestMatcher ADMIN_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/user/**"),
            new AntPathRequestMatcher("/word/**")
    );

    public static final String SECURITY_HEADER_NAME = "Token";

    private AuthenticationProvider authenticationProvider;
    private HttpErrorExceptionAdvice httpErrorExceptionAdvice;

    @Autowired
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Autowired
    public void setHttpErrorExceptionAdvice(HttpErrorExceptionAdvice httpErrorExceptionAdvice) {
        this.httpErrorExceptionAdvice = httpErrorExceptionAdvice;
    }

    @Bean
    protected AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(authenticationFilter(), AuthorizationFilter.class)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers(USER_URLS).hasAnyRole("USER", "ADMIN");
                    registry.requestMatchers(ADMIN_URLS).hasRole("ADMIN");
                    registry.anyRequest().permitAll();
                })
                .csrf(configurer -> {
                    configurer.disable();
                })
                .sessionManagement(configurer -> {
                    configurer.disable();
                })
                .exceptionHandling(configurer -> {
                    configurer.accessDeniedHandler(accessDeniedHandler());
                });
        return httpSecurity.build();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return (request, response, exception) -> {
            sendHttpError(response, new HttpErrorException(403, exception.getMessage()));
        };
    }

    @Bean
    AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter filter = new AuthenticationFilter(PROTECTED_URLS);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());

        return filter;
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy(new NoRedirectStrategy());

        return successHandler;
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            sendHttpError(response, new HttpErrorException(401, exception.getMessage()));
        };
    }

    @Bean
    FilterRegistrationBean<?> registration(AuthenticationFilter filter) {
        FilterRegistrationBean<AuthenticationFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);

        return registration;
    }

    private void sendHttpError(HttpServletResponse response, HttpErrorException error) throws IOException {
            ResponseEntity<Object> responseEntity = httpErrorExceptionAdvice.handle(error);

            response.setStatus(responseEntity.getStatusCode().value());
            responseEntity.getHeaders().entrySet().forEach(
                    entry -> entry.getValue().forEach(
                            value -> response.addHeader(entry.getKey(), value)
                    )
            );
            response.getWriter().print(responseEntity.getBody());
    }
}
