package com.example.MiniProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MiniProject.model.SIMCardInventory;

@Repository
public interface SIMCardInventoryRepository extends JpaRepository<SIMCardInventory, Long> {

	SIMCardInventory findFirstByStatus(String status);

	SIMCardInventory findByCustomerId(Integer customerId);

	long countByStatus(String status);

	boolean existsByTelephoneNumber(long telephoneNumber);

	@Query(value = "select alpha_seq from dual connect by level<1", nativeQuery = true)
	String getNextSeriesSimCardNumber();
}
