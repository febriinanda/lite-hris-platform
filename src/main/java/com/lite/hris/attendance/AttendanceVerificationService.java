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

import java.time.LocalDateTime;
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
                    Optional<EmployeeLeaveGrant> min = employeeLeaveGrantRepository.findByEmployee(a.getSchedule().getEmployee()).stream().filter(
                            o -> o.getEarnedDate().isBefore(a.getAttendanceDate()) && o.getFinalExpireDate().isAfter(a.getAttendanceDate())
                    ).min(Comparator.comparing(EmployeeLeaveGrant::getFinalExpireDate));

                    EmployeeLeaveTransaction t = new EmployeeLeaveTransaction();
                    t.setCreatedAt(LocalDateTime.now());
                    t.setReferenceType(LeaveReferenceType.ATTENDANCE);
                    t.setReferenceId(a.getId());
                    t.setEmployee(a.getSchedule().getEmployee());
                    t.setAmount(-1);
                    LeaveTransactionType type = null;
                    if(a.getState() == AttendanceState.ABSENT || a.getState() == AttendanceState.INCOMPLETE)
                        type = LeaveTransactionType.ABSENCE_CONVERSION;
                    else if(a.getDayType() == DayType.LEAVE)
                        type = LeaveTransactionType.LEAVE_APPROVED;
                    t.setTransactionType(type);
                    t.setCreatedBy(form.getVerifiedBy().getPerson().getName());

                    if(min.isPresent()){
                        EmployeeLeaveGrant grant = min.get();
                        t.setGrant(grant);
                        grant.setRemainingDays(grant.getRemainingDays() + t.getAmount());
                        employeeLeaveGrantRepository.save(grant);
                    }

                    employeeLeaveTransactionRepository.save(t);
                }
            }
            employeeAttendanceRepository.save(a);


        }else throw new RuntimeException("This attendance is not exist");
    }
}
