package com.contest.controller;

import com.contest.model.mapper.rest.Contest;
import com.contest.model.mapper.ContestMapper;
import com.contest.model.mapper.rest.ContestPost;
import com.contest.service.ContestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/contest")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class ContestController {

  private final ContestService contestService;
  private final ContestMapper contestMapper;
  @GetMapping
  public @ResponseBody List<com.contest.model.Contest> getAllContest() {
    return contestService.getAll();
  }

  @PutMapping
  public @ResponseBody List<com.contest.model.Contest> saveContest (
          @RequestBody List<Contest> contest
          ) {
    return contestService.saveContest(contestMapper.toDomainList(contest));
  }

  @GetMapping("/{year}")
  public List<com.contest.model.Contest> getByYear(
          @PathVariable Long year
  ){
    return contestService.getAllByYear(Integer.parseInt(String.valueOf(year)));
  }

  @PostMapping
  public List<com.contest.model.Contest> saveAllContest(
          @RequestBody List<ContestPost> toCreate
  ){
  return contestService.saveAll(contestMapper.toDomainPostList(toCreate));
  }
  @DeleteMapping("/{id}")
  public String deleteContest(@PathVariable Long id){
    return contestService.deleteContestById(id);
  }
}
