package com.example.PowerToolStore.controller;

import com.example.PowerToolStore.constant.MdcConstant;
import com.example.PowerToolStore.dto.request.AddressCreateRequest;
import com.example.PowerToolStore.dto.request.AddressUpdateRequest;
import com.example.PowerToolStore.dto.response.AddressResponse;
import com.example.PowerToolStore.dto.response.ApiResponse;
import com.example.PowerToolStore.service.AddressService;
import jakarta.validation.Valid;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService)
    {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AddressResponse>> createAddress(
            @Valid @RequestBody AddressCreateRequest request
            ){
        return new ResponseEntity<>(
                ApiResponse.<AddressResponse>builder()
                        .data(addressService.createAddress(request))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.CREATED
        );
    };

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<AddressResponse>> updateAddress(
            @Valid @RequestBody AddressUpdateRequest request
            ){
        return new ResponseEntity<>(
                ApiResponse.<AddressResponse>builder()
                        .data(addressService.updateAddress(request))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK
        );
    };

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<AddressResponse>> getAddress(@PathVariable Long userid){
        return new ResponseEntity<>(
                ApiResponse.<AddressResponse>builder()
                        .data(addressService.findByUserId(userid))
                        .requestId(MDC.get(MdcConstant.REQUEST_ID))
                        .build(),
                HttpStatus.OK);
    };

}
