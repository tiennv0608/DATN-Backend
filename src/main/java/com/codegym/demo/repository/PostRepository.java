package com.codegym.demo.repository;

import com.codegym.demo.model.Company;
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
    @Query(nativeQuery = true, value = "SELECT * FROM post p WHERE p.company_id =:id_company")
    Iterable<Post> findAllByIdCompany(@Param("id_company") Long id);
    List<Post> findAllByTitleContainsOrCompanyCompanyNameContainsOrAddressContains(String keyword, String company, String address);
    List<Post> findAllByCategoryId(long id);
    List<Post> findAllByCityId(long id);
    @Query(value = "select * from post where (salary >?1 or salary=?1) and (salary <?2 or salary =?2)",nativeQuery = true)
    List<Post> findAllBySalary(double salary1, double salary2);
    @Query(value = "select * from post order by quantity",nativeQuery = true)
    Page<Post> findPosts(Boolean bl, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM post\n" +
            "GROUP BY company_id\n" +
            "HAVING SUM(quantity)\n" +
            "ORDER BY SUM(quantity) desc limit 5")
    Iterable<Post> findTop5Company();
}
