package com.contest.model.mapper;

import com.contest.model.mapper.rest.Candidates;
import com.contest.model.Contest;
import com.contest.model.mapper.rest.Status;
import com.contest.service.ConditionService;
import com.contest.service.ContestService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
@Data
public class CandidatesMapper {
  private ConditionService service;
  private ContestService contestService;
  public Candidates toRestCandidates(com.contest.model.Candidates candidates) {
    Candidates restCandidates = new Candidates();
    Contest contest = contestService.getById(candidates.getContest().getId());
    restCandidates.setId(candidates.getId());
    restCandidates.setFirstName(candidates.getFirstName());
    restCandidates.setLastName(candidates.getLastName());
    restCandidates.setFrenchAverage((candidates.getFrenchBac() + candidates.getFrenchEntranceExam()) / 2);
    restCandidates.setFrenchBac(candidates.getFrenchBac());
    restCandidates.setFrenchEntranceExam(candidates.getFrenchEntranceExam());
    restCandidates.setMathBac(candidates.getMathBac());
    restCandidates.setMathEntranceExam(candidates.getMathEntranceExam());
    restCandidates.setContest(contest);
    restCandidates.setMathAverage((candidates.getMathBac() + candidates.getMathEntranceExam()) / 2);
    restCandidates.setTotalAverage((restCandidates.getFrenchAverage() + restCandidates.getMathAverage()) / 2);
    if ((candidates.getFrenchBac() + candidates.getFrenchEntranceExam() + candidates.getMathBac()
            + candidates.getMathEntranceExam()) / 4 >= service.getById(1L).get().getMin_admin() && (candidates.getMathBac()
            + candidates.getMathEntranceExam()) / 2 >= service.getById(1L).get().getMin_Math()) {
      restCandidates.setStatus(Status.ADMIS);
    } else if ((candidates.getFrenchBac() + candidates.getFrenchEntranceExam() + candidates.getMathBac()
            + candidates.getMathEntranceExam()) / 4 < service.getById(1L).get().getMin_admin() && (candidates.getFrenchBac()
            + candidates.getFrenchEntranceExam() + candidates.getMathBac()
            + candidates.getMathEntranceExam()) / 4 >= service.getById(1L).get().getMin_list() && (candidates.getMathBac()
            + candidates.getMathEntranceExam()) / 2 >= service.getById(1L).get().getMin_Math()) {
      restCandidates.setStatus(Status.EN_ATTENTE);
    } else {
      restCandidates.setStatus(Status.RECALE);
    }
    return restCandidates;
  }
/**
  public Candidates toRest (com.contest.model.mapper.rest.Candidates toCreate) {
    Contest contest = contestService.getById(toCreate.getContest().getId());
    com.contest.model.Candidates newCandidates = new com.contest.model.Candidates();
    if((toCreate.getFrenchBac()
            + toCreate.getFrenchEntranceExam()
            + toCreate.getMathBac()
            + toCreate.getMathEntranceExam()) / 4 >= service.getById(1L).get().getMin_admin() && (toCreate.getMathBac()
            + toCreate.getMathEntranceExam()) / 2 >= service.getById(1L).get().getMin_Math()) {
        newCandidates.setStatus(Status.ADMIS);
    }
    else if ((toCreate.getFrenchBac() + toCreate.getFrenchEntranceExam() + toCreate.getMathBac()
            + toCreate.getMathEntranceExam()) / 4 < service.getById(1L).get().getMin_admin() && (toCreate.getFrenchBac()
            + toCreate.getFrenchEntranceExam() + toCreate.getMathBac()
            + toCreate.getMathEntranceExam()) / 4 >= service.getById(1L).get().getMin_list() && (toCreate.getMathBac()
            + toCreate.getMathEntranceExam()) / 2 >= service.getById(1L).get().getMin_Math()) {
      newCandidates.setStatus(Status.EN_ATTENTE);
    }
    else {
      newCandidates.setStatus(Status.RECALE);
    }
    return Candidates
            .builder()
            .id(toCreate.getId())
            .lastName(toCreate.getLastName())
            .firstName(toCreate.getFirstName())
            .frenchBac(toCreate.getFrenchBac())
            .frenchEntranceExam(toCreate.getFrenchEntranceExam())
            .mathEntranceExam(toCreate.getMathEntranceExam())
            .mathBac(toCreate.getMathBac())
            .frenchAverage((toCreate.getFrenchBac() + toCreate.getFrenchEntranceExam()) / 2)
            .mathAverage((toCreate.getMathBac() + toCreate.getMathEntranceExam()) / 2)
            .totalAverage((toCreate.getFrenchAverage() + toCreate.getMathAverage()) / 2)
            .contest(contest)
            .status(newCandidates.getStatus())
            .build();
  } */
}
