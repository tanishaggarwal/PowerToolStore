package com.example.PowerToolStore.mapper;

import com.example.PowerToolStore.constant.UserRole;
import com.example.PowerToolStore.dto.request.UserCreateRequest;
import com.example.PowerToolStore.dto.request.UserDetailsUpdateRequest;
import com.example.PowerToolStore.dto.request.UserRoleUpdateRequest;
import com.example.PowerToolStore.dto.response.UserResponse;
import com.example.PowerToolStore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User createEntity(UserCreateRequest request, String hashedPassword)
    {
        return User.builder()
                .role(request.getRole().name())
                .email(request.getEmail())
                .contactNumber(request.getContactNumber())
                .hashedPassword(hashedPassword)
                .username(request.getUsername())
                .isActive(request.getIsActive())
                .build();
    }

    public void updateEntity(User user, UserDetailsUpdateRequest request)
    {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setContactNumber(request.getContactNumber());
    }

    public void updateEntity(User user, UserRoleUpdateRequest request)
    {
        user.setRole(request.getRole().name());
    }

    public UserResponse toResponse(User user)
    {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .contactNumber(user.getContactNumber())
                .email(user.getEmail())
                .role(getUserRole(user.getRole()))
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserRole getUserRole(String role)
    {
        if(role.compareTo(UserRole.USER.name())==0)
            return UserRole.USER;
        else if(role.compareTo(UserRole.ADMIN.name())==0)
            return UserRole.ADMIN;
        else
            return null;
    }
}
