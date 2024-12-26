package com.excelr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.Smartwatches;
@Repository
public interface SmartwatchesRepo extends JpaRepository<Smartwatches, Integer> {

}
