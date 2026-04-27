package com.internship.tool.service;

import com.internship.tool.entity.Policy;
import com.internship.tool.exception.ResourceNotFoundException;
import com.internship.tool.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

@Service // Business logic layer
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    // CREATE policy (clear cache)
    @CacheEvict(value = {"policies", "policy"}, allEntries = true)
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    // GET all policies (cached)
    @Cacheable(value = "policies")
    public Page<Policy> getAllPolicies(Pageable pageable) {
        System.out.println("Fetching from DB...");
        return policyRepository.findAll(pageable);
        
    }

    // GET policy by ID (cached)
    @Cacheable(value = "policy", key = "#id")
    public Policy getPolicyById(Long id) {
        System.out.println("Fetching from DB...");
        return policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + id));
                
    }

    // DELETE policy (clear cache)
    @CacheEvict(value = {"policies", "policy"}, allEntries = true)
    public void deletePolicy(Long id) {
        Policy policy = getPolicyById(id); // ensures it exists
        policyRepository.delete(policy);
        
    }
}