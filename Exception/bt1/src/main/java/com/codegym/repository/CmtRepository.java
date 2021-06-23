package com.codegym.repository;

import com.codegym.model.Cmt;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmtRepository extends PagingAndSortingRepository<Cmt, Long> {

}
