package com.example.QuantumBank.service;

import com.example.QuantumBank.entities.User;
import com.example.QuantumBank.exception.DuplicateUserException;
import com.example.QuantumBank.exception.InvalidInputException;
import com.example.QuantumBank.exception.NotFoundException;
import com.example.QuantumBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) throws DuplicateUserException, InvalidInputException {

        if(user.getUsername() == null || user.getUsername().isEmpty() || user.getUsername().length() > 255) {
            throw new InvalidInputException("Username is invalid!");
        } else if(user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().length() > 255) {
            throw new InvalidInputException("Email is invalid!");
        } else if(user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty() || user.getPhoneNumber().length() > 15) {
            throw new InvalidInputException(("Phone Number is invalid!"));
        }

        Optional<User> foundUser = userRepository.findUserByUsername(user.getUsername());

        if(foundUser.isPresent()) {
            throw new DuplicateUserException("Username already exists!");
        }

        return userRepository.save(user);
    }

    public User findUserByUsername(String username) throws NotFoundException {

        Optional<User> foundUser = userRepository.findUserByUsername(username);

        if(foundUser.isPresent()) {
            return foundUser.get();
        } else {
            throw new NotFoundException("User not found!");
        }
    }
}
