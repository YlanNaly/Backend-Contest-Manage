package com.contest.model.mapper;
import com.contest.model.Contest;
import com.contest.model.YearUniv;
import com.contest.model.mapper.rest.ContestPost;
import com.contest.service.YearUnivService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@AllArgsConstructor
@Component
public class ContestMapper {

  private final YearUnivService yearUnivService;
  public Contest toRest(com.contest.model.mapper.rest.Contest contest) {
    YearUniv id_yearUniv = yearUnivService.getById(contest.getYearUniv().getId());
    return Contest.builder()
            .id(contest.getId())
             .contestDate(contest.getContestDate())
            .yearUniv(id_yearUniv)
             .build();
  }
  public List<Contest> toDomainList(
          List<com.contest.model.mapper.rest.Contest> contestList
  ){
    return contestList.stream().map(this::toRest).toList();
  }

  public Contest toRestPost(ContestPost contestPost){
    YearUniv yearUniv = yearUnivService.getById(contestPost.getId_yearUniv());
    Contest newContest = new Contest();
    newContest.setYearUniv(yearUniv);
    newContest.setContestDate(contestPost.getContestDate());
    return  newContest;
  }

  public List<Contest> toDomainPostList(
          List<com.contest.model.mapper.rest.ContestPost> contestList
  ){
    return contestList.stream().map(this::toRestPost).toList();
  }}
