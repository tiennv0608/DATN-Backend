package com.codegym.demo.service.company;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface ICompanyService extends IGeneralService<Company>, UserDetailsService {
    Optional<Company> findByEmail(String email); //Tim kiem email co ton tai trong DB khong?

    Boolean existsByEmail(String email); //email da co trong DB chua?

    Boolean existsByCompanyName(String companyName); // ten cong ty da co trong DB chua

    Company changeInfo(Long id, Company company);

    Iterable<Company> getEnableCompanies ();

    Company setEnable(long id);
}
