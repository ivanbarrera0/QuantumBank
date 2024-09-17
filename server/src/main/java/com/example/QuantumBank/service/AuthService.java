package com.example.QuantumBank.service;

import com.example.QuantumBank.entities.Auth;
import com.example.QuantumBank.exception.AccessDeniedException;
import com.example.QuantumBank.exception.InvalidInputException;
import com.example.QuantumBank.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Auth saveAuth(Auth auth) throws InvalidInputException {

        if(auth.getUsername().isEmpty() || auth.getUsername().length() > 255) {
            throw new InvalidInputException("Invalid username!");
        } else if(auth.getPassword().isEmpty() || auth.getPassword().length() > 255) {
            throw new InvalidInputException("Invalid password!");
        }

        return authRepository.save(auth);
    }

    public Auth validateLogin(Auth auth) throws AccessDeniedException, InvalidInputException {

        if(auth.getUsername().isEmpty() || auth.getUsername().length() > 255) {
            throw new InvalidInputException("Invalid username!");
        } else if(auth.getPassword().isEmpty() || auth.getPassword().length() > 255) {
            throw new InvalidInputException("Invalid password!");
        }

        Auth checkAuth = authRepository.findByUsername(auth.getUsername());

        if(auth.getPassword().equals(checkAuth.getPassword())) {
            return auth;
        } else {
            throw new AccessDeniedException("Username or Password is incorrect!");
        }
    }
}
