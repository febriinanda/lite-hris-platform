package com.lite.hris.attendance;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final ApplicationEventPublisher publisher;
    private final AttendanceVerificationService attendanceVerificationService;
    private final AttendanceProcessService attendanceProcessService;
    private final AttendanceClockService attendanceClockService;

    @PostMapping("/process")
    public void process(@RequestBody AttendanceProcessRequest form){
        attendanceProcessService.process(form);
    }

    @PatchMapping("/{id}/verify")
    public void verify(@PathVariable long id, @RequestBody AttendanceVerificationRequest form){
        attendanceVerificationService.verify(id, form);
    }

    @PostMapping("/clock")
    public void clock(@RequestBody AttendanceClockRequest form){
        AttendanceLog log = attendanceClockService.clock(form);
        publisher.publishEvent(new AttendanceChangedEvent(log.getEmployee().getId(), log.getTime().toLocalDate()));
    }
}
