package com.konkuk.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration      //WebMvcTest는 일반적인 @Configuration, @Componenet, @Service는 스캔하지 않습니다.
@EnableJpaAuditing  //JPA Auditing 활성화
public class JpaConfig {}
