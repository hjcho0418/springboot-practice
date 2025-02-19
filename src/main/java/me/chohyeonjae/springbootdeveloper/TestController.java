package me.chohyeonjae.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    TestService testService; //객체 주입
    @GetMapping("/test") // /test경로로 get요청이 들어올때
    public List<Member> getAllMembers() {
        List<Member> members = testService.getAllMembers();
        return members;
    }
}

//1. 사용자가 "/test" 주소로 요청을 보냄.  (웹 브라우저에서 localhost:8080/test 입력)
//2. TestController가 요청을 받음. (@GetMapping("/test"))
//3. TestController가 TestService에게 "회원 목록 줘!"라고 요청함.
//4. TestService가 데이터베이스 또는 저장소에서 회원 목록을 가져옴.
//5. TestController가 가져온 회원 목록을 사용자에게 JSON 형식으로 반환함.
