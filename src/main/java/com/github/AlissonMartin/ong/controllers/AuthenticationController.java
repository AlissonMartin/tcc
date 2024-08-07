package com.github.AlissonMartin.ong.controllers;

import com.github.AlissonMartin.ong.dtos.LoginRequestDTO;
import com.github.AlissonMartin.ong.dtos.RegisterRequestDTO;
import com.github.AlissonMartin.ong.dtos.RegisterResponseDTO;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import com.github.AlissonMartin.ong.services.TokenService;
import com.github.AlissonMartin.ong.services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

  @Autowired
  TokenService tokenService;

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  @PostMapping("/login")
  ResponseEntity<String> login(@RequestBody LoginRequestDTO body) {
    Optional<User> user = userRepository.findByEmail(body.email());

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }

    String token = tokenService.generateToken(user.get());

  return ResponseEntity.ok(token);
  }

  @PostMapping("/register")
  ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO body) throws MessagingException, IOException {
    User user = userService.create(body);

    String token = tokenService.generateToken(user);

    return ResponseEntity.ok(new RegisterResponseDTO(user.getName(), user.getEmail(), user.getFederalTaxId(), user.getRole().toString(), token));
  }
}
