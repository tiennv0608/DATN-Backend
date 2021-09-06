package com.codegym.demo.service.province;

import com.codegym.demo.model.Post;
import com.codegym.demo.model.Province;
import com.codegym.demo.repository.PostRepository;
import com.codegym.demo.repository.ProvinceRepository;
import com.codegym.demo.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Optional<Province> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Province save(Province province) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }


}
