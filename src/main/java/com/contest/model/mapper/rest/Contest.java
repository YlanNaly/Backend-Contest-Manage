package com.contest.model.mapper.rest;

import com.contest.model.YearUniv;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Contest {
  private Long id;
  private LocalDate contestDate;
  private YearUniv yearUniv;
}
