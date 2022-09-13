package com.contest.model.mapper.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
@ToString
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ContestPost {
  private LocalDate contestDate;
  private Long id_yearUniv;
}
