package com.example.shop.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor
public class Member {

    // 등록 순서대로 부여되는 id, 기본키
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;


    // 로그인 아이디 login id
    @Column(name = "member_login_id", length = 20)
    private String loginId;


    // 비밀번호 password
    @Column(name = "member_pw", length = 100)
    private String password;


    // 전화번호 phoneNumber
    @Column(name = "member_phone", length = 20)
    private String phoneNumber;


    // 주소 address
    @Column(name = "member_address", length = 255)
    private String address;


    // 포인트 적립 현황 point
    @Column(name = "member_point")
    private int point;

    public Member(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0;
    }

    // 회원 정보 수정 메서드(loginId는 변경 불가)
    public void updateInfo(String password, String phoneNumber, String address) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

}