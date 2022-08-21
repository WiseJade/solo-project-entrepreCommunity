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
@AllArgsConstructor
public class IndustryType {
    @Id
    private String typeId;

    @Column(nullable = false, length = 10, unique = true)
    private String name;
}
