package com.internship.tool.repository;

import com.internship.tool.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, UUID> {

    // Search
    @Query("SELECT p FROM Policy p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Policy> searchPolicies(String keyword);

    // Filter by status
    List<Policy> findByStatus(String status);

    // Filter by category
    List<Policy> findByCategory(String category);

    // Date range
    @Query("SELECT p FROM Policy p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    List<Policy> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}