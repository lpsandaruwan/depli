package com.depli.store.helper;

import com.depli.utility.authentication.JWTInfoProviderComponent;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JWTInfoProviderComponent infoProviderComponent;

  @Value("${jwt.header}")
  private String tokenHeader;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws ServletException, IOException {
    final String requestHeader = httpServletRequest.getHeader(this.tokenHeader);

    String username = null;
    String authToken = null;
    if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
      authToken = requestHeader.substring(7);

      try {
        username = infoProviderComponent.getUsernameFromToken(authToken);
      } catch (IllegalArgumentException ex) {
        LOGGER.error("An error occurred during getting username from token", ex);
      } catch (ExpiredJwtException ex) {
        LOGGER.warn("The token is expired and not valid anymore", ex);
      }
    } else {
      LOGGER.warn("Couldn't find bearer string, will ignore the header");
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      if (infoProviderComponent.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null,
            userDetails.getAuthorities());
        authentication
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        LOGGER.info("authenticated user %s, setting security context", username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}