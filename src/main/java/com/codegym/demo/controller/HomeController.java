package com.codegym.demo.controller;

import com.codegym.demo.dto.response.PostResponse;
import com.codegym.demo.model.Post;
import com.codegym.demo.service.company.ICompanyService;
import com.codegym.demo.service.post.IPostService;
import com.codegym.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/home")
@RestController
@CrossOrigin(origins = "*")
public class HomeController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IPostService postService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    private ResponseEntity<?> findJob(@RequestParam(name = "keyword") String keyword, @RequestParam(name = "cat_id") Long cat_id, @RequestParam(name = "city_id") Long city_id,@RequestParam(name = "salary_id") Long salary_id){
        List<PostResponse> postResponses;
        postResponses = postService.searchJob(keyword, cat_id, city_id, salary_id);
        System.out.println(keyword);
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    private ResponseEntity<?> findAllPPost(Integer page){
        Page<PostResponse> postResponses;
        Pageable pageable;
        if(page!=null){
            pageable = PageRequest.of(page,6);
        }
        else {
            pageable = PageRequest.of(0,6);
        }
        postResponses = postService.findAllPage(pageable);
        return new ResponseEntity<>(postResponses,HttpStatus.OK);
    }
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    private ResponseEntity<?> countPosts(){
        List<Post> postList = (List<Post>) postService.findAll();
        return new ResponseEntity<>(postList.size(),HttpStatus.OK);
    }



}
