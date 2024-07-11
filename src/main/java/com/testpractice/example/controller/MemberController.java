package com.testpractice.example.controller;


import com.testpractice.example.dto.MemberRequestDto;
import com.testpractice.example.dto.MemberResponseDto;
import com.testpractice.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberResponseDto.ListDto> getMemberList(){
        return memberService.findAll();
    }

    @PostMapping("/members")
    public Long createMember(@RequestBody MemberRequestDto.CreateDto createDto){
        return memberService.createMember(createDto.getName(), createDto.getAge());
    }
}
