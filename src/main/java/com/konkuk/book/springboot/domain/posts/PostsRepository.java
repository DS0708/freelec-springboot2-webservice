package com.konkuk.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> { // 기본적인 CRUD 메소드가 자동 생성

    @Query("SELECT p From Posts p order by p.id DESC")
    List<Posts> findAllDesc();

}
