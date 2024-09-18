package com.example.QuantumBank.service;

import com.example.QuantumBank.entities.Auth;
import com.example.QuantumBank.exception.AccessDeniedException;
import com.example.QuantumBank.exception.InvalidInputException;
import com.example.QuantumBank.repository.AuthRepository;
import org.mindrot.jbcrypt.BCrypt;
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

        auth.setPassword(hash(auth.getPassword()));

        return authRepository.save(auth);
    }

    public Auth validateLogin(Auth auth) throws AccessDeniedException, InvalidInputException {

        if(auth.getUsername().isEmpty() || auth.getUsername().length() > 255) {
            throw new InvalidInputException("Invalid username!");
        } else if(auth.getPassword().isEmpty() || auth.getPassword().length() > 255) {
            throw new InvalidInputException("Invalid password!");
        }

        Auth checkAuth = authRepository.findByUsername(auth.getUsername());

        if(checkHash(auth.getPassword(), checkAuth.getPassword())) {
            return auth;
        } else {
            throw new AccessDeniedException("Username or Password is incorrect!");
        }
    }

    public String hash(String text) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(text, salt);
    }

    public boolean checkHash(String text, String hash) {
        return BCrypt.checkpw(text, hash);
    }
}
