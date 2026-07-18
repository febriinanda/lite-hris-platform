package com.lite.hris.attendance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceClockService {
    private final AttendanceLogRepository attendanceLogRepository;
    public AttendanceLog clock(AttendanceClockRequest form){
        AttendanceLog log = new AttendanceLog(form);
        return attendanceLogRepository.save(log);
    }
}
