package com.lite.hris.request.leave;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.ApprovalFlowItemRepository;
import com.lite.hris.approval.resolver.ApprovalResolved;
import com.lite.hris.approval.task.ApprovalFlowResolver;
import com.lite.hris.approval.task.ApprovalStatus;
import com.lite.hris.approval.task.ApprovalTask;
import com.lite.hris.approval.task.ApprovalTaskRepository;
import com.lite.hris.employee.Employee;
import com.lite.hris.request.RequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    private final LeaveTypeRepository leaveTypeRepository;
    private final ApprovalFlowItemRepository approvalFlowItemRepository;
    private final ApprovalFlowResolver approvalFlowResolver;
    private final LeaveRequestRepository leaveRequestRepository;
    private final ApprovalTaskRepository approvalTaskRepository;
    public void submit(LeaveRequestForm form){
        LeaveType leaveType = validate(form);
        Employee requester = form.getRequester();
        LeaveRequest request = new LeaveRequest(form);
        List<ApprovalTask> tasks = generateApprovalTasks(request, requester, leaveType);
        if(tasks.isEmpty())
            throw new RuntimeException("Request is invalid, 0 Approval for this request");

        leaveRequestRepository.save(request);
        approvalTaskRepository.saveAll(tasks);
    }

    private List<ApprovalTask> generateApprovalTasks(LeaveRequest request, Employee requester, LeaveType leaveType) {
        List<ApprovalTask> tasks = new ArrayList<>();
        List<ApprovalFlowItem> items = approvalFlowItemRepository.findByHeader(leaveType.getApprovalFlow());
        int seq = 1;
        for (ApprovalFlowItem item : items){
            ApprovalResolved resolved = approvalFlowResolver.resolve(requester, item);
            for (Employee e : resolved.getEmployees()) {
                ApprovalTask t = new ApprovalTask();
                t.setSequence(seq);
                t.setRequestType(RequestType.LEAVE_TYPE);
                t.setRequestId(request.getId());
                t.setEmployee(e);
                t.setStatus(ApprovalStatus.WAITING);
                t.setMinimumApprovalThisSequence(resolved.getMinimumApproval());
                tasks.add(t);
            }

            if(!resolved.getEmployees().isEmpty())
                seq++;
        }
        return tasks;
    }

    private LeaveType validate(LeaveRequestForm form){
        Optional<LeaveType> byId = leaveTypeRepository.findById(form.getType().getId());
        if(byId.isEmpty())
            throw new RuntimeException("This leave type is not recognize");

        return byId.get();
    }
}
