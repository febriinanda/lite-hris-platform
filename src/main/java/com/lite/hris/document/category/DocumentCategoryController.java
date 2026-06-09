package com.lite.hris.document.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/document/category")
@RequiredArgsConstructor
public class DocumentCategoryController {
    private final DocumentCategoryRepository repository;

    @GetMapping
    public List<DocumentCategory> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public DocumentCategory findById(@PathVariable long id){
        Optional<DocumentCategory> byId = repository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else throw new RuntimeException("Document category is not found");
    }

    @PostMapping
    public void create(@RequestBody DocumentCategoryDTO form){
        DocumentCategory dc = new DocumentCategory(form);
        repository.save(dc);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody DocumentCategoryDTO form){
        Optional<DocumentCategory> byId = repository.findById(id);
        if(byId.isPresent()){
            DocumentCategory existed = byId.get();
            existed.update(form);
            repository.save(existed);
        }else throw new RuntimeException("Document category is not found");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        Optional<DocumentCategory> byId = repository.findById(id);
        if(byId.isPresent()){
            DocumentCategory existed = byId.get();
            existed.setDeleted(true);
            repository.save(existed);
        }else throw new RuntimeException("Document category is not found");
    }
}
