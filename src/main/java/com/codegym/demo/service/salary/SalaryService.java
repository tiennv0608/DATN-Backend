package com.codegym.demo.service.salary;

import com.codegym.demo.model.Salary;
import com.codegym.demo.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaryService implements ISalaryService{
    @Autowired
    SalaryRepository salaryRepository;

    @Override
    public Iterable<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Optional<Salary> findById(Long id) {
        return salaryRepository.findById(id);
    }

    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public void remove(Long id) {
        salaryRepository.deleteById(id);
    }
}
