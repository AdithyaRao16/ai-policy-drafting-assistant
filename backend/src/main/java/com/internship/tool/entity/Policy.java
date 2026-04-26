package com.internship.tool.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private String category;
    private String status;
    private String createdBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean isDeleted;

    // Getters and Setters
}