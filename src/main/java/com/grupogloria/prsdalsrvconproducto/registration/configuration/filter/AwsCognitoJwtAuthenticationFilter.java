package com.grupogloria.prsdalsrvconproducto.registration.configuration.filter;

import com.grupogloria.prsdalsrvconproducto.registration.exception.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AwsCognitoJwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AwsCognitoJwtAuthenticationFilter.class);

    private AwsCognitoIdTokenProcessor awsCognitoIdTokenProcessor;

    public AwsCognitoJwtAuthenticationFilter(AwsCognitoIdTokenProcessor awsCognitoIdTokenProcessor) {
        this.awsCognitoIdTokenProcessor = awsCognitoIdTokenProcessor;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Authentication authentication = null;
        try {
            authentication = awsCognitoIdTokenProcessor.getAuthentication((HttpServletRequest)request);

            if (authentication!=null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            logger.error("Error occured while processing Cognito ID Token",e);
            SecurityContextHolder.clearContext();
            //throw new ServletException("Error occured while processing Cognito ID Token",e);
            // custom error response class used across my project
            ErrorDetails errorDetails = new ErrorDetails(new Date(), "Error occured while processing Cognito ID Token", e.getMessage());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.getWriter().write(errorDetails.convertToJson());

        }

    }
}
