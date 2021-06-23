package com.codegym.service;

import com.codegym.model.Cmt;

import com.codegym.repository.CmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CmtService implements ICmtService {
    @Autowired
    public CmtRepository cmtRepository;

    @Override
    public Page<Cmt> findAll(Pageable pageable) {
        return cmtRepository.findAll(pageable);
    }

    @Override
    public Optional<Cmt> findById(Long id) {
        return cmtRepository.findById(id);
    }

    @Override
    public void save(Cmt cmt) {
        if(cmt.getAuthor().isEmpty() || cmt.getFeedback().isEmpty()){
            return;
        }
        cmtRepository.save(cmt);
    }

    @Override
    public void remove(Cmt cmt) {
        cmtRepository.delete(cmt);
    }
}
