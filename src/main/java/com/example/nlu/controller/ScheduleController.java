package com.example.nlu.controller;

import com.example.nlu.dto.InitialProgramRequest;
import com.example.nlu.dto.SubjectClassRequest;
import com.example.nlu.dto.SubjectClassResponse;
import com.example.nlu.dto.SubjectRequest;
import com.example.nlu.model.*;
import com.example.nlu.repository.*;
import com.example.nlu.security.JwtUtils;
import com.example.nlu.service.Constant;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/schedule")
public class ScheduleController {

    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectClassRepository subjectClassRepository;
    @Autowired
    private InitialProgramDetailRepository initialProgramDetailRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/semesters")
    public List<Semester> getAllSemester() {
        System.out.println("Get semesters");
        return semesterRepository.findByAvaIAndSort(Constant.ACTIVE_STATUS, Sort.by(Sort.Order.desc("id")));

    }

    @PostMapping("/subjectclasses")
    public List<SubjectClassResponse> getActiveSubjectsByUserID(@RequestBody SubjectRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("active subject of user");
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("Token không tồn tại");
        }
        try {
            // Kiểm tra tính hợp lệ của token
            if (!JwtUtils.validateToken(token)) {
                throw new UnauthorizedException("Token hết hạn hoặc không hợp lệ");
            }
        } catch (JwtException e) {
            // Xử lý lỗi khi giải mã token không hợp lệ
            throw new UnauthorizedException("Token không hợp lệ: " + e.getMessage());
        }
        return subjectClassRepository.findActiveSubjectClassesByUserID(params.getUserID(),params.getSemesterID(), Constant.DELETED_STATUS);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }

    @PostMapping("/initialprograms")
    public List<InitialProgramDetail> getInitialProgramOfUsre(@RequestBody InitialProgramRequest params) {
        System.out.println("initialprogram of user: " + params.getUserID());
        long studentID = params.getUserID();
        Optional<Student> studentOptional = studentRepository.findById(studentID);
        if (!studentOptional.isPresent()) {
            return null;
        }

         Student student = studentOptional.get();
        String major = student.getMajor();
        int year = student.getYear();
        System.out.println("major: " + major);
        System.out.println("year: " + year);
        return initialProgramDetailRepository.findByMajorAndYear(major, year);
    }

    @PostMapping("/add/subjectclass")
    public SubjectClassResponse addSubjectClass(@RequestBody SubjectClass params, @RequestHeader("Authorization") String token) {
        System.out.println("insertSubjectClass");
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("Token không tồn tại");
        }
        try {
            // Kiểm tra tính hợp lệ của token
            if (!JwtUtils.validateToken(token)) {
                throw new UnauthorizedException("Token hết hạn hoặc không hợp lệ");
            }
        } catch (JwtException e) {
            // Xử lý lỗi khi giải mã token không hợp lệ
            throw new UnauthorizedException("Token không hợp lệ: " + e.getMessage());
        }
        SubjectClass subjectClass = new SubjectClass();
        subjectClass.setTeam(params.getTeam());
        subjectClass.setClassID(params.getClassID());
        subjectClass.setSubject(params.getSubject());
        subjectClass.setTeacher(params.getTeacher());
        subjectClass.setStartLesson(params.getStartLesson());
        subjectClass.setLessonNum(params.getLessonNum());
        subjectClass.setDay(params.getDay());
        subjectClass.setRoom(params.getRoom());
        subjectClass.setStudentNum(params.getStudentNum());
        subjectClass.setStartDate(params.getStartDate());
        subjectClass.setEndDate(params.getEndDate());
        subjectClass.setStudentNum(params.getStudentNum());
        subjectClass = subjectClassRepository.save(subjectClass);
        return new SubjectClassResponse(subjectClass);
    }
    @PostMapping("/add/registration")
    public SubjectClassResponse addRegistration(@RequestBody SubjectClass params, @RequestHeader("Authorization") String token) {
        System.out.println("insertSubjectClass");
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("Token không tồn tại");
        }
        try {
            // Kiểm tra tính hợp lệ của token
            if (!JwtUtils.validateToken(token)) {
                throw new UnauthorizedException("Token hết hạn hoặc không hợp lệ");
            }
        } catch (JwtException e) {
            // Xử lý lỗi khi giải mã token không hợp lệ
            throw new UnauthorizedException("Token không hợp lệ: " + e.getMessage());
        }
        SubjectClass subjectClass = new SubjectClass();
        subjectClass.setTeam(params.getTeam());
        subjectClass.setClassID(params.getClassID());
        subjectClass.setSubject(params.getSubject());
        subjectClass.setTeacher(params.getTeacher());
        subjectClass.setStartLesson(params.getStartLesson());
        subjectClass.setLessonNum(params.getLessonNum());
        subjectClass.setDay(params.getDay());
        subjectClass.setRoom(params.getRoom());
        subjectClass.setStudentNum(params.getStudentNum());
        subjectClass.setStartDate(params.getStartDate());
        subjectClass.setEndDate(params.getEndDate());
        subjectClass.setStudentNum(params.getStudentNum());
        subjectClass = subjectClassRepository.save(subjectClass);
        return new SubjectClassResponse(subjectClass);
    }

    @PutMapping("/updateqty/subjectclass")
    public ResponseEntity<Object> updateQty(@RequestBody SubjectClassRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("updateqty");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Token không tồn tại"));
        }

        try {
            // Kiểm tra tính hợp lệ của token
            if (!JwtUtils.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Token hết hạn hoặc không hợp lệ"));
            }
        } catch (JwtException e) {
            // Xử lý lỗi khi giải mã token không hợp lệ
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Token không hợp lệ: " + e.getMessage()));
        }

        Optional<SubjectClass> option = subjectClassRepository.findById(params.getSubjectClassID());
        if (option.isPresent()) {
            SubjectClass subjectClass = option.get();
            int currentStudentNum = subjectClass.getStudentNum();

            if (currentStudentNum >= params.getQty()) {
                int avaiNewQty = subjectClass.getStudentNum() - subjectClass.getRemainingQty();
                if (avaiNewQty >= params.getQty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ErrorResponse("Số lượng sinh viên không đủ để cập nhật"));
                } else {
                    int registerNum = subjectClass.getStudentNum() - subjectClass.getRemainingQty();
                    subjectClass.setStudentNum(params.getQty());
                    int remainingQty = params.getQty() - registerNum;
                    subjectClass.setRemainingQty(remainingQty);
                    subjectClass = subjectClassRepository.save(subjectClass);
                    return ResponseEntity.ok(new SubjectClassResponse(subjectClass));
                }
            } else {
                int registerNum = subjectClass.getStudentNum() - subjectClass.getRemainingQty();
                subjectClass.setStudentNum(params.getQty());
                int remainingQty = params.getQty() - registerNum;
                subjectClass.setRemainingQty(remainingQty);
                subjectClass = subjectClassRepository.save(subjectClass);
                return ResponseEntity.ok(new SubjectClassResponse(subjectClass));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Không tìm thấy lớp học"));
    }
    @GetMapping("/subjectclasses/registering")
    public List<SubjectClassResponse> getAllRegisteringSubjectClass(@RequestHeader("Authorization") String token) {
        System.out.println("getAllRegisteringSubjectClass");
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("Token không tồn tại");
        }
        try {

            // Kiểm tra tính hợp lệ của token
            if (!JwtUtils.validateToken(token)) {
                throw new UnauthorizedException("Token hết hạn hoặc không hợp lệ");
            }
        } catch (JwtException e) {
            // Xử lý lỗi khi giải mã token không hợp lệ
            throw new UnauthorizedException("Token không hợp lệ: " + e.getMessage());
        }
