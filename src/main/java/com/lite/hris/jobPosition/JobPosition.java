package com.lite.hris.jobPosition;

import com.lite.hris.config.Audit;
import com.lite.hris.department.Department;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "job_position")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private boolean deleted;

    @Embedded
    private Audit audit = new Audit();

    public JobPosition(JobPositionDTO form) {
        this.title = form.getTitle();
        this.department = form.getDepartment();
    }

    public void update(JobPositionDTO form) {
        this.title = form.getTitle();
        this.department = form.getDepartment();
    }

    public void delete() {
        this.deleted = true;
    }
}
