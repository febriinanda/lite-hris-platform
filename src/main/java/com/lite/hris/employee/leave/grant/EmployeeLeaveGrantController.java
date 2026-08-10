package com.lite.hris.employee.leave.grant;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeLeaveGrantController {
    private final EmployeeLeaveGrantService service;

    @PostMapping("/{id}/leave/grant")
    public void grant(@PathVariable long id, @RequestBody LeaveBalanceGrantRequest form){
        service.grant(id, form);
    }
}
