package com.wisejade.api.member.service;

import com.wisejade.api.exception.BusinessLogicException;
import com.wisejade.api.exception.ExceptionCode;
import com.wisejade.api.member.entity.Member;
import com.wisejade.api.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findByLocation(String companyLocation) {
        List<Member> members = memberRepository.findByCompanyLocation(companyLocation);
        return members;
    }

    public List<Member> findByType(String companyType) {
        List<Member> members = memberRepository.findByCompanyType(companyType);
        return members;
    }

    public List<Member> findAll() {
        List<Member> members = memberRepository.findAll();
        return members;
    }

    public Member createMember(Member member) {
        verifyExistsCompanyName(member.getCompanyName());


        Member createdMember = memberRepository.save(member);
        return createdMember;
    }

    private void verifyExistsCompanyName(String companyName) {
        Optional<Member> member = memberRepository.findByCompanyName(companyName);
        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.COMPANYNAME_EXISTS);
        }
    }
}
