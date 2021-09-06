package com.codegym.demo.repository;

import com.codegym.demo.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends PagingAndSortingRepository<Province,Long> {
}
