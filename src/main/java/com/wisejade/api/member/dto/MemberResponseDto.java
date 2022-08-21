package com.wisejade.api.member.dto;

import com.wisejade.api.member.entity.City;
import com.wisejade.api.member.entity.IndustryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String name;
    private String sex;
    private String companyName;
    private IndustryType companyType;
    private City companyLocation;
}
