package com.example.QuantumBank.controller;

import com.example.QuantumBank.entities.Account;
import com.example.QuantumBank.entities.Auth;
import com.example.QuantumBank.entities.User;
import com.example.QuantumBank.exception.AccessDeniedException;
import com.example.QuantumBank.exception.DuplicateUserException;
import com.example.QuantumBank.exception.InvalidInputException;
import com.example.QuantumBank.exception.NotFoundException;
import com.example.QuantumBank.service.AccountService;
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
    private AccountService accountService;

    @Autowired
    public AuthController(AuthService authService, UserService userService, AccountService accountService) {
        this.authService = authService;
        this.userService = userService;
        this.accountService = accountService;
    }

    @PostMapping("/register/auth")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws DuplicateUserException, InvalidInputException {

        Auth auth = new Auth(user.getUsername(), user.getPassword());

        authService.saveAuth(auth);

        user.setPassword(auth.getPassword());

        userService.saveUser(user);

        Account account = new Account(0, user);

        accountService.saveAccount(account);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // TODO: Use JSONManagedReference and JSONBackReference to the objects to prevent a circular dependency

    @PostMapping("/login/auth")
    public ResponseEntity<User> loginUser(@RequestBody Auth auth) throws AccessDeniedException, InvalidInputException, NotFoundException {
        Auth checkAuth = authService.validateLogin(auth);

        User returnUser = userService.findUserByUsername(checkAuth.getUsername());

        if(returnUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(returnUser, HttpStatus.OK);
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

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionHandler(NotFoundException e) {return e.getMessage();}
}
