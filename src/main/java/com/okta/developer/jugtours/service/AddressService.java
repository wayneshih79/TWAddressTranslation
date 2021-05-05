package com.okta.developer.jugtours.service;

import com.okta.developer.jugtours.model.Address;
import com.okta.developer.jugtours.model.dto.response.TranAddressResponseDto;
import com.okta.developer.jugtours.util.AddressProcessUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AddressService {

    @Autowired
    Map<String, String> addressMap;

    public TranAddressResponseDto translateAddress(String origAddress) {
        String replaceAddress = AddressProcessUtil.replaceAddressChar(origAddress);
        Address address = AddressProcessUtil.parseAddress(replaceAddress);
        /** TO DO seperate city and region **/
        address.setTranCity(addressMap.get(address.getCity() + address.getRegion()));
        address.setTranVillage(addressMap.get(address.getVillage()));
        address.setTranRoad(addressMap.get(address.getRoad()));
        // translate
        if (address.getAlley() != null) {
            address.setTranAlley(address.getAlley().replace("弄", " Alley"));
        }
        if (address.getNo() != null) {
            address.setTranNo(address.getNo().replace("號", " No"));
        }
        if (address.getFloor() != null) {
            address.setFloor(address.getFloor().replace("樓", " Floor"));
        }
        String translateAddress = StringUtils.defaultString(address.getZipcode()) + " " +
                StringUtils.defaultString(address.getOthers()) + " " +
                StringUtils.defaultString(address.getTranFloor()) + " " +
                StringUtils.defaultString(address.getTranNo()) + " " +
                StringUtils.defaultString(address.getTranAlley()) + " " +
                StringUtils.defaultString(address.getTranRoad()) + " " +
                StringUtils.defaultString(address.getTranCity());

        if (translateAddress == null) {
            throw new RuntimeException("can't find address");
        }
        return new TranAddressResponseDto(translateAddress);
    }
}
