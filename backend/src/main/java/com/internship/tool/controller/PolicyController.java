package com.internship.tool.controller;

import com.internship.tool.entity.Policy;
import com.internship.tool.repository.PolicyRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    private final PolicyRepository policyRepository;

    public PolicyController(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    // ✅ CREATE POLICY
    @PostMapping
    public Policy createPolicy(@RequestBody Policy policy) {
        policy.setIsDeleted(false);
        policy.setCreatedAt(LocalDateTime.now());
        return policyRepository.save(policy);
    }

    // ✅ GET ALL (ONLY ACTIVE)
    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyRepository.findByIsDeletedFalse();
    }

    // ✅ UPDATE POLICY
    @PutMapping("/{id}")
    public Policy updatePolicy(@PathVariable UUID id,
                                @RequestBody Policy updatedPolicy) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setTitle(updatedPolicy.getTitle());
        policy.setDescription(updatedPolicy.getDescription());
        policy.setCategory(updatedPolicy.getCategory());
        policy.setStatus(updatedPolicy.getStatus());
        policy.setUpdatedAt(LocalDateTime.now());

        return policyRepository.save(policy);
    }

    // ✅ SOFT DELETE
    @DeleteMapping("/{id}")
    public String deletePolicy(@PathVariable UUID id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setIsDeleted(true);
        policyRepository.save(policy);

        return "Policy soft deleted successfully";
    }

    // ✅ SEARCH
    @GetMapping("/search")
    public List<Policy> searchPolicies(@RequestParam String q) {
        return policyRepository.searchPolicies(q);
    }

    // ✅ STATS (FIXED)
    @GetMapping("/stats")
    public Map<String, Object> getStats() {

        List<Policy> activePolicies = policyRepository.findByIsDeletedFalse();
        List<Policy> allPolicies = policyRepository.findAll();

        long total = activePolicies.size();
        long active = activePolicies.stream()
                .filter(p -> "ACTIVE".equalsIgnoreCase(p.getStatus()))
                .count();

        long draft = activePolicies.stream()
                .filter(p -> "DRAFT".equalsIgnoreCase(p.getStatus()))
                .count();

        long deleted = allPolicies.stream()
                .filter(p -> Boolean.TRUE.equals(p.getIsDeleted()))
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("active", active);
        stats.put("draft", draft);
        stats.put("deleted", deleted);

        return stats;
    }
}