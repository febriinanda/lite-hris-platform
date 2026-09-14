package com.lite.hris.approval;

import com.lite.hris.approval.task.ApprovalStatus;
import com.lite.hris.approval.task.ApprovalTask;
import com.lite.hris.approval.task.ApprovalTaskRepository;
import com.lite.hris.fact.leave.LeaveFact;
import com.lite.hris.fact.leave.LeaveFactRepository;
import com.lite.hris.request.RequestStatus;
import com.lite.hris.request.RequestType;
import com.lite.hris.request.leave.LeaveRequest;
import com.lite.hris.request.leave.LeaveRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalTaskRepository approvalTaskRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveFactRepository leaveFactRepository;
    public void submit(ApprovalForm form){
        Optional<ApprovalTask> byId = approvalTaskRepository.findById(form.getApprovalTaskId());
        if(byId.isEmpty())
            throw new RuntimeException("This approval task is not found");

        ApprovalTask task = byId.get();
        List<ApprovalTask> approvals = approvalTaskRepository.findByRequestTypeAndRequestId(task.getRequestType(), task.getRequestId());
        Map<Integer, List<ApprovalTask>> approvalMaps = approvals.stream().collect(Collectors.groupingBy(ApprovalTask::getSequence));
        List<ApprovalTask> prevWaves = approvalMaps.getOrDefault(task.getSequence() - 1, new ArrayList<>()).stream()
                .filter(o -> o.getStatus().equals(ApprovalStatus.APPROVED))
                .toList();

        checkPreviousWaves(prevWaves);

        if(form.getStatus() == ApprovalStatus.REJECTED){
            rejected(task, form);
        }

        if(form.getStatus().equals(ApprovalStatus.APPROVED)){
            approved(task, form, approvalMaps);
        }
    }

    private void checkPreviousWaves(List<ApprovalTask> prevWaves) {
        int minimum = prevWaves.stream()
                .map(ApprovalTask::getMinimumApprovalThisSequence)
                .min(Comparator.comparing(o -> o)).orElse(0);

        if(prevWaves.size() < minimum){
            throw new RuntimeException("Last wave of approval is not approved properly");
        }
    }

    private void approved(ApprovalTask task, ApprovalForm form, Map<Integer, List<ApprovalTask>> approvalMaps) {
        task.setStatus(form.getStatus());
        approvalTaskRepository.save(task);

        List<ApprovalTask> currentWaves = approvalMaps.getOrDefault(task.getSequence(), new ArrayList<>())
                .stream()
                .filter(o -> o.getStatus().equals(ApprovalStatus.APPROVED))
                .toList();

        int minimum = currentWaves.stream().map(ApprovalTask::getMinimumApprovalThisSequence)
                .min(Comparator.comparing(o -> o))
                .orElse(0);

        if(currentWaves.size()>=minimum){
            List<ApprovalTask> nextWaves = approvalMaps.getOrDefault(task.getSequence() + 1, new ArrayList<>());
            if(nextWaves.isEmpty()){
                if(task.getRequestType() == RequestType.LEAVE_TYPE){
                    leave(task);
                }
            }
        }
    }

    private void leave(ApprovalTask task) {
        Optional<LeaveRequest> requestOptional = leaveRequestRepository.findById(task.getRequestId());
        if(requestOptional.isPresent()){
            LeaveRequest request = requestOptional.get();
            request.setStatus(RequestStatus.APPROVED);
            leaveRequestRepository.save(request);

            LocalDate start = request.getStartDate();
            List<LeaveFact> facts = new ArrayList<>();
            while(!start.isAfter(request.getEndDate())){
                facts.add(new LeaveFact(request,start));
                start = start.plusDays(1);
            }

            leaveFactRepository.saveAll(facts);
        }
    }

    private void rejected(ApprovalTask task, ApprovalForm form) {
        task.setStatus(form.getStatus());
        approvalTaskRepository.save(task);

        if(task.getRequestType() == RequestType.LEAVE_TYPE){
            Optional<LeaveRequest> requestOptional = leaveRequestRepository.findById(task.getRequestId());
            if(requestOptional.isPresent()){
                LeaveRequest request = requestOptional.get();
                request.setStatus(RequestStatus.REJECTED);
                leaveRequestRepository.save(request);
            }
        }
    }
}
