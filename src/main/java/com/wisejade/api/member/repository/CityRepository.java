package com.wisejade.api.member.repository;

import com.wisejade.api.member.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
}
