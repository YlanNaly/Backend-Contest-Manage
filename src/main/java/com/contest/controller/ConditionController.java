package com.contest.controller;

import com.contest.model.Conditions;
import com.contest.service.ConditionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
@RequestMapping("/conditions")
public class ConditionController {
    private ConditionService conditionService;

    @PutMapping
   public String updateCondition(@RequestBody Conditions conditions){
        conditionService.SavingChange(conditions);
        return "method successfully attempt";
    }

}
