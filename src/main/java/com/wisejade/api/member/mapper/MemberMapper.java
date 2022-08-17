package com.wisejade.api.member.mapper;

import com.wisejade.api.member.dto.MemberResponseDto;
import com.wisejade.api.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper{
    MemberResponseDto memberToMemberResponseDto (Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
