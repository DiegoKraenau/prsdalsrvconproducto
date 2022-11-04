package com.grupogloria.prsdalsrvconproducto.registration.configuration;

import com.grupogloria.prsdalsrvconproducto.registration.configuration.cognito.JwtConfiguration;
import com.grupogloria.prsdalsrvconproducto.registration.configuration.filter.AwsCognitoJwtAuthenticationFilter;
import com.grupogloria.prsdalsrvconproducto.registration.exception.ErrorDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements Ordered {

    private int order = 4;

    private final AwsCognitoJwtAuthenticationFilter awsCognitoJwtAuthenticationFilter;

    private final JwtConfiguration jwtConfiguration;

    public SecurityConfiguration(AwsCognitoJwtAuthenticationFilter awsCognitoJwtAuthenticationFilter, JwtConfiguration jwtConfiguration) {
        this.awsCognitoJwtAuthenticationFilter = awsCognitoJwtAuthenticationFilter;
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().cacheControl();

        http.csrf().disable().authorizeRequests()
                .antMatchers("/actuator/health").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/docs/**").permitAll();

        if (jwtConfiguration.isEnable()) {
            http.authorizeRequests()
                    .antMatchers("/visita", "/visita/**").authenticated()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(awsCognitoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        } else {
            http.authorizeRequests()
                    .antMatchers("/visita", "/visita/**").permitAll();
        }
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());

    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException, IOException {
                ErrorDetails errorDetails = new ErrorDetails(new Date(), "Authentication token was either missing or invalid", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write(errorDetails.convertToJson());

            }
        };
    }
}
