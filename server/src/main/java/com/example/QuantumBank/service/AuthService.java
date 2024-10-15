package com.example.QuantumBank.service;

import com.example.QuantumBank.entities.Auth;
import com.example.QuantumBank.exception.AccessDeniedException;
import com.example.QuantumBank.exception.InvalidInputException;
import com.example.QuantumBank.repository.AuthRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: Change the way that user and auth services save passwords because the passwords are being
// encrypted differently

@Service
public class AuthService {
    private AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Auth saveAuth(Auth auth) throws InvalidInputException {

        validateInput(auth);

        auth.setPassword(this.hash(auth.getPassword()));

        return authRepository.save(auth);
    }

    public Auth validateLogin(Auth auth) throws AccessDeniedException, InvalidInputException {

        validateInput(auth);

        Optional<Auth> checkAuth = authRepository.findByUsername(auth.getUsername());

        if(checkAuth.isPresent()) {

            Auth checkedAuth = checkAuth.get();

            if(checkHash(auth.getPassword(), checkedAuth.getPassword())) {
                return auth;
            } else {
                throw new AccessDeniedException("Username or Password is incorrect!");
            }
        }

        return auth;
    }

    public String hash(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt(12));
    }

    public boolean checkHash(String text, String hash) {
        return BCrypt.checkpw(text, hash);
    }

    public void validateInput(Auth auth) throws InvalidInputException {
        if(auth.getUsername().isEmpty() || auth.getUsername().length() > 255) {
            throw new InvalidInputException("Invalid username!");
        } else if(auth.getPassword().isEmpty() || auth.getPassword().length() > 255) {
            throw new InvalidInputException("Invalid password!");
        }
    }
}
