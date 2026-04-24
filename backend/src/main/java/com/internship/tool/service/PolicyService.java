package com.internship.tool.service;

import com.internship.tool.entity.Policy;
import com.internship.tool.exception.ResourceNotFoundException;
import com.internship.tool.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@Service // Business logic layer
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    // Create policy
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    // Get all policies
    public Page<Policy> getAllPolicies(Pageable pageable) {
    return policyRepository.findAll(pageable);
    }

    // Get policy by ID (with exception handling)
    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + id));
    }

    // Delete policy (safe delete)
    public void deletePolicy(Long id) {
        Policy policy = getPolicyById(id); // ensures it exists
        policyRepository.delete(policy);
    }
}