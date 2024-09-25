package com.itschool.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        // SpringApplication.run() 메서드는 애플리케이션을 실행
        // 첫 번쨰 인수는
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
