package com.example.QuantumBank.controller;

import com.example.QuantumBank.entities.Auth;
import com.example.QuantumBank.entities.User;
import com.example.QuantumBank.exception.AccessDeniedException;
import com.example.QuantumBank.exception.DuplicateUserException;
import com.example.QuantumBank.exception.InvalidInputException;
import com.example.QuantumBank.service.AuthService;
import com.example.QuantumBank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {
    private AuthService authService;
    private UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register/auth")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws DuplicateUserException, InvalidInputException {
        System.out.println(user.toString());

        User newUser = userService.saveUser(user);

        Auth newAuth = new Auth(newUser.getUsername(), newUser.getPassword());

        authService.saveAuth(newAuth);

        if(newUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/login/auth")
    public ResponseEntity<Auth> loginUser(@RequestBody Auth auth) throws AccessDeniedException, InvalidInputException {
        Auth checkAuth = authService.validateLogin(auth);

        if(checkAuth == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(checkAuth, HttpStatus.OK);
        }
    }

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionHandler(DuplicateUserException e) {
        return e.getMessage();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionHandler(AccessDeniedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionHandler(InvalidInputException e) {
        return e.getMessage();
    }
}
