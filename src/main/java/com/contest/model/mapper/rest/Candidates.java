package com.contest.model.mapper.rest;


import com.contest.model.Contest;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Candidates {
  private Long id;

  private String firstName;

  private String lastName;

  private Double frenchEntranceExam;

  private Double frenchBac;

  private Double frenchAverage;

  private Double mathEntranceExam;

  private Double mathBac;

  private Double mathAverage;

  private Double totalAverage;

  private Status status;

  private Contest contest;

}
