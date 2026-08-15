package com.example.PowerToolStore.service;

import com.example.PowerToolStore.dto.request.UserCreateRequest;
import com.example.PowerToolStore.dto.request.UserDetailsUpdateRequest;
import com.example.PowerToolStore.dto.request.UserRoleUpdateRequest;
import com.example.PowerToolStore.dto.response.UserResponse;
import com.example.PowerToolStore.entity.User;
import com.example.PowerToolStore.exception.UserNotFoundException;
import com.example.PowerToolStore.mapper.UserMapper;
import com.example.PowerToolStore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TO DO: Create a method for password change and something for forgot password feature and otp-verification

@Service
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, UserMapper userMapper)
    {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request){
        // hash password
        String hashedPassword = this.encoder.encode(request.getPassword());
        // create entity
        User user = this.userMapper.createEntity(request, hashedPassword);
        // save entity in db
        userRepository.save(user);

        return userMapper.toResponse(user);
    };

    @Transactional
    public UserResponse updateUserDetails(UserDetailsUpdateRequest request){
        // get user from db
        Optional<User> user = userRepository.findById(request.getUserId());
        if(user.isPresent())
        {
            userMapper.updateEntity(user.get(), request);
            // save updated user in db
            userRepository.save(user.get());
            return userMapper.toResponse(user.get());
        }
        else {
            throw new UserNotFoundException(request.getUserId());
        }
    };

    @Transactional
    public UserResponse updateRole(UserRoleUpdateRequest request){
        // get user from db
        Optional<User> user = userRepository.findById(request.getUserId());
        if(user.isPresent())
        {
            userMapper.updateEntity(user.get(), request);
            // save updated user in db
            userRepository.save(user.get());
            return userMapper.toResponse(user.get());
        }
        else{
            throw new UserNotFoundException(request.getUserId());
        }
    };

    @Transactional
    public UserResponse findById(Long id)
    {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
        {
            return userMapper.toResponse(user.get());
        }
        else {
            throw new UserNotFoundException(id);
        }
    }

    @Transactional
    public List<UserResponse> getAll()
    {
        List<UserResponse> users = new ArrayList<>();
        for(User user: userRepository.findAll())
        {
            users.add(userMapper.toResponse(user));
        }
        return users;
    }

}
