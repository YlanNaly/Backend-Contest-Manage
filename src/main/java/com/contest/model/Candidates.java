package com.contest.model;

import com.contest.model.mapper.rest.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Candidates implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 150)
  private String firstName;

  @Column(nullable = false, length = 150)
  private String lastName;

  @Column(nullable = false)
  private Double frenchEntranceExam;

  @Column(nullable = false)
  private Double frenchBac;

  @Column(nullable = false)
  private Double mathEntranceExam;

  @Column(nullable = false)
  private Double mathBac;

  @OneToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "id_contest")
  private Contest contest;

  @Transient
  private Double totalAverage;
}
