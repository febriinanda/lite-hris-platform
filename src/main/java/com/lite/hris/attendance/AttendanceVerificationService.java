package com.lite.hris.attendance;

import com.lite.hris.employee.attendance.*;
import com.lite.hris.employee.leave.grant.EmployeeLeaveGrant;
import com.lite.hris.employee.leave.grant.EmployeeLeaveGrantRepository;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransaction;
import com.lite.hris.employee.leave.transaction.EmployeeLeaveTransactionRepository;
import com.lite.hris.employee.leave.transaction.LeaveReferenceType;
import com.lite.hris.employee.leave.transaction.LeaveTransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceVerificationService {
    private final EmployeeAttendanceRepository employeeAttendanceRepository;
    private final EmployeeLeaveGrantRepository employeeLeaveGrantRepository;
    private final EmployeeLeaveTransactionRepository employeeLeaveTransactionRepository;
    public void verify(long id, AttendanceVerificationRequest form){
        Optional<EmployeeAttendance> byId = employeeAttendanceRepository.findById(id);
        if(byId.isPresent()){
            EmployeeAttendance a = byId.get();
            a.verified(form);

            if(a.getVerificationStatus() == VerificationStatus.VERIFIED || a.getVerificationStatus() == VerificationStatus.AUTO_VERIFIED){
                if(a.getAction() == AttendanceFollowUp.LEAVE_DEDUCTION){
                    leaveDeduction(a);
                }
            }
            employeeAttendanceRepository.save(a);


        }else throw new RuntimeException("This attendance is not exist");
    }

    private void leaveDeduction(EmployeeAttendance a) {
        Optional<EmployeeLeaveGrant> current = currentLeaveGrant(a);

        EmployeeLeaveTransaction t = new EmployeeLeaveTransaction();
        t.setReferenceType(LeaveReferenceType.ATTENDANCE);
        t.setReferenceId(a.getId());
        t.setEmployee(a.getSchedule().getEmployee());
        t.setAmount(-1);
        t.setTransactionType(defineTransactionType(a));

        if(current.isPresent()){
            EmployeeLeaveGrant grant = current.get();
            t.setGrant(grant);
            grant.setRemainingDays(grant.getRemainingDays() + t.getAmount());
            employeeLeaveGrantRepository.save(grant);
        }

        employeeLeaveTransactionRepository.save(t);
    }

    private LeaveTransactionType defineTransactionType(EmployeeAttendance a){
        LeaveTransactionType type = null;
        if(a.getState() == AttendanceState.ABSENT || a.getState() == AttendanceState.INCOMPLETE)
            type = LeaveTransactionType.ABSENCE_CONVERSION;
        else if(a.getDayType() == DayType.LEAVE)
            type = LeaveTransactionType.LEAVE_APPROVED;

        return type;
    }

    private Optional<EmployeeLeaveGrant> currentLeaveGrant(EmployeeAttendance a){
        return employeeLeaveGrantRepository.findByEmployee(a.getSchedule().getEmployee()).stream().filter(
                o -> o.getEarnedDate().isBefore(a.getAttendanceDate()) && o.getFinalExpireDate().isAfter(a.getAttendanceDate())
        ).min(Comparator.comparing(EmployeeLeaveGrant::getFinalExpireDate));
    }
}
