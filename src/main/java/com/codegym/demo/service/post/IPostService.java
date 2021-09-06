package com.codegym.demo.service.post;

import com.codegym.demo.model.Post;
import com.codegym.demo.service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findByAddressContaining(String address);
    Iterable<Post> search(String address, String description);
//    Iterable<Post> search(String address, String description,String title,String position);
    Iterable<Post>findTop2New();
    Iterable<Post> findAllByAddressAndDescription(String address, String description);
}
