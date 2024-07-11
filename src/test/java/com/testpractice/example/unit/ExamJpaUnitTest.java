package com.testpractice.example.unit;

import com.testpractice.example.entity.Member;
import com.testpractice.example.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExamJpaUnitTest {
    //@AutoConfigureTestDatabase 의 설정을 Replace.NONE 으로 하면, 실제 DB 를 활용하여 테스트할 수 있다.
    //JPA 를 활용하여 실제 DB 테스트

    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("실제 DB에 멤버 저장")
    void createMember(){
        /* given ( 아래의 member 객체가 생성되었을때) */
        Member member1 = Member.builder().name("h1").age(10).build();
        Member member2 = Member.builder().name("h2").age(20).build();

        /* when ( member 객체를 JPA 를 활용하여 DB 에 save 요청을 한다면) */
        Member result1 = memberRepository.save(member1);
        Member result2 = memberRepository.save(member2);

        /* then ( given 에서 생성한 나이와, JPA 를 통해 저장된 나이가 같은지? )*/
        Assertions.assertThat(result1.getAge()).isEqualTo(member1.getAge());
        Assertions.assertThat(result2.getAge()).isEqualTo(member2.getAge());
    }


    @Test
    @DisplayName("멤버 리스트를 정상적으로 반환 하는지 확인")
    void MemberList(){
        /* given ( member 객체 생성 후, DB 에 저장하였을때 ) */
        Member member1 = Member.builder().name("h1").age(10).build();
        Member member2 = Member.builder().name("h2").age(20).build();

        Member result1 = memberRepository.save(member1);
        Member result2 = memberRepository.save(member2);

        /* when ( JPA 의 findAll 메소드로 저장된 member 를 가져온다 ) */
        List<Member> resultList = memberRepository.findAll();


        /* then ( List 의 size 가 2인지 확인한다. ) */
        Assertions.assertThat(resultList.size()).isEqualTo(2);


    }


}
