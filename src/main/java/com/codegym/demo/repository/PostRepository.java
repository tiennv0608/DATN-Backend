package com.codegym.demo.repository;

import com.codegym.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByPositionContaining(String position);
    Iterable<Post>findAllByTitleContaining(String title);
    Iterable<Post>findAllByAddressContaining(String address);
   Iterable<Post>findAllBySalaryIsGreaterThan(double salary);

}