//        List<SubjectClass> subjectClasses = subjectClassRepository.findAllRegisteringSubjectClass(Constant.COMING_STATUS);
//        return subjectClasses;
        return subjectClassRepository.findAllRegisteringSubjectClass(Constant.COMING_STATUS);
    }
    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @PutMapping("/update/subjectclass")
    public ResponseEntity<Object> update(@RequestBody SubjectClassRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("update");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Token không tồn tại"));
        }

        try {
            // Kiểm tra tính hợp lệ của token
            if (!JwtUtils.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Token hết hạn hoặc không hợp lệ"));
            }
        } catch (JwtException e) {
            // Xử lý lỗi khi giải mã token không hợp lệ
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Token không hợp lệ: " + e.getMessage()));
        }
        SubjectClass param = new SubjectClass();
        param.setId(0);
        param = params.getSubjectClass();


        Optional<SubjectClass> option = subjectClassRepository.findById(param.getId());
        if (option.isPresent()) {
            SubjectClass subjectClass = option.get();
            subjectClass.setTeam(param.getTeam());
            subjectClass.setClassID(param.getClassID());
            subjectClass.setRoom(param.getRoom());
            subjectClass.setDay(param.getDay());
            subjectClass.setStartLesson(param.getStartLesson());
            subjectClass.setLessonNum(param.getLessonNum());
            Teacher teacher = subjectClass.getTeacher();
            Optional<Teacher> teacherOptional = teacherRepository.findById(param.getTeacher().getId());
            if (teacherOptional.isPresent()) {
                teacher = teacherOptional.get();
            }
            subjectClass.setTeacher(teacher);
            subjectClass.setStartDate(param.getStartDate());
            subjectClass.setEndDate(param.getEndDate());
            subjectClass = subjectClassRepository.save(subjectClass);
            return ResponseEntity.ok(new SubjectClassResponse(subjectClass));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Không tìm thấy lớp học"));
    }
    @PutMapping("/add/subjectclass")
    public ResponseEntity<Object> add(@RequestBody SubjectClassRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("add");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Token không tồn tại"));
        }

        try {
            // Kiểm tra tính hợp lệ của token
            if (!JwtUtils.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Token hết hạn hoặc không hợp lệ"));
            }
        } catch (JwtException e) {
            // Xử lý lỗi khi giải mã token không hợp lệ
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Token không hợp lệ: " + e.getMessage()));
        }
        SubjectClass param = new SubjectClass();
        param.setId(0);
        param = params.getSubjectClass();
        System.out.println("param: " + param.toString());
        SubjectClass subjectClass = new SubjectClass();
        subjectClass.setTeam(param.getTeam());
        subjectClass.setClassID(param.getClassID());
        subjectClass.setRoom(param.getRoom());
        subjectClass.setDay(param.getDay());
        subjectClass.setStartLesson(param.getStartLesson());
        subjectClass.setLessonNum(param.getLessonNum());
        Teacher teacher = subjectClass.getTeacher();
        Optional<Teacher> teacherOptional = teacherRepository.findById(param.getTeacher().getId());
        if (teacherOptional.isPresent()) {
            teacher = teacherOptional.get();
        }
        subjectClass.setTeacher(teacher);
        Subject subject = params.getSubject();
        Optional<Subject> subjectOptional = subjectRepository.findById(subject.getId());
        if (subjectOptional.isPresent()) {
            subject = subjectOptional.get();
        }
        subjectClass.setSubject(subject);
        subjectClass.setStartDate(param.getStartDate());
        subjectClass.setEndDate(param.getEndDate());
        subjectClass.setStudentNum(param.getStudentNum());
        subjectClass.setRemainingQty(param.getStudentNum());
        subjectClass.setStatus(Constant.COMING_STATUS);
        subjectClass = subjectClassRepository.save(subjectClass);
        return ResponseEntity.ok(new SubjectClassResponse(subjectClass));

    }



}
