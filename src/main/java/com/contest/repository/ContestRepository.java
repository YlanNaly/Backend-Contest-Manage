package com.contest.repository;

import com.contest.model.Candidates;
import com.contest.model.Contest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
  @Query(value =
          "SELECT * FROM contest " +
                  "LEFT JOIN university_year  " +
                  "ON contest.id_year = university_year.id " +
                  "WHERE university_year.id = :year "
          ,nativeQuery = true
  )
  List<Contest> findAllByYearUniv(@Param("year")int universityYear);
}
