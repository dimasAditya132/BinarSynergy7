package com.example.BinFood.controller;

import com.example.BinFood.payload.LoginRequestDTO;
import com.example.BinFood.payload.Response;
import com.example.BinFood.security.jwt.JwtResponse;
import com.example.BinFood.security.jwt.JwtUtils;
import com.example.BinFood.security.service.UserDetailsImpl;
import com.example.BinFood.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    MailService mailService;

    @PostMapping("/signin")
    public ResponseEntity<Response> authenticate(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), roles);
        return new ResponseEntity<>(new Response.Success(jwtResponse), HttpStatus.CREATED);
    }

    @GetMapping("/oauth2/success")
    public ResponseEntity<Response> googleLoginSuccess(Authentication authentication) {
        // Create a new Principal object with modified authorities
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = new ArrayList<>(oidcUser.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        OidcUser modifiedOidcUser = new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

        // Create a new Authentication object with the modified Principal
        Authentication modifiedAuthentication = new UsernamePasswordAuthenticationToken(
                modifiedOidcUser,
                oidcUser.getIdToken(),
                authorities
        );

        // Generate token using the modified authentication
        String jwt = jwtUtils.generateToken(modifiedAuthentication);

        // Extract user details from the modified authentication
        List<String> roles = modifiedAuthentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // Prepare response
        JwtResponse jwtResponse = new JwtResponse(jwt, oidcUser.getEmail(), roles);

        return new ResponseEntity<>(new Response.Success(jwtResponse), HttpStatus.CREATED);
    }

    @GetMapping("/test/mail")
    public String sendMail() {
        mailService.sendMail("dimasaaditya456@gmail.com", "subject test mail", "Hello World");
        return "Mail sent";
    }
}
