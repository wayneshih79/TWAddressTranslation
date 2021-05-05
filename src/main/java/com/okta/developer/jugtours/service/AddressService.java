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
        /** we combine village, road and alley **/
        address.setTranVillage(addressMap.get(
                StringUtils.defaultString(address.getVillage()) +
                StringUtils.defaultString(address.getRoad()) +
                StringUtils.defaultString(address.getSection()) +
                StringUtils.defaultString(address.getLane()) +
                StringUtils.defaultString(address.getAlley())
                )
        );
        // translate
        if (address.getNo() != null) {
            address.setTranNo("No. " + address.getNo().replace("號", ""));
        }
        if (address.getFloor() != null) {
            address.setTranFloor(address.getFloor().replace("樓", " F."));
        }
        String translateAddress =
                AddressProcessUtil.appendIfNotEmpty(address.getOthers(), ", ") +
                AddressProcessUtil.appendIfNotEmpty(address.getTranFloor(), ", ") +
                AddressProcessUtil.appendIfNotEmpty(address.getTranNo(), ", ") +
                //AddressProcessUtil.appendIfNotEmpty(address.getTranAlley()) + combine with road according to csv
                //AddressProcessUtil.appendIfNotEmpty(address.getTranRoad(), ", ") +
                AddressProcessUtil.appendIfNotEmpty(address.getTranVillage(), ", ") +
                AddressProcessUtil.appendIfNotEmpty(address.getTranCity(), " ") +
                AddressProcessUtil.appendIfNotEmpty(address.getZipcode(), "") +
                        ", " + address.getCountry();

        if (translateAddress == null) {
            throw new RuntimeException("can't find address");
        }
        return new TranAddressResponseDto(translateAddress);
    }
}
