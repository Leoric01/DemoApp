package bank.mysuperbank_v1.security.config;

import bank.mysuperbank_v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserRepository repository;

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(UserRepository repository, JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.repository = repository;
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       return http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth -> {
                   auth.requestMatchers(new AntPathRequestMatcher("/auth/**"))
                           .permitAll();
                   auth.anyRequest()
                           .authenticated();
               })
               .sessionManagement(session -> {
                   session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
               })
               .authenticationProvider(authenticationProvider)
               .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }
}
