package mate.academy.cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/cinemahalls")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/movies")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/moviesessions")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/orders")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/orders/complete")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/orders/all")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/shoppingcarts/**")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/users/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
