package com.okta.developer.jugtours.controller;

import com.okta.developer.jugtours.model.dto.request.TranAddressRequestDto;
import com.okta.developer.jugtours.model.dto.response.TranAddressResponseDto;
import com.okta.developer.jugtours.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class AddressController {

    private final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @PostMapping("/translate/address")
    TranAddressResponseDto translateAddress(@RequestBody TranAddressRequestDto tranAddressRequestDto) {
        return addressService.translateAddress(tranAddressRequestDto.getAddress());
    }
}