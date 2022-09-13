package com.contest.repository;

import com.contest.model.Candidates;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidatesRepository extends JpaRepository<Candidates, Long> {

  @Query("select c from Candidates c "+
          " where (c.mathBac+c.mathEntranceExam+c.frenchBac+c.frenchEntranceExam)/4 >= :min_admin and (c.mathBac + c.mathEntranceExam)/2 >= :min_math " +
          " order by (c.mathBac+c.mathEntranceExam+c.frenchBac+c.frenchEntranceExam)/4  DESC" )
  List<Candidates> findAllAdmittedCandidates(
          @Param("min_admin") Double minAdmin,
          @Param("min_math") Double minMath,
          Pageable pageable
  );
  @Query("select c from Candidates c where (c.mathBac+c.mathEntranceExam+c.frenchBac+c.frenchEntranceExam)/4 < :min_admin" +
          " and (c.mathBac+c.mathEntranceExam+c.frenchBac+c.frenchEntranceExam)/4 > :min_list " +
          "and (c.mathBac + c.mathEntranceExam)/2 >= :min_math " +
          "order by (c.mathBac+c.mathEntranceExam+c.frenchBac+c.frenchEntranceExam)/4  DESC")
  List<Candidates> findAllInStandBy(
          @Param("min_admin") Double minAdmin,
          @Param("min_math") Double minMath,
          @Param("min_list") Double minList
  );

  @Query("select c from Candidates c where (c.mathBac+c.mathEntranceExam+c.frenchBac+c.frenchEntranceExam)/4 < :min_list order by (c.mathBac+c.mathEntranceExam+c.frenchBac+c.frenchEntranceExam)/4  ASC")
  List<Candidates> findAllNonAdmitted(@Param("min_list") Double minList, Pageable pageable);
  @Query(value =
          "SELECT * FROM candidates " +
                  "LEFT JOIN contest " +
                  "ON candidates.id_contest = contest.id " +
                  "LEFT JOIN university_year  " +
                  "ON contest.id_year = university_year.id " +
                  "WHERE university_year.id = :year "
  ,nativeQuery = true
  )
  List<Candidates> findAllByYearUniv(@Param("year")int universityYear , Pageable pageable);
  @Query(value=
          "SELECT * FROM candidates " +
                  "LEFT JOIN contest " +
                  "ON candidates.id_contest = contest.id " +
                  "WHERE contest.id = :id_date " +
                  "order by (candidates.math_bac+candidates.math_entrance_exam+candidates.french_bac+candidates.french_entrance_exam)/4  ASC " +
                  "limit 1"
          ,nativeQuery=true
  )
  Candidates findAllByContest(@Param("id_date")int id_date);
}
