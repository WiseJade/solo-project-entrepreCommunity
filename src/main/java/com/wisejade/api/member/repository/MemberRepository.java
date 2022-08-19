package com.wisejade.api.member.repository;

import com.wisejade.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByCompanyLocation(String companyLocation);
    List<Member> findByCompanyType(String companyType);

    Optional<Member> findByCompanyName(String companyName);
}
