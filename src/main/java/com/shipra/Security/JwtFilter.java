package com.shipra.Security;

import com.shipra.Domain.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.core.TokenStreamFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
     private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
             String token = header.substring(7);

             try{
                 if(jwtUtil.isValidToken(token)){
                     String email = jwtUtil.extractEmail(token);
                     Role role = jwtUtil.getRole(token);

                     SimpleGrantedAuthority  authority = new SimpleGrantedAuthority("Role_"+role.name());

                     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                             email,
                             null,
                             Collections.singleton(authority)
                     );
                     SecurityContextHolder.getContext().setAuthentication(authToken);
                 }
             }catch (Exception e){
                logger.warn(e.getMessage());
             }

        }
        filterChain.doFilter(request, response);
    }

}
