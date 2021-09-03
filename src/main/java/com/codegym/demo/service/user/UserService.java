package com.codegym.demo.service.user;

import com.codegym.demo.model.User;
import com.codegym.demo.repository.UserRepository;
import com.codegym.demo.security.userprincipal.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

//    @Override
//    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
//        Optional<User> user = findByEmail(email);
//        if (!user.isPresent()) throw new UsernameNotFoundException(email);
//        return UserPrinciple.build(user.get());
//    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = findByEmail(username);
        if (!user.isPresent()) throw new UsernameNotFoundException(username);
        return UserPrinciple.build(user.get());
    }
}
