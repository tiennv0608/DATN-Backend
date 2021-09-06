package com.codegym.demo.service.post;

import com.codegym.demo.model.Post;
import com.codegym.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PostService implements IPostService{
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
        return (Post) postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findByAddressContaining(String address) {
        return postRepository.findByAddressContaining(address);
    }

    @Override
    public Iterable<Post> search(String address, String description) {
        return postRepository.search(address, description);
    }

    @Override
    public Iterable<Post> findTop2New() {
        return postRepository.findTop2New();
    }

    @Override
    public Iterable<Post> findAllByAddressAndDescription(String address, String description) {
        return postRepository.findAllByAddressAndDescription(address,description);
    }

}
