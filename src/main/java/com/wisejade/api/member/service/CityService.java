package com.wisejade.api.member.service;

import com.wisejade.api.exception.BusinessLogicException;
import com.wisejade.api.exception.ExceptionCode;
import com.wisejade.api.member.entity.City;
import com.wisejade.api.member.entity.Member;
import com.wisejade.api.member.repository.CityRepository;

import java.util.Optional;

public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City createCity(String cityId, String name) {
        City city = new City(cityId, name);
        return cityRepository.save(city);
    }

    public City findByCityId(String cityId) {
        City city = findVerifiedCity(cityId);
        return city;
    }

    public void addMemberList(Member member, String cityId) {
        City city = findByCityId(cityId);

    }

    public City findVerifiedCity(String cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        City findCity = optionalCity.orElseThrow(() ->new BusinessLogicException(ExceptionCode.CITY_NOT_FOUND));
        return findCity;
    }
}
