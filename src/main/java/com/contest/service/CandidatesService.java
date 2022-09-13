package com.contest.service;

import com.contest.repository.CandidatesRepository;
import com.contest.model.Candidates;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class CandidatesService {
    private CandidatesRepository repository;
  @Transactional
  public List<Candidates> getAllCandidates(int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return repository.findAll(pageable).toList();
  }

  public List<Candidates> getAllAdmitted(Double minAdmin, Double minMath  ,int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return repository.findAllAdmittedCandidates(minAdmin, minMath , pageable);
  }

  public List<Candidates> getAllNonAdmitted(Double minList, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return repository.findAllNonAdmitted(minList, pageable);
  }

  public List<Candidates> getAllInStandBy(Double minAdmin, Double minMath, Double minList) {
    return repository.findAllInStandBy(minAdmin, minMath, minList);
  }
  public List<Candidates> getAll(){
        return repository.findAll();
    }

  public List<Candidates> findAllByYearUniv (int year , int page , int page_size) {
    Pageable pageable = PageRequest.of(page,page_size);
    return repository.findAllByYearUniv((year) , pageable);
  }
  public Candidates findAllByContestId (int contest ) {
    return repository.findAllByContest(contest);
  }
}
