package com.example.shop.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA만 사용 가능, 외부 사용 차단
public class Member {

    // 회원 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //키값 결정을 DB에게 위임. 1234 순으로 자동 증가
    @Column(name = "member_id")
    private Long id;

    // 회원 아이디
    @Column(name = "member_login_id")
    private String loginId;

    // 비밀번호
    @Column(name = "member_pw")
    private String password;

    // 전화번호
    @Column(name = "member_phone")
    private String phoneNumber;

    // 주소
    @Column(name = "member_address")
    private String address;

    // 적립금
    @Column(name = "member_point")
    private int point;

    /**
     * 회원 생성자 (id와 point는 자동 생성/초기화)
     */
    public Member(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0; // 신규 회원 적립금은 0으로 초기화
    }

    /**
     * 회원 정보 수정 메서드
     * - 비밀번호, 전화번호, 주소만 수정 가능
     * - loginId는 변경 불가
     */
    public void updateInfo(String password, String phoneNumber, String address) {
        if (password != null) {
            this.password = password;
        }
        if (phoneNumber != null) {
            this.phoneNumber = phoneNumber;
        }
        if (address != null) {
            this.address = address;
        }
    }
}

