package com.testpractice.example.service.impl;

import com.testpractice.example.dto.MemberResponseDto;
import com.testpractice.example.entity.Member;
import com.testpractice.example.repository.MemberRepository;
import com.testpractice.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override

    public List<MemberResponseDto.ListDto> findAll(){
        return memberRepository.findAll().stream().map(a -> new MemberResponseDto.ListDto(a.getName(),a.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public Long createMember(String name, int age){
        memberRepository.findByName(name).ifPresent(a -> {
            //ifPresent : Optional 객체가 값을 가지고 있다면 (null 이 아니라면) true 반대의 경우라면 false
            throw new IllegalStateException("이미 있는 아이디");
        });

        Member member = Member.builder()
                .age(age)
                .name(name).build();

        return memberRepository.save(member).getId();
    }
}