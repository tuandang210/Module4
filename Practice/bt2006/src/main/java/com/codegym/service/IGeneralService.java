package com.codegym.service;

import com.codegym.exception.DuplicateNameExceptionCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findALl();

    Optional<T> findById(Long id) throws Exception;

    T save(T t) throws DuplicateNameExceptionCategory;

    void remove(Long id);

    Page<T> findAll(Pageable pageable);
}
