package com.example.MiniProject.repository;

import java.sql.Date;
import java.util.*;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MiniProject.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findCustomerByEmail(String email);

	@Query(nativeQuery = true, value = "SELECT * FROM CUSTOMER INNER JOIN SIM_CARD_INVENTORY ON CUSTOMER.CUSTOMER_ID = SIM_CARD_INVENTORY.CUSTOMER_ID WHERE SIM_CARD_INVENTORY.PLAN_VALIDITY_DATE <= TO_DATE(:dateData,'YYYY-MM-DD')"
			+ "")
	List<Customer> findByLastDays(@Param("dateData") String dateDate);

}
