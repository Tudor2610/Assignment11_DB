package com.jwtauth.assignment11_db.controller;

import com.jwtauth.assignment11_db.model.Request;
import com.jwtauth.assignment11_db.model.Response;
import com.jwtauth.assignment11_db.service.UserService;
import com.jwtauth.assignment11_db.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping({"/login"})
    public String firstPage() {
        return "Logged in successfully!";
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Request authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password!", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new Response(jwt));
    }
}
