package com.intervest.staysure.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intervest.staysure.database.model.MarginInfo;

@Repository
@Transactional
public interface MarginProtectionRepository extends JpaRepository<MarginInfo, Long>, CrudRepository<MarginInfo, Long> {

    List<MarginInfo> findAll();

}
