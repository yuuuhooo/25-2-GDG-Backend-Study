package com.example.shop.member.dto;

// password, phoneNumber, address

import com.example.shop.common.message.ErrorMessage;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    @Size(min = 8, max = 20, message= ErrorMessage.PASSWORD_SIZE)
    private String password;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = ErrorMessage.PHONENUMBER_FORM)
    private String phoneNumber;

    @Size(min=1, max=255, message = ErrorMessage.ADDRESS_SIZE)
    private String address;

    public MemberUpdateRequest(String password, String phoneNumber, String address) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
