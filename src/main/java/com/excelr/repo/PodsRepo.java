package com.excelr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.Pods;
@Repository
public interface PodsRepo extends JpaRepository<Pods, Integer> {

}
