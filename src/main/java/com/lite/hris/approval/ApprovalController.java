package com.lite.hris.approval;

import com.lite.hris.approval.task.ApprovalStatus;
import com.lite.hris.approval.task.ApprovalTask;
import com.lite.hris.approval.task.ApprovalTaskRepository;
import com.lite.hris.request.RequestStatus;
import com.lite.hris.request.RequestType;
import com.lite.hris.request.leave.LeaveRequest;
import com.lite.hris.request.leave.LeaveRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {
    private final ApprovalTaskRepository approvalTaskRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    @PostMapping
    public void submit(@RequestBody ApprovalForm form){
        Optional<ApprovalTask> byId = approvalTaskRepository.findById(form.getApprovalTaskId());
        if(byId.isEmpty())
            throw new RuntimeException("This approval task is not found");

        ApprovalTask task = byId.get();
        List<ApprovalTask> approvals = approvalTaskRepository.findByRequestTypeAndRequestId(task.getRequestType(), task.getRequestId());
        Map<Integer, List<ApprovalTask>> approvalMaps = approvals.stream().collect(Collectors.groupingBy(ApprovalTask::getSequence));
        List<ApprovalTask> prevWaves = approvalMaps.getOrDefault(task.getSequence() - 1, new ArrayList<>()).stream()
                .filter(o -> o.getStatus().equals(ApprovalStatus.APPROVED))
                .toList();

        int minimum = prevWaves.stream()
                .map(ApprovalTask::getMinimumApprovalThisSequence)
                .min(Comparator.comparing(o -> o)).orElse(0);

        if(prevWaves.size() < minimum){
            throw new RuntimeException("Last wave of approval is not approved properly");
        }

        if(form.getStatus().equals(ApprovalStatus.REJECTED)){
            task.setStatus(form.getStatus());
            approvalTaskRepository.save(task);

            if(task.getRequestType().equals(RequestType.LEAVE_TYPE)){
                Optional<LeaveRequest> requestOptional = leaveRequestRepository.findById(task.getRequestId());
                if(requestOptional.isPresent()){
                    LeaveRequest request = requestOptional.get();
                    request.setStatus(RequestStatus.REJECTED);
                    leaveRequestRepository.save(request);
                }
            }
        }

        if(form.getStatus().equals(ApprovalStatus.APPROVED)){
            task.setStatus(form.getStatus());
            approvalTaskRepository.save(task);

            List<ApprovalTask> currentWaves = approvalMaps.getOrDefault(task.getSequence(), new ArrayList<>())
                    .stream()
                    .filter(o -> o.getStatus().equals(ApprovalStatus.APPROVED))
                    .toList();

            minimum = currentWaves.stream().map(ApprovalTask::getMinimumApprovalThisSequence)
                    .min(Comparator.comparing(o -> o))
                    .orElse(0);

            if(currentWaves.size()>=minimum){
                List<ApprovalTask> nextWaves = approvalMaps.getOrDefault(task.getSequence() + 1, new ArrayList<>());
                if(nextWaves.isEmpty()){
                    if(task.getRequestType().equals(RequestType.LEAVE_TYPE)){
                        Optional<LeaveRequest> requestOptional = leaveRequestRepository.findById(task.getRequestId());
                        if(requestOptional.isPresent()){
                            LeaveRequest request = requestOptional.get();
                            request.setStatus(RequestStatus.APPROVED);
                            leaveRequestRepository.save(request);
                        }
                    }
                }
            }
        }
    }
}
