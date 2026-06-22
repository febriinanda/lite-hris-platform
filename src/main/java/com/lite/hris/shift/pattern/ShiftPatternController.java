package com.lite.hris.shift.pattern;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shift/pattern")
@RequiredArgsConstructor
public class ShiftPatternController {
    private final ShiftPatternRepository patternRepository;
    private final ShiftPatternItemRepository patternItemRepository;

    @GetMapping
    public List<ShiftPattern> findAll(){
        return patternRepository.findAll();
    }

    @GetMapping("/{id}")
    public ShiftPattern findById(@PathVariable long id){
        Optional<ShiftPattern> byId = patternRepository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else throw new RuntimeException("Shift pattern is not found");
    }

    @PostMapping
    public void create(@RequestBody ShiftPatternDTO form){
        ShiftPattern sp = new ShiftPattern(form);
        List<ShiftPatternItem> items = form.getItems().stream().map(o -> new ShiftPatternItem(o, sp)).collect(Collectors.toList());

        patternRepository.save(sp);
        patternItemRepository.saveAll(items);
    }
}
