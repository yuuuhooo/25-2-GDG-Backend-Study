package com.example.shop.member.service;

import com.example.shop.member.entity.Member;
import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;

import java.util.List;


public interface MemberService {

    // 회원 등록
    Long createMember(MemberCreateRequest request);

    // 전체 회원 목록 조회
    List<Member> getAllMembers();

    // 회원 ID로 단일 회원 조회
    Member getMemberById(Long id);

    // 회원 정보 수정
    void updateMember(Long id, MemberUpdateRequest request);

    // 회원 삭제
    void deleteMember(Long id);
}