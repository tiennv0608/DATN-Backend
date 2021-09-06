package com.codegym.demo.service.post;

import com.codegym.demo.dto.response.PostResponse;
import com.codegym.demo.model.Post;
import com.codegym.demo.repository.CompanyRepository;
import com.codegym.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse convert(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setAddress(post.getAddress());
        postResponse.setCompanyname(post.getCompany().getCompanyName());
        postResponse.setTittle(post.getTitle());
        postResponse.setExpireddate(post.getExpiredDate());
        postResponse.setSalary(post.getSalary().getName());
        return postResponse;
    }

    @Override
    public List<PostResponse> searchJob(String keyword, Long cat_id, Long city_id, Long salary_id) {
        List<Post> postList = new ArrayList<>();
        if (keyword.equals("")){
            postList.addAll(postRepository.findAll());
        }else {
            postList.addAll(postRepository.findAllByTitleContainsOrCompanyCompanyNameContainsOrAddressContains(keyword,keyword,keyword));
        }
        if (cat_id == -1){
            postList.addAll(postRepository.findAll());
        }else {
            postList.addAll(postRepository.findAllByCategoryId(cat_id));
        }
        if (city_id == -1){
            postList.addAll(postRepository.findAll());
        }else {
            postList.addAll(postRepository.findAllByCityId(city_id));
        }
        if (salary_id == -1){
            postList.addAll(postRepository.findAll());
        }else {
            postList.addAll(postRepository.findAllBySalaryId(salary_id));
        }
        Set<Post> postSet = this.getDuplicate(postList,4);
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post: postSet) {
            postResponses.add(this.convert(post));
        }
        return postResponses;
    }
    public Set<Post> getDuplicate(List<Post> postList, int number){
//        Set<Post> posts = new HashSet<>();
//        Set<Post> postSet = postList.stream().filter(n -> !posts.add(n)).collect(Collectors.toSet());
        final Set<Post> postSet = new HashSet<>();

        for (Post post : postList)
        {
            int occurrences = Collections.frequency(postList, post);

            if (occurrences == number)
            {
                postSet.add(post);
            }
        }
        return postSet;
    }

    @Override
    public Page<PostResponse> findAllPage(Pageable pageable) {
        Page<Post> postList = postRepository.findPosts(true,pageable);
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post: postList) {
            postResponses.add(this.convert(post));
        }
        return new PageImpl<>(postResponses,pageable, postResponses.size());
    }
}
