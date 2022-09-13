package com.contest.repository;

import com.contest.model.YearUniv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearUnivRepository extends JpaRepository<YearUniv, Long> {
}
