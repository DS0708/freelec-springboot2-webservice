package com.konkuk.book.springboot.service.posts;


import com.konkuk.book.springboot.domain.posts.Posts;
import com.konkuk.book.springboot.domain.posts.PostsRepository;
import com.konkuk.book.springboot.web.dto.PostsListResponseDto;
import com.konkuk.book.springboot.web.dto.PostsResponseDto;
import com.konkuk.book.springboot.web.dto.PostsSaveRequestDto;
import com.konkuk.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        //update 부분에서 따로 쿼리를 날리지 않는 이유 = dirty checking (https://jojoldu.tistory.com/415)
        //JPA에서는 트랜잭션이 끝나는 시점에 변화가 있는 모든 엔티티 객체를 데이터베이스에 자동으로 반영해줍니다.
        //findById를 통해 찾을 때 해당 엔티티의 스냅샷을 만들어 놓는다.

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()//List<Posts>를 가공하거나 매핑할 수 있게 stream 객체로 변환
                .map(PostsListResponseDto::new) //.map(posts -> new PostsListResponseDto(posts))와 같음
                .collect(Collectors.toList());  //스트림을 List<PostsListsResponseDto>로 변환
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }
}
