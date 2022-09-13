package com.contest.controller;

import com.contest.service.YearUnivService;
import com.contest.model.YearUniv;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/year")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class YearUnivController {

  private final YearUnivService yearUnivService;
  @GetMapping
  public List<YearUniv> getAll(
          @RequestParam int page ,
          @RequestParam int pageSize
  ){
    return yearUnivService.getAll(page, pageSize);
  }
  @PostMapping
  public List<YearUniv> addYearUniv(YearUniv yearUniv){
    return yearUnivService.saveYearUniv(yearUniv);
  }
}
