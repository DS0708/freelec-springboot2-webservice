package com.konkuk.book.springboot.web;

import com.konkuk.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = { //스캔 대상에서 SecurityConfig를 제거
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; //이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있습니다.

    @WithMockUser(roles="USER")
    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))  //MockMvc를 통해 /hello 주소로 HTTP GET 요청을 합니다
                .andExpect(status().isOk())    //200 이면 성공
                .andExpect(content().string(hello));    // 응답 본문의 리턴 값이 hello와 일치하면 성공
    }

    @WithMockUser(roles="USER")
    @Test
    public void return_HelloDto() throws Exception{
        String name = "hello";
        int amount =  1000;
        String name2 = "hello";
        int amount2 =  1000;

        mvc.perform(get("/hello/dto").param("name",name2).param("amount",String.valueOf(amount2))) //get의 파라미터 설정
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
