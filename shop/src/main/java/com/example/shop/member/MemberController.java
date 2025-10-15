package com.example.shop.member;

import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*
@Controller
@ResponseBody // 메소드의 반환값을 body의 내용으로 직접 사용하게 지정. 안 쓰면 View의 이름으로 해석하여 뷰를 찾으려 함
*/

@RestController // 위의 두 어노테이션이 포함되어 있음
@RequiredArgsConstructor // 생성자를 만들어주는 어노테이션.
// *final**로 선언된 모든 필드와 @NonNull 어노테이션이 붙은 모든 필드를 매개변수로 받는 생성자를 코드를 직접 작성하지 않아도 자동으로 만들어 줌
@RequestMapping("/members") // 공통 엔드포인트 여기에 한 번만 작성
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) {
        Long memberId = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/members" + memberId)).build();
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members); // ok() 안에 넣어주면 그대로 리스폰스 바디에 들어감
    }

    @GetMapping("/{memberId}") // PathVa
    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberUpdateRequest request) {
        memberService.updateMember(memberId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }



}

