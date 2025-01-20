package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Config;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.security.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

//    private final PersonDetailsService personDetailsService;
//
//    @Autowired
//    public SecurityConfig(PersonDetailsService personDetailsService) {
//        this.personDetailsService = personDetailsService;
//    }
//
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("/auth/login", "/auth/registration", "/auth/logout", "/error")
//                        .permitAll()
//                        .anyRequest().authenticated())
//                .userDetailsService(personDetailsService)
//                .formLogin().loginPage("/auth/login") // Используем форму для входа
//                .loginProcessingUrl("process_login") // Используем для обработки формы входа
//                .defaultSuccessUrl("/hello", true)
//                .failureUrl("/auth/login?error");
//
//        return http.build();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

}
