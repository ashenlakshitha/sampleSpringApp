package com.intervest.staysure.database.repository;

import com.intervest.staysure.database.model.Quote;
import com.intervest.staysure.database.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kamalabalan on 8/14/15.
 */
@Repository
@Transactional
public interface TestRepository extends JpaRepository<Test, Long>, CrudRepository<Test, Long> {

    List<Test> findAllByOrderByStatusDesc();

}
