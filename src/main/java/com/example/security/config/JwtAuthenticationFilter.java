package com.example.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);


    private final  JwtHelper jwtHelper;
    private final UserDetailsService userDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String reqHeader = request.getHeader("Authorization");
        String userName =  null;
        String token = null;

        if(reqHeader!=null && reqHeader.startsWith("Bearer")){
            token = reqHeader.substring(7);
            try{
                userName = this.jwtHelper.getUserNameFromToken(token);
            }catch (Exception exp){
                exp.printStackTrace();
                log.error("Exception occurred");
            }
        }
        if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userD = this.userDetails.loadUserByUsername(userName);
            boolean validateToken = this.jwtHelper.validateToken(token,userD);
            if(validateToken){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userD,null,userD.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }else{
                log.info("Validations fails");
            }
        }
        filterChain.doFilter(request,response);

    }
}
