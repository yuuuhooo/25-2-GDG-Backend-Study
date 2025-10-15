package com.example.shop.member;

import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //@Transactional
    public Long createMember(MemberCreateRequest request) {
        Member existingMember = memberRepository.findByLoginId(request.getLoginId());
        if(existingMember != null) {
            throw new RuntimeException("이미 존재하는 로그인 아이디입니다: " + request.getLoginId()); // 사실 런타임은 지양하는 게 좋음
        }

        Member member = new Member(
          request.getLoginId(),
          request.getPassword(),
          request.getPhoneNumber(),
          request.getAddress()
        );

        memberRepository.save(member);

        return member.getId(); // id를 넘겨줘야 컨트롤러 createMember의 리턴에서 멤버 아이디가 포함된 엔드포인트를 제대로 넘겨줄 수 있음
    }

    //@Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    //@Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        Member member = memberRepository.findById(id);
        // 이때 잘못된 id가 들어올 수 있기 때문에 분기 처리가 필요

        if(member == null) {
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }
        return member;
    }

    public void updateMember(Long id, MemberUpdateRequest request) {
        Member member = memberRepository.findById(id);

        if(member == null) {
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        // 회원 정보 수정 (도메인 객체의 메서드 사용)
        member.updateInfo(request.getPassword(), request.getPhoneNumber(), request.getAddress());
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id);

        if(member == null) {
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        // repository를 통해 삭제
        memberRepository.deleteById(id);
    }
}
