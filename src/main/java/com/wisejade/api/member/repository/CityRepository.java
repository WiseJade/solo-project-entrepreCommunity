package com.wisejade.api.member.repository;

import com.wisejade.api.member.entity.City;
import com.wisejade.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, String> {
}
