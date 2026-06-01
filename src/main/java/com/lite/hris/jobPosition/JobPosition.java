package com.lite.hris.jobPosition;

import com.lite.hris.department.Department;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "job_position")
@Data
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
