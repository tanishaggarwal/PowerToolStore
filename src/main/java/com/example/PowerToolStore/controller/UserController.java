package com.example.PowerToolStore.controller;

import com.example.PowerToolStore.constant.MdcConstant;
import com.example.PowerToolStore.dto.request.user.UserCreateRequest;
import com.example.PowerToolStore.dto.request.user.UserDetailsUpdateRequest;
import com.example.PowerToolStore.dto.request.user.UserRoleUpdateRequest;
import com.example.PowerToolStore.dto.response.ApiResponse;
import com.example.PowerToolStore.dto.response.UserResponse;
import com.example.PowerToolStore.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserCreateRequest request){

        return new ResponseEntity<>(
                ApiResponse.<UserResponse>builder()
                        .data(this.userService.createUser(request))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build()
        , HttpStatus.CREATED);
    };

    @PutMapping("/update-details")
    public ResponseEntity<ApiResponse<UserResponse>> updateUserDetails(@Valid @RequestBody UserDetailsUpdateRequest request){

        return new ResponseEntity<>(
                ApiResponse.<UserResponse>builder()
                        .data(this.userService.updateUserDetails(request))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK
        );
    };

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-role")
    public ResponseEntity<ApiResponse<UserResponse>> updateRole(@Valid @RequestBody UserRoleUpdateRequest request){

        return new ResponseEntity<>(
                ApiResponse.<UserResponse>builder()
                        .data(this.userService.updateRole(request))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK
        );
    };

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> findById(@PathVariable Long id)
    {
        return new ResponseEntity<>(
                ApiResponse.<UserResponse>builder()
                        .data(userService.findById(id))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers()
    {
        return new ResponseEntity<>(
                ApiResponse.<List<UserResponse>>builder()
                        .data(userService.getAll())
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK);
    }
}
