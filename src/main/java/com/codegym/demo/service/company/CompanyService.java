package com.codegym.demo.service.company;

import com.codegym.demo.model.Company;
import com.codegym.demo.repository.CompanyRepository;
import com.codegym.demo.security.principal.CompanyPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void remove(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByCompanyName(String companyName) {
        return companyRepository.existsByCompanyName(companyName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Company> company = findByEmail(username);
        if (!company.isPresent()) throw new UsernameNotFoundException(username);
        return CompanyPrinciple.build(company.get());
    }
}
