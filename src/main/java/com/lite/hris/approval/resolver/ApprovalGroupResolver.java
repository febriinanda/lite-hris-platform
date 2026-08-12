package com.lite.hris.approval.resolver;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.FlowType;
import com.lite.hris.approval.group.*;
import com.lite.hris.approval.group.resolver.ApprovalCountResolver;
import com.lite.hris.employee.Employee;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApprovalGroupResolver implements ApprovalResolver{
    private final ApprovalGroupRepository approvalGroupRepository;
    private final List<ApprovalCountResolver> resolvers;
    private Map<ApprovalMode, ApprovalCountResolver> resolverMap;

    @PostConstruct
    void init(){
        resolverMap = resolvers.stream()
                .collect(Collectors.toMap(
                        ApprovalCountResolver::getMode,
                        Function.identity()
                ));
    }
    @Override
    public FlowType getType() {
        return FlowType.GROUP;
    }

    @Override
    public ApprovalResolved resolve(Employee requester, ApprovalFlowItem item) {
        ApprovalResolved r = new ApprovalResolved();
        Optional<ApprovalGroup> byId = approvalGroupRepository.findById(item.getReferenceId());
        if(byId.isPresent()){
            ApprovalGroup group = byId.get();
            int minimumApproval = resolverMap.get(group.getMode())
                    .resolve(group);

            r.setMinimumApproval(minimumApproval);
        }
        return r;
    }
}
