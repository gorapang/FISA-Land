package com.fisa.land.fisaland.lending.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.lending.entity.LendingRecords;

@Repository
public interface LendingRecordRepository extends JpaRepository<LendingRecords, Long> {
	
    List<LendingRecords> findByBorrowerId(Long borrowerId);
    
    List<LendingRecords> findByOwnerId(Long ownerId);
}