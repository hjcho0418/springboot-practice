package me.chohyeonjae.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자
@Getter
@Entity //엔티티로 지정
public class Member {
    @Id  //id필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키를 자동으로 1씩증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false) //name이라는 not null컬럼과 매핑
    private String name;

    public void changeName(String name) {
        this.name = name;
    }

}