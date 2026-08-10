package com.lite.hris.department;

import com.lite.hris.exception.DepartmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository repository;

    public List<DepartmentResponse> findAll(){
        return repository.findAll()
                .stream().map(DepartmentResponse::from).toList();
    }

    public DepartmentResponse findById(long id){
        return DepartmentResponse.from(findDepartment(id));
    }

    private Department findDepartment(long id){
        return repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    public void create(DepartmentDTO form){
        Department d = new Department(form);
        repository.save(d);
    }

    @Transactional
    public void update(long id, DepartmentDTO form){
        Department department = findDepartment(id);
        department.update(form);
    }

    @Transactional
    public void delete(long id){
        Department department = findDepartment(id);
        department.delete();
    }
}
