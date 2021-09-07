package com.codegym.demo.repository;

import com.codegym.demo.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //    @Query(value = "select * from post where title = ? ",nativeQuery = true)
//    @Query(value = "select * from post p join company c on c.id = p.company_id where c.company_name LIKE '%?%' ",nativeQuery = true)
    Iterable<Post> findAllByTitleContainsOrCompanyCompanyNameContainsOrAddressContains(String keyword, String company, String address);

    Iterable<Post> findAllByCategoryId(long id);

    Iterable<Post> findAllByCityId(long id);

    Iterable<Post> findAllBySalaryIsGreaterThanEqual(double salary);

    @Query(value = "select * from post order by quantity", nativeQuery = true)
    Page<Post> findPosts(Boolean bl, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM post p WHERE p.company_id =:id_company")
    Iterable<Post> findAllByIdCompany(@Param("id_company") Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM post\n" +
            "GROUP BY company_id\n" +
            "HAVING SUM(quantity)\n" +
            "ORDER BY SUM(quantity) desc limit 5")
    Iterable<Post> findTop5Company();
}
