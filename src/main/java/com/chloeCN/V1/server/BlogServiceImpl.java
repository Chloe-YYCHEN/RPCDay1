package com.chloeCN.V1.server;

import com.chloeCN.V1.common.Blog;
import com.chloeCN.V1.service.BlogService;

public class BlogServiceImpl implements BlogService {

    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("My Blog").useId(22).build();
        System.out.println("Client select count" + id + "blogs");
        return blog;
    }
}
