package com.codegym.demo.repository;

import com.codegym.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findByAddressContaining( String address);
    @Query(value = "select * from post order by id desc limit 10", nativeQuery = true)
    Iterable<Post> findTop2New();
    Iterable<Post> findAllByAddressAndDescription(String address, String description);
}