package com.example.MiniProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MiniProject.model.MobilePlans;

@Repository
public interface MobilePlansRepository extends JpaRepository<MobilePlans, Integer> {

}
