package com.temzu.market_project.mscore.filters;

import com.temzu.market_project.mscore.exceptions.InvalidTokenException;
import com.temzu.market_project.mscore.interfaces.RedisService;
import com.temzu.market_project.mscore.model.UserInfo;
import com.temzu.market_project.mscore.services.JWTTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTTokenService tokenService;
    private final RedisService redisService;

    public JWTAuthenticationFilter(JWTTokenService tokenService, RedisService redisService) {
        this.tokenService = tokenService;
        this.redisService = redisService;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String servletPath = httpServletRequest.getServletPath();

        if (authorizationHeaderIsInvalid(authorizationHeader)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = createToken(authorizationHeader, servletPath);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean authorizationHeaderIsInvalid(String authorizationHeader) {
        return authorizationHeader == null
                || !authorizationHeader.startsWith("Bearer ");
    }

    private UsernamePasswordAuthenticationToken createToken(String authorizationHeader, String servletPath) throws ExpiredJwtException {
        String token = authorizationHeader.replace("Bearer ", "");
        
        if (servletPath.equals("/logout")) {
            redisService.putInvalidToken(token);
        }

        if (redisService.checkIfInvalid(token))
            throw new InvalidTokenException("Invalid token:" + token);

        UserInfo userInfo = tokenService.parseToken(token);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (userInfo.getRole() != null && !userInfo.getRole().isEmpty()) {
            authorities.add(new SimpleGrantedAuthority(userInfo.getRole()));
        }

        return new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
    }
}
