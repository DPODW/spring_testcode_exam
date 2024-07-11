package com.testpractice.example.unit;

import com.testpractice.example.controller.MemberController;
import com.testpractice.example.dto.MemberResponseDto;
import com.testpractice.example.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(MemberController.class)
public class ExamControllerUnitTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberService memberService;

    @Test
    @DisplayName("컨트롤러에서 리스트 반환받기")
    void getList() throws Exception {

        /* given */
        List<MemberResponseDto.ListDto> testList = List.of(new MemberResponseDto.ListDto("asd", 10)
                , new MemberResponseDto.ListDto("fsd", 12));
        // 응답 객체 2개가 들어있는 List 를 만든다.

        Mockito.when(memberService.findAll()).thenReturn(testList);
        //실제 컨트롤러에서 memberService.findAll 이 호출되면, 위의 testList 를 반환한다.

        /* when then */
        mvc.perform(MockMvcRequestBuilders.get("/members").contentType(MediaType.APPLICATION_JSON))
        // MockMvc 에서 제공하는 기능을 사용하여 컨트롤러오 요청을 보냄.

                .andDo(MockMvcResultHandlers.print()) //컨트롤러 요청 상태를 로그로 확인할 수 있음
                .andExpect(MockMvcResultMatchers.status().isOk()) //컨트롤러 요청 상태를 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("fsd")) //json 리스트 값 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("asd")); //json 리스트 값 검증

    }


}
