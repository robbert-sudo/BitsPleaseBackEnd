package bitspleaseApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private JwtRequestFilter jwtRequestFilter;

    //@Lazy om circular reference te voorkomen
    @Autowired
    WebSecurityConfiguration(DataSource dataSource,@Lazy JwtRequestFilter jwtRequestFilter) {
        this.dataSource = dataSource;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(POST, "/user").permitAll()
                .antMatchers(GET,"/user/**").authenticated()
                .antMatchers(PATCH, "/user/{user_id}").authenticated()
                .antMatchers(DELETE, "/user/**").authenticated()
                .antMatchers(PATCH, "/user/delete/**").authenticated()

                .antMatchers(PATCH,"/users/{^[\\w]$}/password").authenticated()
                .antMatchers(GET, "/users").hasRole("ADMIN")
                .antMatchers("/users/**").authenticated()

                .antMatchers("/admin/*").hasRole("ADMIN")

                .antMatchers(POST,"/authenticate").permitAll()

                .antMatchers("/games/**").authenticated()

                .antMatchers("/sellerratings").authenticated()
                .antMatchers("/sellerratings/**").authenticated()

                .antMatchers("/getaverage/**").authenticated()

                .antMatchers(GET,"/authenticated").authenticated()

                .antMatchers("/admin/**").hasRole("ADMIN")


                .anyRequest().denyAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }



}
