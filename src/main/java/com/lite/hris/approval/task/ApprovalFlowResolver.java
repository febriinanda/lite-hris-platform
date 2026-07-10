package com.lite.hris.approval.task;

import com.lite.hris.approval.flow.ApprovalFlowItem;
import com.lite.hris.approval.flow.FlowType;
import com.lite.hris.approval.resolver.ApprovalResolved;
import com.lite.hris.approval.resolver.ApprovalResolver;
import com.lite.hris.employee.Employee;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApprovalFlowResolver {
    private final List<ApprovalResolver> resolvers;
    private Map<FlowType, ApprovalResolver> resolverMap;

    @PostConstruct
    void init(){
        resolverMap = resolvers.stream()
                .collect(Collectors.toMap(
                        ApprovalResolver::getType,
                        Function.identity()));
    }

    public ApprovalResolved resolve(Employee requester, ApprovalFlowItem item){
        return resolverMap.get(item.getType())
                .resolve(requester, item);
    }
}
