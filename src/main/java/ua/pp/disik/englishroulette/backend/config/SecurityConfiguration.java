package ua.pp.disik.englishroulette.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
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

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            // swagger paths
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/swagger-v2"),
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
            new AntPathRequestMatcher("/user/**", RequestMethod.GET.name()),
            new AntPathRequestMatcher("/user/*", RequestMethod.POST.name()),
            new AntPathRequestMatcher("/user/**", RequestMethod.DELETE.name()),
            new AntPathRequestMatcher("/word/*", RequestMethod.PUT.name()),
            new AntPathRequestMatcher("/word/**", RequestMethod.DELETE.name())
    );

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                    .sessionCreationPolicy(STATELESS)
                    .and()
                .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                    .requestMatchers(USER_URLS).hasAnyRole("USER", "ADMIN")
                    .requestMatchers(ADMIN_URLS).hasRole("ADMIN")
                    .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter filter = new AuthenticationFilter(PROTECTED_URLS);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler(failureHandler());

        return filter;
    }

    @Bean
    AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy(new NoRedirectStrategy());

        return successHandler;
    }

    @Bean
    AuthenticationFailureHandler failureHandler() {
        return (request, response, exception) -> {
            HttpErrorException httpErrorException = new HttpErrorException(401, exception.getMessage());
            ResponseEntity<Object> responseEntity = httpErrorExceptionAdvice.handle(httpErrorException);

            response.setStatus(responseEntity.getStatusCodeValue());
            responseEntity.getHeaders().entrySet().forEach(
                    entry -> entry.getValue().forEach(
                            value -> response.addHeader(entry.getKey(), value)
                    )
            );
            response.getWriter().print(responseEntity.getBody());
        };
    }

    @Bean
    FilterRegistrationBean<?> registration(AuthenticationFilter filter) {
        FilterRegistrationBean<AuthenticationFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);

        return registration;
    }
}
