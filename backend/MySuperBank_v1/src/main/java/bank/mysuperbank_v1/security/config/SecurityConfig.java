package bank.mysuperbank_v1.security.config;

import bank.mysuperbank_v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserRepository repository;

    @Autowired
    public SecurityConfig(UserRepository repository) {
        this.repository = repository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       return http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth -> {
                   auth.requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll();
                   auth.anyRequest().authenticated();
               })
               .formLogin(withDefaults())
               .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.findUserByUsername(username);
    }
}
