package com.codegym.demo.security.userprincipal;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.repository.CompanyRepository;
import com.codegym.demo.repository.UserRepository;
import com.codegym.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    IUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found -> email or password" + email));
        return UserPrinciple.buildUser(user);
    }

    public UserDetails loadCompanyByEmail(String email) throws UsernameNotFoundException {
        Company company = companyRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found -> email or password" + email));
        return UserPrinciple.buildCompany(company);
    }

    //HAM LAY RA USER HIEN TAI DE THUC HIEN THAO TAC VOI DB
    public User getCurrentUser() {
        Optional<User> user;
        String email;
        //Lay 1 object principal trong SecurityContexHolder
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //So sanh obj voi Userdetails neu ma dung thi gan userName = principal.getUsername();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            //neu khong phai user hien tai thi userName = principal.toString();
            email = principal.toString();
        }
        //kiem tra neu userName ton tai trong DB thi gan user = ham tim kiem trong DB theo userName do
        if (userRepository.existsByEmail(email)) {
            user = userService.findByEmail(email);
        } else {
            //Neu chua ton tai thi tra ve 1 the hien cua lop User thong qua Optional.of
            user = Optional.of(new User());
            //set cho no 1 cai ten user an danh Day la truong hop ma tuong tac qua dang nhap kieu FB hay GG
            user.get().setEmail("Anonymous");
        }
        return user.get();
    }
}
