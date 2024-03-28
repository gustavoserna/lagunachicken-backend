package com.digitaul.lagunachicken.controller;

import com.digitaul.lagunachicken.domain.dto.ERoleDTO;
import com.digitaul.lagunachicken.domain.dto.RoleDTO;
import com.digitaul.lagunachicken.domain.dto.UserDTO;
import com.digitaul.lagunachicken.payload.request.LoginRequest;
import com.digitaul.lagunachicken.payload.request.SignupRequest;
import com.digitaul.lagunachicken.payload.response.MessageResponse;
import com.digitaul.lagunachicken.payload.response.UserInfoResponse;
import com.digitaul.lagunachicken.security.jwt.JwtUtils;
import com.digitaul.lagunachicken.security.services.RoleServiceImpl;
import com.digitaul.lagunachicken.security.services.UserDetailsImpl;
import com.digitaul.lagunachicken.security.services.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(
        origins = {"*" /*"http://demo.vidsaconstructora.com.mx"*/},
        maxAge = 3600,
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userDetailsService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userDetailsService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserDTO userDTO = new UserDTO(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );

        List<String> strRoles = signUpRequest.getRole();
        List<RoleDTO> roles = new ArrayList<>();

        if (strRoles == null) {
            throw new RuntimeException("Error: Role is missing.");
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        RoleDTO adminRole = roleService.findByName(ERoleDTO.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    default:
                        throw new RuntimeException("Error: Role is not found.");
                }
            });
        }

        userDTO.setRoles(roles);
        userDetailsService.saveUser(userDTO);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

}
