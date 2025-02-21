package me.chohyeonjae.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //springbootapplication이 있는 클래스를 찾고 그 클래스에 있는 빈을 찾은 다음 테스트용 애플리케이션 컨텍스트라는 것을 만듦
@AutoConfigureMockMvc //MockMvc를 생성하고 자동으로 구성하는 애너테이션, 웹 서버를 띄우지 않고 컨트롤러를 직접 호출하여 테스트할 수 있음
class TestControllerTest {
    @Autowired
    protected MockMvc mockMvc; //Spring MVC의 MockMvc 객체를 자동 주입
    //MockMvc는 실제 서버를 실행하지 않고, 컨트롤러 API를 테스트할 수 있도록 도와준다.
    //test클래스에서만 사용하도록 protected

    @Autowired
    private WebApplicationContext context; //Spring의 웹 애플리케이션 컨텍스트를 주입
    //이거 없으면 Spring의 @Service, @Repository, @Component 빈을 사용할 수 없음

    @Autowired
    private MemberRepository memberRepository;
    @BeforeEach //mockMvcSetUp를 사용해 MockMvc설정
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        //MockMvcBuilders를 사용하면 MockMvc를 더 정교하게 설정하고 초기화할 수 있음
        //webAppContextSetup()	전체 Spring 웹 컨텍스트 기반으로 테스트 (실제 환경과 유사)
    }
    @AfterEach  //cleanup을 사용해 member테이블에 있는 데이터 모두 삭제
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when
        // "/test" API를 GET 요청으로 호출하고, JSON 응답을 요청
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk()) // 응답상태확인
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}