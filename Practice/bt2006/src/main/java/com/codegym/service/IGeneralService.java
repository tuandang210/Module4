package com.codegym.service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> findALl();

    T findById(Long id);

    void save(T t);

    void delete(Long id);
}
