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
        return postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findAllByPositionContaining(String position) {
        return postRepository.findAllByPositionContaining(position);
    }

    @Override
    public Iterable<Post> findAllByTitleContaining(String title) {
        return postRepository.findAllByTitleContaining(title);
    }

    @Override
    public Iterable<Post> findAllByAddressContaining(String address) {
        return postRepository.findAllByAddressContaining(address);
    }

    @Override
    public Iterable<Post> findAllBySalaryContaining(double salary) {
        return postRepository.findAllBySalaryIsGreaterThan(salary);
    }
}
