package com.github.AlissonMartin.ong.controllers;

import com.github.AlissonMartin.ong.dtos.JobDetailResponseDTO;
import com.github.AlissonMartin.ong.dtos.JobListResponseDTO;
import com.github.AlissonMartin.ong.services.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/jobs")
public class JobsController {

  @Autowired
  JobService jobService;

  @GetMapping
  public ResponseEntity<List<JobListResponseDTO>> list(@RequestParam("search") String search, @RequestParam("page") int page, @RequestParam int size) {

    List<JobListResponseDTO> institutions = jobService.list(search, page, size);

    return ResponseEntity.ok(institutions);
  }
  @Operation(summary = "Private endpoint", security = @SecurityRequirement(name = "bearerAuth"))
  @GetMapping("/{id}")
  public ResponseEntity<JobDetailResponseDTO> getById(@PathVariable("id") int id) {
    JobDetailResponseDTO job = jobService.getById(id);

    return ResponseEntity.ok(job);
  }
}
