package com.lite.hris.company;

import com.lite.hris.exception.CompanyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository repository;

    public List<CompanyResponse> findAll(){
        return repository.findAll()
                .stream().map(CompanyResponse::from)
                .toList();
    }

    public CompanyResponse findById(long id){
        return CompanyResponse.from(findCompany(id));
    }

    private Company findCompany(long id){
        return repository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @Transactional
    public void create(CompanyDTO form){
        Company c = new Company(form);
        repository.save(c);
    }

    @Transactional
    public void update(long id, CompanyDTO form){
        Company company = findCompany(id);
        company.update(form);
    }

    @Transactional
    public void delete(long id){
        Company company = findCompany(id);
        company.delete();
    }
}
