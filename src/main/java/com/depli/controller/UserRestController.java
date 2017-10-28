package com.depli.controller;

import com.depli.store.helper.JwtUser;
import com.depli.utility.authentication.JWTInfoProviderComponent;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private JWTInfoProviderComponent infoProviderComponent;

  @Autowired
  private UserDetailsService userDetailsService;

  @GetMapping("user")
  @PreAuthorize("hasRole('Admin')")
  public ResponseEntity<JwtUser> getAuthenticationUser(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader).substring(7);
    String username = infoProviderComponent.getUsernameFromToken(token);
    return new ResponseEntity<>((JwtUser) userDetailsService.loadUserByUsername(username),
        HttpStatus.OK);
  }
}