package com.codegym.demo.controller;

import com.codegym.demo.dto.response.Response;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.Post;
import com.codegym.demo.service.category.ICategoryService;
import com.codegym.demo.service.company.ICompanyService;
import com.codegym.demo.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.codegym.demo.dto.response.ResponseBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private IPostService iPostService;
    @Autowired
    private ICompanyService iCompanyService;
    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping
    public ResponseEntity<Iterable<Post>> findAll() {
        List<Post> posts = (List<Post>) iPostService.findAll();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseBody> create(@Validated @RequestBody Post post) {
        try {
            if (post.getQuantity()==0){
                return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
            }
            Company company = iCompanyService.findById(post.getCompany().getId()).get();
            post.setCode("CODE"+company.getCompanyCode()+post.getCategory().getId());
            post.setStatus(true);
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, iPostService.save(post)), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Optional<Post> postOptional = iPostService.findById(id);

        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/companies/{idCompany}")
    public ResponseEntity<Iterable<Post>> findAllByCompany(@PathVariable Long idCompany){
        List<Post> posts = (List<Post>) iPostService.findAllByIdCompany(idCompany);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> editPost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = iPostService.findById(id);
        post.setCode(postOptional.get().getCode());
        post.setStatus(postOptional.get().getStatus());
        post.setId(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
        }
        if (post.getQuantity()==0){
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, iPostService.save(post)), HttpStatus.CREATED);
    }
    @GetMapping("/status/{id}")
    public ResponseEntity<ResponseBody> editStatus(@PathVariable Long id) {
        Optional<Post> postOptional = iPostService.findById(id);

        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
        }
        if (postOptional.get().getQuantity()==0){
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
        }
        if (postOptional.get().getStatus()==true){
            postOptional.get().setStatus(false);
        }else {
            postOptional.get().setStatus(true);
        }
        Post post = postOptional.get();
        return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, iPostService.save(post)), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id) {
        Optional<Post> productOptional = iPostService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iPostService.remove(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.NO_CONTENT);
    }
}
