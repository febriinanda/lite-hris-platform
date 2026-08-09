package com.lite.hris.shift.pattern;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftPatternService {
    private final ShiftPatternRepository patternRepository;
    private final ShiftPatternItemRepository patternItemRepository;

    public List<ShiftPattern> findAll(){
        return patternRepository.findAll();
    }

    public ShiftPattern findById(long id){
        Optional<ShiftPattern> byId = patternRepository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else throw new RuntimeException("Shift pattern is not found");
    }

    public void create(ShiftPatternDTO form){
        ShiftPattern sp = new ShiftPattern(form);
        List<ShiftPatternItem> items = form.getItems().stream().map(o -> new ShiftPatternItem(o, sp)).collect(Collectors.toList());

        patternRepository.save(sp);
        patternItemRepository.saveAll(items);
    }
}
