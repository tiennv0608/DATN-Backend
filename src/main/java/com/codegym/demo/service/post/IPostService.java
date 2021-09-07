package com.codegym.demo.service.post;

import com.codegym.demo.model.Post;
import com.codegym.demo.service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllByPositionContaining(String position);
    Iterable<Post>findAllByTitleContaining(String title);
    Iterable<Post>findAllByAddressContaining(String address);
    Iterable<Post>findAllBySalaryContaining(double salary);
    Iterable<Post>searchAdvanced(String title, Integer salary,String exp, String address);
}
