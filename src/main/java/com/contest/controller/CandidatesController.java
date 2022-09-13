package com.contest.controller;

import com.contest.model.mapper.CandidatesMapper;
import com.contest.model.mapper.rest.Candidates;
import com.contest.service.CandidatesService;
import com.contest.service.ConditionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/candidates")
@CrossOrigin("http://localhost:3000")
public class CandidatesController {
    private CandidatesService service;
    private CandidatesMapper candidatesMapper;

    private ConditionService conditionService;

  @GetMapping
  public List<Candidates> getAllCandidates(
          @RequestParam(value = "page") int page,
          @RequestParam(value = "page_size") int pageSize
  ) {
    return service.getAllCandidates(page, pageSize)
            .stream().map(candidatesMapper::toRestCandidates)
            .toList()
            ;
  }

  @GetMapping("/admitted")
  public List<Candidates> getAllAdmitted(
          @RequestParam(value = "page", required = false) int page,
          @RequestParam(value = "page_size", required = false) int pageSize
  ) {
    Double minAdmin = conditionService.getById(1L).get().getMin_admin();
    Double minMath = conditionService.getById(1L).get().getMin_Math();
    return service.getAllAdmitted(minAdmin, minMath , page, pageSize).stream()
            .map(candidatesMapper::toRestCandidates)
            .toList();
  }

  @GetMapping("/standby")
  public List<Candidates> getAllInStandBy() {
    Double minAdmin = conditionService.getById(1L).get().getMin_admin();
    Double minMath = conditionService.getById(1L).get().getMin_Math();
    Double minList = conditionService.getById(1L).get().getMin_list();
    return service.getAllInStandBy(minAdmin, minMath, minList).stream()
            .map(candidatesMapper::toRestCandidates)
            .toList();
  }

    @GetMapping("/")
    public List<com.contest.model.Candidates> getAll(){
        return service.getAll();
    }

  @GetMapping("/nonadmitted")
  public List<Candidates> getAllNonAdmitted(
          @RequestParam(value = "page", required = false) int page,
          @RequestParam(value = "page_size", required = false) int pageSize
  ) {
    Double minList = conditionService.getById(1L).get().getMin_list();
    return service.getAllNonAdmitted(minList, page, pageSize).stream()
            .map(candidatesMapper::toRestCandidates)
            .toList();
  }
  @GetMapping("/{year}")
  public List<Candidates> getAllByYear(
          @RequestParam (value = "page") int page ,
          @RequestParam (value = "page_size") int page_size,
          @PathVariable Long year
  ){
    return service.findAllByYearUniv(Integer.parseInt(String.valueOf(year)) , page , page_size)
            .stream().map(candidatesMapper::toRestCandidates)
            .toList();
  }
  @GetMapping("/date/{date}")
  public com.contest.model.Candidates getAllByYear(
          @PathVariable Long date
  ){
    return service.findAllByContestId(Integer.parseInt(String.valueOf(date)));
  }
}
