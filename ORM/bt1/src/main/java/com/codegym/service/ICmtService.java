package com.codegym.service;

import com.codegym.model.Cmt;

import java.util.List;

public interface ICmtService {

    List<Cmt> findAll();

    Cmt findOne(Long id);

    Cmt save(Cmt cmt);
}
