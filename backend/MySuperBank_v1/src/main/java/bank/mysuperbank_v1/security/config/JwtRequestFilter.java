package bank.mysuperbank_v1.security.config;

import bank.mysuperbank_v1.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.AuthenticationException;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    private final UserService userService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtTokenProvider tokenGenerator;

    @Autowired
    public JwtRequestFilter(UserService userService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtTokenProvider tokenGenerator) {
        this.userService = userService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.tokenGenerator = tokenGenerator;
    }




    private String getJWTFromRequest(HttpServletRequest request) {
        try {
            String bearerToken = request.getHeader("Authorization");
            return bearerToken.substring(7);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token;
        try {
            token = getJWTFromRequest(request);
            if (token != null && tokenGenerator.validateToken(token)){
                String username = tokenGenerator.getUsernameFromToken(token);
                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        }catch (AuthenticationException e){
            jwtAuthenticationEntryPoint.commence(request, response, e);
        }
    }
}
