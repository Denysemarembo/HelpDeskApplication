package rw.gov.rra.helpdeskapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/requests/**")
                        //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/resources/**").permitAll()
                        .requestMatchers("/dashboard", "/dashboard/**").authenticated()
                        .requestMatchers("/requestForm", "/submitRequest", "/postRequests").hasRole("STAFF")
                        .requestMatchers("/viewRequests").hasAnyRole("SUPER_USER" ,"HOD")
                        .requestMatchers("/requests/**").hasAnyRole("SUPER_USER", "HOD", "ASSIGNED_USER")
                        .requestMatchers(HttpMethod.GET, "/requests/**").hasAnyRole("SUPER_USER", "HOD", "ASSIGNED_USER")
                        .requestMatchers(HttpMethod.DELETE, "/requests/**").hasAnyRole("SUPER_USER", "HOD","ASSIGNED_USER")
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .sessionManagement(session -> {
                    session
                            .maximumSessions(1)
                            .maxSessionsPreventsLogin(false);
                    session
                            .invalidSessionUrl("/login?sessionExpired")
                            .sessionFixation().migrateSession()
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

                });

        return http.build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);

    }
}
