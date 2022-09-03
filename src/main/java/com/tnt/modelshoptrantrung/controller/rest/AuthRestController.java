package com.tnt.modelshoptrantrung.controller.rest;

import com.tnt.modelshoptrantrung.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tnt.modelshoptrantrung.exception.DataInputException;
import com.tnt.modelshoptrantrung.exception.AttributesExistsException;
import com.tnt.modelshoptrantrung.model.JwtResponse;
import com.tnt.modelshoptrantrung.model.Role;
import com.tnt.modelshoptrantrung.model.User;
import com.tnt.modelshoptrantrung.model.dto.UserDTO;
import com.tnt.modelshoptrantrung.service.account.user.UserService;
import com.tnt.modelshoptrantrung.service.role.RoleService;
import com.tnt.modelshoptrantrung.util.AppUtil;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AppUtil appUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        Optional<UserDTO> optUser = userService.findUserDTOByUsername(userDTO.getUsername());

        if (optUser.isPresent()) {
            throw new AttributesExistsException ("Username already exists");
        }

        try {
            User user = userDTO.toUser();
            Optional<Role> role = roleService.findById ( 2L );
            user.setRole ( role.get () );
            userService.save(user);

            return new ResponseEntity<>( HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Account information is not valid, please check the information again");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken (user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.getByUsername(user.getUsername()).get ();

        JwtResponse jwtResponse = new JwtResponse(
                jwt,
                currentUser.getId(),
                userDetails.getUsername(),
                currentUser.getUsername(),
                userDetails.getAuthorities()
        );

        ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(60 * 1000)
                .domain("localhost")
                .build();

        System.out.println(jwtResponse);

        return ResponseEntity
                .ok()
                .header( HttpHeaders.SET_COOKIE, springCookie.toString())
                .body(jwtResponse);

    }
}
