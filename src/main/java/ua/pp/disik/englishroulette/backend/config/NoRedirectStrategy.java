package ua.pp.disik.englishroulette.backend.config;

import org.springframework.security.web.RedirectStrategy;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoRedirectStrategy implements RedirectStrategy {
    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        
    }
}
