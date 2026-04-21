package com.internship.tool.service;

import com.internship.tool.entity.Policy;
import com.internship.tool.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as service layer (business logic)
public class PolicyService {

    @Autowired // Injects PolicyRepository dependency
    private PolicyRepository policyRepository;

    // Save a new policy
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    // Get all policies
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    // Get policy by ID
    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id).orElse(null);
    }

    // Delete policy by ID
    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);
    }
}