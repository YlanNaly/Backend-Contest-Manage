package com.contest.service;

import com.contest.model.YearUniv;
import com.contest.repository.YearUnivRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class YearUnivService {

  private final YearUnivRepository yearUnivRepository;

  public List<YearUniv> getAll(
          int page,
          int pageSize
  ){
    Pageable pageable = PageRequest.of(page, pageSize );
    return yearUnivRepository.findAll(pageable).toList();
  }

  @Transactional
  public List<YearUniv> saveYearUniv(YearUniv yearUniv) {
     yearUnivRepository.save(yearUniv);
    return yearUnivRepository.findAll();
  }

  public YearUniv getById(Long id){
    return yearUnivRepository.getById(id);
  }
}
