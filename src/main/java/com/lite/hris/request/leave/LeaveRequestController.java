package com.lite.hris.request.leave;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leave/request")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestService service;

    @PostMapping
    public void submit(@RequestBody LeaveRequestForm form){
        service.submit(form);
    }
}
