package com.internship.tool.controller;

import com.internship.tool.entity.Policy;
import com.internship.tool.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as REST API controller
@RequestMapping("/api/policies") // Base URL for all endpoints
public class PolicyController {

    @Autowired // Injects PolicyService
    private PolicyService policyService;

    // Create a new policy
    @PostMapping
    public Policy createPolicy(@RequestBody Policy policy) {
        return policyService.createPolicy(policy);
    }

    // Get all policies
    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    // Get policy by ID
    @GetMapping("/{id}")
    public Policy getPolicyById(@PathVariable Long id) {
        return policyService.getPolicyById(id);
    }

    // Delete policy by ID
    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
    }
}