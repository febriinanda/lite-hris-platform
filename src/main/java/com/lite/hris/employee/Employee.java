package com.lite.hris.employee;

import com.lite.hris.config.Audit;
import com.lite.hris.person.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private String employeeNo;
    private LocalDate joinDate;
    private LocalDate resignDate;
    private String resignReason;

    @Embedded
    private Audit audit = new Audit();

    public Employee(EmployeeJoinDTO form) {
        this.person = form.getPerson();
        this.joinDate = form.getJoinDate();
    }

    public void update(EmployeeResignDTO form) {
        this.resignDate = form.getResignDate();
        this.resignReason = form.getReason();
    }

    public void registrationNumber(NumberRegistrationDTO form) {
        this.employeeNo = form.getNumber();
    }
}
