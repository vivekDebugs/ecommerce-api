package com.example.ecommerceapi.dtos;

import com.example.ecommerceapi.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        return user;
    }

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}