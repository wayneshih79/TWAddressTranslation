package com.okta.developer.jugtours.web;

import com.okta.developer.jugtours.model.dto.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class AddressController {

    private final Logger log = LoggerFactory.getLogger(AddressController.class);

    @PostMapping("/translate/address")
    Address translateAddress(@RequestBody Address address) {
        //reverse a string
        return new Address(new StringBuilder(address.getAddress()).reverse().toString());
    }
}