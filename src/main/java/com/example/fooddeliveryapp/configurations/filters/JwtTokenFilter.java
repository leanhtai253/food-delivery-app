package com.example.fooddeliveryapp.configurations.filters;

import com.example.fooddeliveryapp.configurations.security.JwtUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    private Gson gson = new Gson();
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        // Find Authorization header
        System.out.println("Arrived at Jwt Filter");
        String token = getTokenFromHeader(request);
        if (token != null) {
            if (jwtUtil.validate(token)) {
                String subjectJson = jwtUtil.getSubject(token);
                Map<String,Object> subjectData = gson.fromJson(subjectJson,Map.class);
                if (StringUtils.hasText(subjectData.get("type").toString())
                    && !subjectData.get("type").equals("refresh")) {
                    UsernamePasswordAuthenticationToken authenticationToken = new
                            UsernamePasswordAuthenticationToken("","", new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        System.out.println("Token: " + token);
        filterChain.doFilter(request,response);
    }

    public String getTokenFromHeader(HttpServletRequest request) {
        // Get Token from field Authorization from Header
        String strToken = request.getHeader("Authorization");
        if (StringUtils.hasText(strToken) && strToken.startsWith("Bearer ")) {
            return strToken.substring(7);
        } else {
            return null;
        }
    }
}
