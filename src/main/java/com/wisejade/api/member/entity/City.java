package com.wisejade.api.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class City {
    @Id
    private String cityId;

    @Column(nullable = false, length = 10, unique = true)
    private String name;

    @OneToMany(mappedBy = "companyLocation", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    public City(String cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }
}
