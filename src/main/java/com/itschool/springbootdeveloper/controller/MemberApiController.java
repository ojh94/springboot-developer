package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.entity.Member;
import com.itschool.springbootdeveloper.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Presentation 계층 : 웹 클라이언트의 요청 및 응답을 처리
// RESTController(REST API, json) 또는 Controller는 라우터 역할을 함
// 라우터는 HTTP 요청과 메서드를 연결하는 장치
// @RestController 애너테이션은 @Component 를 포함
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    // Presentation 계층 <-> Service 계층
    @Autowired
    MemberService testService;
    @Autowired
    private MemberService memberService;

    // TestController를 라우터로 지정해 /test라는 GET 요청이 오면 test() 메서드를 실행
    @GetMapping("")
    public List<Member> getAllMembers() {
        // Presentation 계층은 Service 계층과 관련있음
        List<Member> members = testService.getAllMember();
        return members;
    }

    @GetMapping("{id}")
    public Member findMemberById(@PathVariable Long id) {
        return testService.getMemberById(id);
    }

    @GetMapping("searchByName/{name}")
    public List<Member> searchMemberBName(@PathVariable String  name) {
        return testService.searchMemberByName(name);
    }

    @GetMapping("{id}/{name}")
    public Member getMemberByIdAndName(@PathVariable Long id, @PathVariable String name) {
        return testService.getMemberByIdAndName(id, name);
    }

    @GetMapping("test")
    public String test() {
        memberService.test();
        return "test-api";
    }


}
