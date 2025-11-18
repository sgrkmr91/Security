package com.example.security.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    private String userName;
    private String userPassword;
    private String role;

    public com.example.security.entity.User toEntity(User user) {
        com.example.security.entity.User userEntity = new com.example.security.entity.User();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getUserPassword());
        userEntity.setRole(user.getRole());
        return userEntity;

    }
}
