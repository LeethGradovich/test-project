package com.example.bpotp.sequrity.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);
    private JwtUtils jwtUtils;

    public JwtTokenFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        try
        {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String jwt = resolveToken(httpServletRequest);
            if (StringUtils.hasText(jwt))
            {
                if (this.jwtUtils.validateJwtToken(jwt))
                {
                    Authentication authentication = this.jwtUtils.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
        catch (ExpiredJwtException eje) {
            log.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String resolveToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader(JwtConfigurer.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        String jwt = request.getParameter(JwtConfigurer.AUTHORIZATION_TOKEN);
        if (StringUtils.hasText(jwt)) {
            return jwt;
        }
        return null;
    }

}