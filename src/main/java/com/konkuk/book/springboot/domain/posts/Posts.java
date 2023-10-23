package com.konkuk.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//getter 와 NoArgsConstructor는 롬복의 어노테이션이며 Entity는 JPA의 어노테이션
//롬복은 코드를 단순화시켜 주지만 필수 어노테이션은 아님
//Entity는 테이블과 링크될 클래스임을 나타냄 (이름 매핑 ex) SalesManager.java -> sales_manager table)
@Getter                 //클래스 내 모든 필드의 Getter 메소드를 자동생성, 참고로 Entity클래스 에서는 절대 Setter 메소드를 만들지 않는다.
@NoArgsConstructor      //기본 생성자 자동 추가,  public Posts() {}와 같은 효과
@Entity
public class Posts {
    @Id     //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //PK의 생성 규칙을 나타냄, IDENTITY는 auto_increment이다.
    private Long id;

    @Column(length = 500, nullable = false) //굳이 선언하지 않아도 해당 테이블의 컬럼이되지만, 기본값 이외 추가로 변경이 필요한 옵션이 있으면 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        //생성자 대신에 @Builder를 통해 제공되는 빌더 클래스 사용, Builder를 사용하게 되면 어느 필드에 어떤 값을 채워야할지 명확하게 인지할 수 있다.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
