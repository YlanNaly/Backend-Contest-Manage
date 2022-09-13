package com.contest.service;

import com.contest.repository.ConditionsRepository;
import com.contest.model.Conditions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConditionService {

  private ConditionsRepository conditionsRepository;
  @Transactional
  public void SavingChange(Conditions conditions){
       Optional<Conditions> conditions1 = conditionsRepository.findById(1L);
       if(conditions1.isPresent()){
           conditions1.get().setMin_list(conditions.getMin_list());
           conditions1.get().setMin_Math(conditions.getMin_Math());
           conditions1.get().setMin_admin(conditions.getMin_admin());
           conditionsRepository.save(conditions1.get());
       }
       else {
           conditionsRepository.save(new Conditions(conditions.getMin_admin(), conditions.getMin_list(), conditions.getMin_admin()));
       }
    }
    public Optional<Conditions> getById(Long id){
      return conditionsRepository.findById(id);
    }
}
