package com.contest.service;

import com.contest.model.Contest;
import com.contest.repository.ContestRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class ContestService {

  private final ContestRepository contestRepository;

  public List<Contest> getAll() {
    return contestRepository.findAll(Sort.by(ASC, "contestDate"));
  }

  @Transactional
  public List<Contest> saveContest(List<Contest> contestList) {
    contestRepository.saveAll(contestList);
    return contestRepository.findAll();
  }

  public String deleteContestById(Long id) {
    contestRepository.deleteById(id);
    return "delete successfully";
  }

  public List<Contest> getAllByYear(int year) {
    return contestRepository.findAllByYearUniv(year);
  }

  @Transactional
  public List<Contest> saveAll(List<Contest> contest){
     contestRepository.saveAll(contest);
    return contestRepository.findAll();
  }
  public Contest getById(Long id){
    return contestRepository.getById(id);
  }
}
