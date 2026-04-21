package com.internship.tool.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // Marks this class as a JPA entity (maps to DB table)
@Table(name = "policies") // Specifies table name in database
@EntityListeners(AuditingEntityListener.class) // Enables auditing (auto timestamps)
@Data // Lombok: generates getters, setters, toString, etc.
@NoArgsConstructor // Lombok: no-args constructor
@AllArgsConstructor // Lombok: all-args constructor
@Builder // Lombok: builder pattern
public class Policy {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false) // Column cannot be null
    private String title;

    @Column(columnDefinition = "TEXT") // Stores large text
    private String description;

    @Column // Default column mapping
    private String category;

    @Column // Default column mapping
    private String status;

    @CreatedDate // Automatically sets creation timestamp
    @Column(updatable = false) // Prevents update after creation
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updates timestamp on modification
    private LocalDateTime updatedAt;
}