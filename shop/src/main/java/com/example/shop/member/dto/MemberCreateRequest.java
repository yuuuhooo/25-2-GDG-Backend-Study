package com.example.shop.member.dto;

import lombok.Getter;

// loginId, password, phoneNumber, address
@Getter //Lombok에서 제공, getLoginId부터 getAddress까지 자동 생성됨
public class MemberCreateRequest {
    private String loginId;
    private String password;
    private String phoneNumber;
    private String address;

    public MemberCreateRequest(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
