package com.wisejade.api.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 15)
    private String password;

    @Column(nullable = false, length = 1)
    private String sex;

    @Column(nullable = false, unique = true)
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private IndustryType companyType;

    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City companyLocation;
}
