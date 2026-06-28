package com.lite.hris.employee.schedule;

import com.lite.hris.employee.Employee;
import com.lite.hris.employee.EmployeeRepository;
import com.lite.hris.employee.shiftPattern.EmployeeShiftPattern;
import com.lite.hris.employee.shiftPattern.EmployeeShiftPatternRepository;
import com.lite.hris.shift.pattern.ShiftPatternItem;
import com.lite.hris.shift.pattern.ShiftPatternItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeScheduleController {
    private final EmployeeRepository repository;
    private final EmployeeShiftPatternRepository shiftPatternRepository;
    private final ShiftPatternItemRepository shiftPatternItemRepository;
    private final EmployeeScheduleRepository scheduleRepository;

    @GetMapping("/{id}/schedule")
    public List<EmployeeSchedule> getSchedules(
            @PathVariable long id,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            return scheduleRepository.findByEmployeeAndScheduleDateBetween(byId.get(), start, end);
        }else throw new RuntimeException("This employee is not found");
    }

    @PostMapping("/{id}/schedule/generate")
    public void generate(@PathVariable long id, @RequestBody GenerateShiftDTO form){
        Optional<Employee> byId = repository.findById(id);
        if(byId.isPresent()){
            Employee employee = byId.get();
            List<EmployeeSchedule> schedules = new ArrayList<>();
            List<EmployeeShiftPattern> patterns = shiftPatternRepository.findByEmployee(employee);
            for (EmployeeShiftPattern pattern : patterns) {
                if(pattern.eligible(form.getStartDate(), form.getEndDate())){
                    Map<Integer, ShiftPatternItem> map = shiftPatternItemRepository.findByPattern(pattern.getPattern()).stream().collect(Collectors.toMap(ShiftPatternItem::getSequence, Function.identity()));
                    List<EmployeeSchedule> empties = pattern.generateEmptySchedules(employee, form.getStartDate(), form.getEndDate());
                    for (EmployeeSchedule empty : empties) {
                        long between = ChronoUnit.DAYS.between(pattern.getEffectiveDate(), empty.getScheduleDate());
                        int seq = (int)(between+pattern.getStartSequence()) % pattern.getPattern().getCycleLength();
                        ShiftPatternItem item = map.get(seq);
                        if(item != null){
                            empty.setup(item.getShift());
                        }else{
                            empty.setOff(true);
                        }

                        schedules.add(empty);
                    }
                }
            }

            scheduleRepository.saveAll(schedules);
        }else throw new RuntimeException("This employee is not found");
    }
}
