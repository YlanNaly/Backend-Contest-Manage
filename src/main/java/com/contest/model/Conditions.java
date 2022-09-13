package com.contest.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conditions {

    public Conditions(Double min_admin, Double min_Math, Double min_list) {
        this.min_admin = min_admin;
        this.min_Math = min_Math;
        this.min_list = min_list;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double min_admin;
    @Column
    private Double min_Math;
    @Column
    private Double min_list;
}
