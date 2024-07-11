package com.testpractice.example.unit;

import com.testpractice.example.entity.Member;
import com.testpractice.example.repository.MemberRepository;
import com.testpractice.example.service.MemberService;
import com.testpractice.example.service.impl.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class ExamServiceTest {

    //Test 주체
    MemberService memberService;

    //Test 협력자 ( memberService 계층에서 memberRepository 를 호출하는 로직이 존재함 -> 가짜 객체( MockBean )으로 대체 )
    //가짜 객체를 사용할때는 가짜 객체의 응답 또한 정의해줘야 함
    @MockBean
    MemberRepository memberRepository;


    @BeforeEach
    void setup(){
        memberService = new MemberServiceImpl(memberRepository);
    }


    @Test
    @DisplayName("멤버 생성 성공")
    void createMemberSuccess(){

        /* given  */

        Member testMember = Member.builder().name("hi3").age(10).build();
        ReflectionTestUtils.setField(testMember,"id",3L);
        //ReflectionTestUtils.setField : private 로 설정된 필드에 값을 설정할 수 있게 해줌
        //member 객체의 id 필드는 private 이기 때문에, 위와 같은 방법으로 접근하여 값 설정


        Mockito.when(memberRepository.save(testMember)).thenReturn(testMember);
        //when -> ( 가짜 객체 로직 실행 ) -> thenReturn -> 가짜 객체 응답 정의 (member)
        // createMember 의 실제 memberRepository.save(member) 가 호출되면, 위의 Mockito 기능으로 인해서
        // 최종적으로 testMember 를 반환하게 된다 ( thenReturn(testMember)).


        /* when */

        Long hi3 = memberService.createMember("hi3", 10);


        /* then */
        Assertions.assertThat(hi3).isEqualTo(3L);
    }

    @Test
    @DisplayName("멤버 생성시 member1 과 이름이 같아서 예외 발생")
    void createMemberFail(){

        /* given */
        Member duplicateMember = Member.builder().name("hi1").age(10).build();
        Mockito.when(memberRepository.findByName("hi1")).thenReturn(Optional.of(duplicateMember));
        //findByName 에 일부러 중복된 name 을 요청하고, 이미 저장되어있는 member1 를 반환하게 만든다.

        /* when then */
        Assertions.assertThatThrownBy(()->memberService.createMember("hi1",10)).isInstanceOf(IllegalStateException.class);

    }

}
