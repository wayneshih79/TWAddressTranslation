package com.okta.developer.jugtours.util;

import com.okta.developer.jugtours.model.Address;
import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class AddressProcessUtil {
    public static Address parseAddress(String address) {
        String pattern = "(?<zipcode>(^\\d{5}|^\\d{3})?)" +
                "(?<city>\\D+?[縣市])" +
                "(?<region>\\D+?(市區|鎮區|鎮市|[鄉鎮市區]))?" +
                "(?<village>\\D+?[村里巷])?" +
                //"(?<neighbor>\\d+[鄰])?" +
                "(?<road>\\D+?(村路|[路街道段]))?" +
                //"(?<section>\\D?段)?" +
                //"(?<lane>\\d+)?" +
                "(?<alley>\\d+弄)?" +
                "(?<no>\\d+號?)?" +
                //"(?<seq>-\\d+?(號))?" +
                "(?<floor>\\d+樓)?" +
                "(?<others>.+)?";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(address);
        if (m.find()) {
            //zipcode 為郵遞區號，只接受3碼及5碼
            //City 為縣市，Region為鄉鎮市區，Village為村里，Neighbor為鄰，Road為路，
            //Section為段，Lane為巷，Alley為弄，No為號，Seq為序號，Floor為樓層，other為其他。
            System.out.println("Found zipcode value: " + m.group("zipcode") );
            System.out.println("Found city value: " + m.group("city") );
            System.out.println("Found region value: " + m.group("region") );
            System.out.println("Found village value: " + m.group("village") );
//            System.out.println("Found neighbor value: " + m.group("neighbor") );
            System.out.println("Found road value: " + m.group("road") );
//            System.out.println("Found section value: " + m.group("section") );
//            System.out.println("Found lane value: " + m.group("lane") );
            System.out.println("Found alley value: " + m.group("alley") );
            System.out.println("Found no value: " + m.group("no") );
//            System.out.println("Found seq value: " + m.group("seq") );
            System.out.println("Found floor value: " + m.group("floor") );
            System.out.println("Found others value: " + m.group("others") );
            System.out.println("================================================");

        }else {
            System.out.println("NO MATCH");
        }
        return Address.builder()
                .address(address)
                .zipcode(m.group("zipcode"))
                .city(m.group("city"))
                .region(m.group("region"))
                .village(m.group("village"))
                .zipcode(m.group("zipcode"))
                .road(m.group("road"))
                .no(m.group("no"))
                .zipcode(m.group("zipcode"))
                .floor(m.group("floor"))
                .others(m.group("others"))
                .build();
    }

    public static String replaceAddressChar(String address) {
        address = address.replace("台", "臺");
        return address;
    }
}
