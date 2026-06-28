package com.lite.hris.employee.attendance;

import com.lite.hris.employee.schedule.EmployeeSchedule;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "employee_attendance")
@NoArgsConstructor
public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private EmployeeSchedule schedule;

    private LocalDate attendanceDate;

    private LocalDateTime clockIn;
    private LocalDateTime clockOut;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

    public EmployeeAttendance(EmployeeSchedule s, VerificationStatus verificationStatus) {
        this.schedule = s;
        this.attendanceDate = s.getScheduleDate();
        this.verificationStatus = verificationStatus;
    }

    public void check(){
        if(this.verificationStatus.equals(VerificationStatus.VERIFIED) || this.verificationStatus.equals(VerificationStatus.AUTO_VERIFIED))
            return;

        if(schedule.isOff()){
            this.status = AttendanceStatus.OFF;
            this.verificationStatus = VerificationStatus.AUTO_VERIFIED;
        }else{
            if(this.clockIn == null && this.clockOut == null)
                this.status = AttendanceStatus.ABSENT;
            else if(this.clockIn == null || this.clockOut == null)
                this.status = AttendanceStatus.INCOMPLETE;
            else{
                if(this.clockIn.isAfter(schedule.getStartDate()))
                    this.status = AttendanceStatus.LATE;
                else {
                    this.status = AttendanceStatus.PRESENT;
                    this.verificationStatus = VerificationStatus.AUTO_VERIFIED;
                }
            }
        }
    }
}
