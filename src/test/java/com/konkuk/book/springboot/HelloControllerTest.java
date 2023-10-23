package com.konkuk.book.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; //이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있습니다.

    @Test
    public void return_hello() throws Exception {
        String hello = "hello23";

        mvc.perform(get("/hello"))  //MockMvc를 통해 /hello 주소로 HTTP GET 요청을 합니다
                .andExpect(status().isOk())    //200 이면 성공
                .andExpect(content().string(hello));    // 응답 본문의 리턴 값이 hello와 일치하면 성공
    }

}
