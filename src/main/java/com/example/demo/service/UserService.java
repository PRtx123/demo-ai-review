package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.UserUniqueEmailViolationException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserUniqueEmailViolationException(user.getEmail());
        }
        return userRepository.save(user);
    }

    public User update(Long id, User update) {
        User existing = getById(id);
        if (!existing.getEmail().equalsIgnoreCase(update.getEmail())
                && userRepository.existsByEmail(update.getEmail())) {
            throw new UserUniqueEmailViolationException(update.getEmail());
        }
        existing.setName(update.getName());
        existing.setEmail(update.getEmail());
        return userRepository.save(update);
    }

    public void delete(Long id) {
        User existing = getById(id);
        userRepository.delete(existing);
    }
}



