package com.wisejade.api.member.controller;

import com.wisejade.api.member.dto.SingleResponseDto;
import com.wisejade.api.member.entity.Member;
import com.wisejade.api.member.mapper.MemberMapper;
import com.wisejade.api.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Slf4j
public class MemberController {
    @Autowired
    private final MemberService memberService;
    @Autowired
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password) {

        return "loginForm";
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> members = memberService.findAll();
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.membersToMemberResponseDtos(members)), HttpStatus.OK);
    }

    @GetMapping("/location")
    public ResponseEntity getMembersByLocation(@Positive @RequestParam String companyLocation) {
        List<Member> members = memberService.findByLocation(companyLocation);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.membersToMemberResponseDtos(members)), HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity getMembersByType(@Positive @RequestParam String companyType) {
        List<Member> members = memberService.findByType(companyType);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.membersToMemberResponseDtos(members)), HttpStatus.OK);
    }

}
