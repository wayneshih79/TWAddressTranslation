package com.okta.developer.jugtours.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String country;
    private String address;
    private String tranAddress;
    /** 郵遞區號 **/
    private String zipcode;

    /** 縣市 **/
    private String city;
    private String tranCity;

    /** 鄉鎮市區 **/
    private String region;
    private String tranRegion;

    /** 村里巷 **/
    private String village;
    private String tranVillage;

    /** 路 **/
    private String road;
    private String tranRoad;

    /** 段 **/
    private String section;
    private String tranSection;

    /** 巷 **/
    private String lane;
    private String tranLane;

    /** 弄 **/
    private String alley;
    private String tranAlley;

    /** 號 **/
    private String no;
    private String tranNo;

    /** 樓 **/
    private String floor;
    private String tranFloor;

    /** 其他 **/
    private String others;
    private String tranOthers;

}
