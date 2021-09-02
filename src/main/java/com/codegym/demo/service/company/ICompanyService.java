package com.codegym.demo.service.company;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.service.IGeneralService;

import java.util.Optional;

public interface ICompanyService extends IGeneralService<Company> {
    Optional<Company> findByEmail(String email); //Tim kiem email co ton tai trong DB khong?

    Boolean existsByEmail(String email); //email da co trong DB chua?
}
