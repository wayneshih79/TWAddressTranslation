package com.okta.developer.jugtours;

import com.okta.developer.jugtours.model.dto.response.TranAddressResponseDto;
import com.okta.developer.jugtours.service.AddressService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {
    @Autowired
    AddressService addressService;

    @Test
    public void testParserUtil() {

        TranAddressResponseDto tranAddressResponseDto = addressService.translateAddress("台北市信義區市府路華夏巷西一弄1號");
        Assert.assertEquals("xxx", tranAddressResponseDto.getAddress());
    }
}
