package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.entity.Member;
import com.itschool.springbootdeveloper.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcsetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given : 테스트 실행 준비
        System.out.println("시작");
        final String url = "/api/member";
        Member savedMember = memberRepository.save(new Member("홍길동"));

        // when : 테스트 진행
        // perform() 메서드는 요청을 전송하는 역할(postman의 request요청)
        // perform() 메서드의 반환 타입은 ResultActions 객체
        System.out.println("when 시작");
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON));
        System.out.println("when 끝");
        // accept() 메서드는 요청을 보낼 때 무슨 타입으로 응답을 받을지 결정하는 메서드. XML이 표준이긴 하지만 HSION으로 줄 것을 요청


        // then : 테스트 결과 검증
        // JsonPath("$[0].${필드명}")은 JSON 응답값을 가져오는 메서드 : 0번째 배열에 들어있는 객체의 필드를 가져옴
        System.out.println("then 시작");
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(savedMember.getName()));
        System.out.println("then 끝");
    }
}