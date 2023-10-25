package com.konkuk.book.springboot.service.posts;


import com.konkuk.book.springboot.domain.posts.Posts;
import com.konkuk.book.springboot.domain.posts.PostsRepository;
import com.konkuk.book.springboot.web.dto.PostsResponseDto;
import com.konkuk.book.springboot.web.dto.PostsSaveRequestDto;
import com.konkuk.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor //final이 선언된 모든 필자를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        //update 부분에서 따로 쿼리를 날리지 않는 이유 = dirty checking (https://jojoldu.tistory.com/415)

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }


}
