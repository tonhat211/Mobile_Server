package com.example.nlu.controller;

import com.example.nlu.helper.ResourceNotFoundException;
import com.example.nlu.model.Semester;
import com.example.nlu.model.User;
import com.example.nlu.repository.SemesterRepository;
import com.example.nlu.service.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/semester")
public class SemesterController {
    @Autowired
    private SemesterRepository semesterRepository;

    @GetMapping("/semesters")
    public List<Semester> getAllSemester() {
        System.out.println("Get semesters");
        return semesterRepository.findByAvaIAndSort(Constant.ACTIVE_STATUS, Sort.by(Sort.Order.desc("id")));
    }

    @GetMapping("semesters/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Semester semester = semesterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Học kỳ này không tồn tại: " + id));
        return ResponseEntity.ok().body(semester);
    }


    @PostMapping("semesters")
    public Semester createSemester(@Valid @RequestBody Semester semester) {
        return semesterRepository.save(semester);
    }

    @PutMapping("semester/{id}")
    public ResponseEntity<Semester> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody  Semester details) throws ResourceNotFoundException {
        Semester semester = semesterRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Học kỳ này không tồn tại: " + id));
        semester.setName(details.getName()); semester.setStartDate(details.getStartDate());semester.setStartDate(details.getStartDate());
        final Semester updatedSemester = semesterRepository.save(semester);
        return ResponseEntity.ok(updatedSemester);
    }

    @DeleteMapping("semesters/{id}")
    public Map<String, Boolean> deleteSemesterr(@PathVariable(value = "id") Long semesterId) throws ResourceNotFoundException {
        Semester semester = semesterRepository.findById(semesterId) .orElseThrow(() -> new ResourceNotFoundException("Học kỳ này không tồn tại: " + semesterId));
        semesterRepository.delete(semester);
        Map<String, Boolean> response = new HashMap<>(); response.put("deleted", Boolean.TRUE);
        return response;
    }

}
