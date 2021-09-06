package com.codegym.demo.repository;

import com.codegym.demo.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query(value = "select * from post where title = ? ",nativeQuery = true)
//    @Query(value = "select * from post p join company c on c.id = p.company_id where c.company_name LIKE '%?%' ",nativeQuery = true)
    List<Post> findAllByTitleContainsOrCompanyCompanyNameContainsOrAddressContains(String keyword,String company,String address);
    List<Post> findAllByCategoryId(long id);
    List<Post> findAllByCityId(long id);
    List<Post> findAllBySalaryId(long id);
    @Query(value = "select * from post order by quantity",nativeQuery = true)
    Page<Post> findPosts(Boolean bl,Pageable pageable);
}
