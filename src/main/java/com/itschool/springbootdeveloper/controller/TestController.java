package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.entity.Member;
import com.itschool.springbootdeveloper.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Presentation 계층 : 웹 크라이언트의 요청 및 응답을 처리
// RestController 또는 Controller는 라우터 역할을 함
// 라우터는 HTTP 요청과 메서드를 연결하는 장치
// @RestController 애너테이션은 @Component 을 포함
@RestController
public class TestController {

    // Service 계층 : 비즈니스 조릭 처리와 비즈니스 관련된 도메인 모델의 적합성 검증
    @Autowired
    TestService testService;


    @GetMapping("/test")
    public List<Member> getAllMembers(){
        // Presentation 계층은 Service 계층과 관련있음
        List<Member> members = testService.getAllMember();
        return members;
    }
}
