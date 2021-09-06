package com.codegym.demo.repository;

import com.codegym.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByPositionContaining(String position);

    Iterable<Post> findAllByTitleContaining(String title);

    Iterable<Post> findAllByAddressContaining(String address);

    Iterable<Post> findAllBySalaryIsGreaterThan(double salary);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM post " +
            "WHERE (:title IS NULL OR title LIKE %:title%) " +
            "  AND (:salary IS NULL OR salary >= :salary) " +
            "  AND (:address IS NULL OR address LIKE %:address%)")
    Iterable<Post> searchAdvanced(
            @Param("title") String title,
            @Param("salary") Integer salary,
//            @Param("exp") String exp,
            @Param("address") String address);
}
