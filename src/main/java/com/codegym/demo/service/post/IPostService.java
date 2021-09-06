package com.codegym.demo.service.post;

import com.codegym.demo.dto.response.PostResponse;
import com.codegym.demo.model.Post;
import com.codegym.demo.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService extends IGeneralService<Post> {
    PostResponse convert (Post post);
    List<PostResponse> searchJob(String keyword, Long cat_id, Long city_id, Long salary_id);
    Page<PostResponse> findAllPage(Pageable pageable);


}
