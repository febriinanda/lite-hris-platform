package com.lite.hris.employee.attendance;

import com.lite.hris.attendance.AttendanceLog;
import com.lite.hris.attendance.AttendanceVerificationRequest;
import com.lite.hris.employee.Employee;
import com.lite.hris.employee.schedule.EmployeeSchedule;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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
    private AttendanceState state;

    @Enumerated(EnumType.STRING)
    private DayType dayType;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

    @Enumerated(EnumType.STRING)
    private AttendanceFollowUp action;

    @ManyToOne
    @JoinColumn(name = "verifier_id")
    private Employee verifiedBy;

    private LocalDateTime verifiedAt;
    private String verificationNote;

    public EmployeeAttendance(EmployeeSchedule s, VerificationStatus verificationStatus) {
        this.schedule = s;
        this.attendanceDate = s.getScheduleDate();
        this.verificationStatus = verificationStatus;
    }

    public void check(){
        if(this.verificationStatus.equals(VerificationStatus.VERIFIED) || this.verificationStatus.equals(VerificationStatus.AUTO_VERIFIED))
            return;

        this.verificationStatus = VerificationStatus.PENDING;
        if(schedule.isOff()){
            this.dayType = DayType.OFF;
            this.verificationStatus = VerificationStatus.AUTO_VERIFIED;
        }else{
            if(this.clockIn == null && this.clockOut == null)
                this.state = AttendanceState.ABSENT;
            else if(this.clockIn == null || this.clockOut == null)
                this.state = AttendanceState.INCOMPLETE;
            else{
                if(this.clockIn.isAfter(schedule.getStartDate()))
                    this.state = AttendanceState.LATE;
                else {
                    this.state = AttendanceState.PRESENT;
                    this.verificationStatus = VerificationStatus.AUTO_VERIFIED;
                }
            }
        }
    }

    public void verified(AttendanceVerificationRequest form) {
        this.verificationStatus = VerificationStatus.VERIFIED;
        this.action = form.getAction();
        this.verifiedBy = form.getVerifiedBy();
        this.verifiedAt = LocalDateTime.now();
        this.verificationNote = form.getNote();
    }

    public void updateClock(List<AttendanceLog> logs) {
        if(this.verificationStatus == VerificationStatus.VERIFIED || this.verificationStatus == VerificationStatus.AUTO_VERIFIED)
            return;

        if(this.schedule.getStartDate() == null || this.schedule.getEndDate() == null)
            return;

        int threshold = 2;
        LocalDateTime min1 = this.schedule.getStartDate().minusHours(threshold);
        LocalDateTime min2 = this.schedule.getStartDate().plusHours(threshold);

        logs.stream().filter(o->min1.isBefore(o.getTime()) && min2.isAfter(o.getTime()))
                .min(Comparator.comparing(AttendanceLog::getTime))
                .ifPresent(o->this.clockIn = o.getTime());

        LocalDateTime max1 = this.schedule.getEndDate().minusHours(threshold);
        LocalDateTime max2 = this.schedule.getEndDate().plusHours(threshold);

        logs.stream().filter(o->max1.isBefore(o.getTime()) && max2.isAfter(o.getTime()))
                .max(Comparator.comparing(AttendanceLog::getTime))
                .ifPresent(o->this.clockOut = o.getTime());
    }
}
