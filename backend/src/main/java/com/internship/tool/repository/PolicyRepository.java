package com.internship.tool.repository;

import com.internship.tool.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PolicyRepository extends JpaRepository<Policy, UUID> {

    // ✅ Only non-deleted policies
    List<Policy> findByIsDeletedFalse();

    // ✅ Search (your existing method assumed)
    @Query("SELECT p FROM Policy p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Policy> searchPolicies(String q);
}