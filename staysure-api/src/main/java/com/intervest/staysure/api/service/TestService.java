package com.intervest.staysure.api.service;

import com.intervest.staysure.database.model.Quote;
import com.intervest.staysure.database.model.Test;
import com.intervest.staysure.database.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by charith on 9/4/16.
 */
@Service
@Transactional
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public void saveTest(Test test) {
        testRepository.save(test);
    }

    public List<Test> getAllTests() {
        return testRepository.findAllByOrderByStatusDesc();
    }
}
