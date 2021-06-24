package com.codegym.service;

import com.codegym.model.Cmt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICmtService {
    Page<Cmt> findAll(Pageable pageable);

    Optional<Cmt> findById(Long id) throws Exception;

    void save(Cmt cmt);

    void remove(Cmt cmt);
}
