package com.codegym.demo.repository;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM post p WHERE p.company_id =:id_company")
    Iterable<Post> findAllByIdCompany(@Param("id_company") Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM post\n" +
            "GROUP BY company_id\n" +
            "HAVING SUM(quantity)\n" +
            "ORDER BY SUM(quantity) desc limit 5")
    Iterable<Post> findTop5Company();
}
