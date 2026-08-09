package com.lite.hris.shift.pattern;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift/pattern")
@RequiredArgsConstructor
public class ShiftPatternController {
    private final ShiftPatternService patternService;

    @GetMapping
    public List<ShiftPattern> findAll(){
        return patternService.findAll();
    }

    @GetMapping("/{id}")
    public ShiftPattern findById(@PathVariable long id){
        return patternService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody ShiftPatternDTO form){
        patternService.create(form);
    }
}
