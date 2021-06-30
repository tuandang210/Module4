package com.codegym.service;

import com.codegym.model.Blog;

import java.util.Optional;

public interface IBlogService {
    Iterable<Blog> findAll();

    Optional<Blog> findById(Long id);

    void save(Blog blog);

    void delete(Long id);
}
