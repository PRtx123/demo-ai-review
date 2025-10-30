package com.example.demo.dto;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequest req) {
        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getName());
        return u;
    }

    public UserResponse toResponse(User u) {
        UserResponse res = new UserResponse();
        res.setId(u.getId());
        res.setName(u.getName());
        res.setEmail(u.getEmail());
        res.setCreatedAt(u.getCreatedAt());
        return res;
    }
}



