package com.depli.security.controller;

import com.depli.security.JwtAuthenticationRequest;
import com.depli.security.JwtTokenUtil;
import com.depli.security.JwtUser;
import com.depli.security.controller.models.InputUser;
import com.depli.security.model.Authority;
import com.depli.security.model.AuthorityName;
import com.depli.security.model.User;
import com.depli.security.service.JwtAuthenticationResponse;
import com.depli.security.service.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class AuthenticationRestController {

  @Value("${jwt.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {

    final Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
        authenticationRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }

  @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader).substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @RequestMapping(value = "${jwt.route.authentication.onboard}", method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> createNewUser(HttpServletRequest request, @RequestBody InputUser inputUser) {
    try {
      User user = new User();
      long id = new Random().nextLong();
      user.setId(id);
      user.setUsername(inputUser.getUsername());
      user.setPassword(new BCryptPasswordEncoder().encode(inputUser.getPassword()));
      user.setFirstname(inputUser.getFirstname());
      user.setLastname(inputUser.getLastname());
      user.setEmail(inputUser.getEmail());
      user.setEnabled(inputUser.isEnabled());
      user.setLastPasswordResetDate(new Date());

      List<Authority> authorities = new ArrayList<>();
      for (String inputAuth: inputUser.getAuthorities()) {
        if (AuthorityName.ROLE_ADMIN.name().equals("ROLE_" + inputAuth)) {

//          ((JwtUserDetailsServiceImpl) userDetailsService).createUserAuthorityEntry(userAuthority);
          authorities.add(new Authority().withId(1L).withName(AuthorityName.ROLE_ADMIN));
        }
        if (AuthorityName.ROLE_USER.name().equals("ROLE_" + inputAuth)) {

//          ((JwtUserDetailsServiceImpl) userDetailsService).createUserAuthorityEntry(userAuthority);
          authorities.add(new Authority().withId(2L).withName(AuthorityName.ROLE_USER));
        }
      }

      user.setAuthorities(authorities);
      ((JwtUserDetailsServiceImpl) userDetailsService).createUser(user);
      return ResponseEntity.ok("New user created");
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }
}