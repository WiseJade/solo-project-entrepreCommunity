package com.wisejade.api.member.mapper;

import com.wisejade.api.member.dto.MemberPostDto;
import com.wisejade.api.member.dto.MemberResponseDto;
import com.wisejade.api.member.entity.City;
import com.wisejade.api.member.entity.IndustryType;
import com.wisejade.api.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper{
    MemberResponseDto memberToMemberResponseDto (Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

    default Member memberPostDtoToMember(MemberPostDto postDto) {
        Member member = Member.builder()
                        .name(postDto.getName())
                .password(postDto.getPassword())
                .sex(postDto.getSex())
                .companyName(postDto.getCompanyName())
                .companyLocation(new City(postDto.getCompanyType(), ""))
                .companyType(new IndustryType(postDto.getCompanyType(), ""))
        .build();
        return member;
    }
}
