package com.codegym.service;

import com.codegym.model.Cmt;

import com.codegym.repository.CmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public Optional<Cmt> findById(Long id) throws Exception {
        Optional<Cmt> cmtOptional = cmtRepository.findById(id);
        if(!cmtOptional.isPresent()){
            throw new Exception("Comment not found");
        }
        return cmtOptional;
    }

    @Override
    public void save(Cmt cmt) {
        List<String> badWords = new ArrayList<>();
        badWords.add("fuck");
        badWords.add("damn");
        badWords.add("shit");
        String[] comment = cmt.getFeedback().split(" ");
        if (cmt.getAuthor().isEmpty() || cmt.getFeedback().isEmpty()) {
            return;
        }
        for (int i = 0; i < comment.length; i++) {
            for (String x : badWords) {
                if (comment[i].equals(x)) {
                    String a = "";
                    for (int j = 0; j < x.length(); j++) {
                        a += "*";
                    }
                    comment[i] = a;
                }
            }
        }
        String ab ="";
        for (String x: comment){
            ab+=x+" ";
        }
        cmt.setFeedback(ab);
        cmtRepository.save(cmt);
    }

    @Override
    public void remove(Cmt cmt) {
        cmtRepository.delete(cmt);
    }
}
