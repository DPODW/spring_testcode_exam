package com.testpractice.example.unit;

import com.testpractice.example.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExamUnitTest {

    @Test
    @DisplayName("멤버가 생성되는지 확인하는 테스트")
    void createMember(){
        /* given */
        Member member = Member.builder().age(10).name("noon").build();

        /* when , then */
        Assertions.assertThat(member.getAge()).isEqualTo(10);
        Assertions.assertThat(member.getName()).isEqualTo("noon");
    }


    @Test
    @DisplayName("멤버의 나이가 바뀌는지 확인하는 테스트")
    void changeAgeTest(){
        /* given (아래의 member 객체가 생성되었을때) */
        Member member = Member.builder().age(10).name("noon").build();

        /* when (changeAge 메소드를 통해 age 를 13 으로 변경한다면) */
        member.changeAge(13);

        /* then (changeAge 메소드가 정상적으로 작동해서 나이가 변경 되는가 ?) */
        Assertions.assertThat(member.getAge()).isEqualTo(13);


    }

}
