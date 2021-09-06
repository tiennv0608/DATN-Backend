package com.codegym.demo.controller;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.Post;
import com.codegym.demo.service.category.ICategoryService;
import com.codegym.demo.service.company.ICompanyService;
import com.codegym.demo.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public ResponseEntity<Void> create(@RequestBody Post post) {
        Company company = iCompanyService.findById(post.getCompany().getId()).get();
        post.setCode("CODE"+company.getCompanyCode()+post.getCategory().getId());
        post.setStatus(true);
        iPostService.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Optional<Post> postOptional = iPostService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editPost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = iPostService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        post.setId(id);
        iPostService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
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
    @GetMapping("/search")
    public ResponseEntity<Iterable<Post>> findAllByAddressAndDescription( String address,  String description){
        List<Post> postList= (List<Post>) iPostService.findAllByAddressAndDescription(address, description);
        if(postList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
}
