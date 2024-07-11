package com.testpractice.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Builder
    public Member(String name, int age){
        this.name=name;
        this.age=age;
    }


    @Override
    public boolean equals(Object obj) { // 협력자에서 이름 중복 예외를 검증하기 위함
        Member me = (Member) obj;
        return this.name.equals(me.name) && this.age==me.age;
        //현재 객체의 name (this) 필드와 입력받은 객체 (obj) 의 name 필드가 같은지 검사 && age 필드도 동일하게 검사
    }

    public void changeAge(int age){
        this.age=age;
    }
}
