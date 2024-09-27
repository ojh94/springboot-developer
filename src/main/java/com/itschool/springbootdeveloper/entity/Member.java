package com.itschool.springbootdeveloper.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // (지연 로딩) 프록시 조회에서 접근을 위해 protected
//@AllArgsConstructor
@Getter
// 엔티티는 데이터베이스의 테이블과 매핑되는 객체를 의미함
// 엔티티는 본질적으로는 자바 객체이므로 일반 객체와 다르지 않음
// 데이터베이스의 테이블과 직접 연결된다는 특별한 특징이 있음
// 엔티티 매니저 : 엔티티 매니저는 엔티티를 관리해 데이터베이스와 어플리케이션 사이에서 객체를 생성, 수정, 삭제하는 등의 역할을 함
// 엔티티 매니저는 Sprinf Data JPA에서 관리하므로 직접 생성하서나 관리할 필요가 없음
// 엔티티 매니저 팩토리 : 엔티티 매니저를 생성, 어플리케이션 내에서 단 하나만 존재
@Entity(name = "user")
public class Member {
    @Id // id 필드를 기본키로 지정, (Long 타입의 id필드를 기본키로 우리가 지정)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 데이터베이스에 위임(=AUTO_INCREMENT)
    @Column(name = "id", updatable = false) // DB 실제 컬럼명, name을 넣지 않으면 변수명을 lower_snake_case로 간주
    // AUTO : 선택한 데이터베이스 방언에 따라 방식을 자동으로 선택(기본값)
    private Long id; // DB 테이블의 'id' 컬럼과 매칭

    @Column(nullable = false) // name이라는 not column 과 매핑
    // name : 필드와 매핑할 컬럼이름, 설정하지 않으면 필드 이름으로 지정해줌
    // nullable : 컬럼의 null 허용 여부, 설정하지 않으면 ture(nullable), false(NOY NULL)
    // uniqu : 컬럼의 유일한 값(uniq
    private String name; // DB테이블의 'name' 컬럼과 매칭

    public Member(String name) {
        this.name = name;
    }
}
