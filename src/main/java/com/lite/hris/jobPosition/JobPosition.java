package com.lite.hris.jobPosition;

import com.lite.hris.department.Department;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_position")
@Data
@NoArgsConstructor
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private boolean deleted;

    public JobPosition(JobPositionDTO form) {
        this.title = form.getTitle();
        this.department = form.getDepartment();
    }

    public void update(JobPositionDTO form) {
        this.title = form.getTitle();
        this.department = form.getDepartment();
    }
}
