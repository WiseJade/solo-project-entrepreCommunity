package com.wisejade.api.member.service;

import com.wisejade.api.member.entity.Member;
import com.wisejade.api.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
