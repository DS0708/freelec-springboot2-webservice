package com.konkuk.book.springboot.web;

import com.konkuk.book.springboot.config.auth.LoginUser;
import com.konkuk.book.springboot.config.auth.dto.SessionUser;
import com.konkuk.book.springboot.service.posts.PostsService;
import com.konkuk.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){   //Model은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.

        model.addAttribute("posts",postsService.findAllDesc());
        //postsService.findAllDesc()의 결과를 posts이름으로 index.mustache로 전달

//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        //앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
//        // 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있음

        if(user != null){ //세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 됨
            model.addAttribute("userName", user.getName());
        }

        return "index";
        /*
        * 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 떄 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
        * src/main/resources/templates/index.mustache 로 전환되어 View Resolver가 처리하게 된다.
        * */
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
