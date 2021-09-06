package com.codegym.demo.service.city;

import com.codegym.demo.model.City;
import com.codegym.demo.repository.CityRepository;
import com.codegym.demo.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CityService implements ICityService{
    @Autowired
    private CityRepository cityRepository;
    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public City save(City city) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
