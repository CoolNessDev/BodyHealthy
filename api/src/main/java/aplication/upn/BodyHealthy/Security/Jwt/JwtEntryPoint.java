package aplication.upn.BodyHealthy.Security.Jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JwtEntryPoint implements AuthenticationEntryPoint {
    private final static Logger LOGGER = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        LOGGER.error("Error en el metodo commence");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"NO AUTORIZADO >:v");
    }
}
