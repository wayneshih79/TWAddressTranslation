package com.okta.developer.jugtours.util;

import com.okta.developer.jugtours.model.Address;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class AddressProcessUtil {

    Map<String, String> chineseNumToNumMap  = new HashMap<String, String>() {{
        put("一段", "１段");
        put("1段", "１段");
        put("二段", "２段");
        put("2段", "２段");
        put("三段", "３段");
        put("3段", "３段");
        put("四段", "４段");
        put("4段", "４段");
        put("五段", "５段");
        put("5段", "５段");
        put("六段", "６段");
        put("6段", "６段");
        put("七段", "７段");
        put("7段", "７段");
        put("八段", "８段");
        put("8段", "８段");
        put("九段", "９段");
        put("9段", "９段");
    }};
    public static Address parseAddress(String address) {
        String pattern = "(?<zipcode>(^\\d{5}|^\\d{3})?)" +
                "(?<city>\\D+?[縣市])?" +
                "(?<region>\\D+?(市區|鎮區|鎮市|[鄉鎮市區]))?" +
                "(?<village>\\D+?[村里])?" +
                //"(?<neighbor>\\d+[鄰])?" +
                "(?<road>\\S+?([路街道段]|村路))?" +
                "(?<section>\\S?段)?" +
                "(?<lane>\\D+?巷)?" +
                "(?<alley>\\S+弄)?" +
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
            System.out.println("Found section value: " + m.group("section") );
            System.out.println("Found lane value: " + m.group("lane") );
            System.out.println("Found alley value: " + m.group("alley") );
            System.out.println("Found no value: " + m.group("no") );
//            System.out.println("Found seq value: " + m.group("seq") );
            System.out.println("Found floor value: " + m.group("floor") );
            System.out.println("Found others value: " + m.group("others") );
            System.out.println("================================================");

        }else {
            System.out.println("NO MATCH");
        }

        Address res = Address.builder()
                .address(address)
                .zipcode(m.group("zipcode"))
                .city(m.group("city"))
                .region(m.group("region"))
                .village(m.group("village"))
                .zipcode(m.group("zipcode"))
                .road(m.group("road"))
                .section(m.group("section"))
                .lane(m.group("lane"))
                .alley(m.group("alley"))
                .no(m.group("no"))
                .zipcode(m.group("zipcode"))
                .floor(m.group("floor"))
                .others(m.group("others"))
                .country("Taiwan (R.O.C)")
                .build();
        changeSectionToFullNumFormat(res);
        changeNumToChineseCharactor(res);
        /** replace 之 to - */
        changeChineseCharactorInOthers(res);
        return res;
    }

    public static String replaceAddressChar(String address) {
        address = address.replace("台", "臺");
        address = address.replace("台灣", "");
        address = address.replace("臺灣", "");
        address = address.replace("　", "");
        address = address.replaceAll("[~!@#$%^&*(),     ]", "");
        return address;
    }

    public static String appendIfNotEmpty(String str, String appendStr) {
        return StringUtils.isEmpty(str) ? "" : str + appendStr;
    }

    /** support 五段 -> ５段 **/
    public static void changeSectionToFullNumFormat(Address address) {
        address.setSection(chineseNumToNumMap.get(address.getSection()));
    }

    /** support 87巷 and 1 弄 for village and alley **/
    public static void changeNumToChineseCharactor(Address address) {
        address.setVillage(replaceNumToChineseCharactor(address.getVillage()));
        address.setAlley(replaceNumToChineseCharactor(address.getAlley()));
    }

    public static String replaceNumToChineseCharactor(String str) {
        if (str != null) {
            str = str.replace("1", "一");
            str = str.replace("2", "二");
            str = str.replace("3", "三");
            str = str.replace("4", "四");
            str = str.replace("5", "五");
            str = str.replace("6", "六");
            str = str.replace("7", "七");
            str = str.replace("8", "八");
            str = str.replace("9", "九");
        }
        return str;
    }

    /** replace 之 xxx 室 */
    public static void changeChineseCharactorInOthers(Address address) {
        if (address.getOthers() != null) {
            address.setOthers(address.getOthers().replace("之", "Rm. "));
            address.setOthers(address.getOthers().replace("室", ""));
        }
    }
}
