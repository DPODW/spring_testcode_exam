package com.testpractice.example.service;

import com.testpractice.example.dto.MemberResponseDto;

import java.util.List;

public interface MemberService {

    List<MemberResponseDto.ListDto> findAll();

    Long createMember(String name, int age);
}
