package com.example.nlu.controller;

import com.example.nlu.dto.*;
import com.example.nlu.model.Registration;
import com.example.nlu.model.SubjectClass;
import com.example.nlu.model.User;
import com.example.nlu.repository.ProgramRepository;
import com.example.nlu.repository.RegistrationRepository;
import com.example.nlu.repository.SubjectClassRepository;
import com.example.nlu.security.JwtUtils;
import com.example.nlu.service.Constant;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/register")
public class RegistrationController {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private SubjectClassRepository subjectClassRepository;
    @Autowired
    private ProgramRepository programRepository;

    @PostMapping("/subjectclasses")
    public List<RegisteringSubjectClassResponse> getRegisterSubjectClassByClassID(@RequestBody RegisterRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("get register by classID: " + params.getUserID());
        if (token == null || token.isEmpty()) {
            throw new RegistrationController.UnauthorizedException("Token không tồn tại");
        }
        try {

            // Kiểm tra tính hợp lệ của token
            if (!JwtUtils.validateToken(token)) {
                throw new RegistrationController.UnauthorizedException("Token hết hạn hoặc không hợp lệ");
            }
        } catch (JwtException e) {
            // Xử lý lỗi khi giải mã token không hợp lệ
            throw new RegistrationController.UnauthorizedException("Token không hợp lệ: " + e.getMessage());
        }
        String queryFilter = params.getQueryFilter();
        if(queryFilter.equalsIgnoreCase("class"))
            return registrationRepository.findRegisterSubjectClassesByClassID(params.getUserID(), Constant.COMING_STATUS, Constant.DELETED_STATUS);
        else if(queryFilter.equalsIgnoreCase("program")) {
            String subjectIDsJSON = programRepository.findSubjectIdsByMajor(params.getUserID());
            Gson gson = new Gson();
            // Xác định kiểu của danh sách Long
            Type listType = new TypeToken<List<Long>>() {}.getType();
            // Chuyển chuỗi JSON thành List<Long>
            List<Long> subjectIDs = gson.fromJson(subjectIDsJSON, listType);
            return registrationRepository.findRegisterSubjectClassesByProgram(subjectIDs, Constant.COMING_STATUS, Constant.DELETED_STATUS);
        }

        else
            return registrationRepository.findRegisterSubjectClassesByClassID(params.getUserID(), Constant.COMING_STATUS, Constant.DELETED_STATUS);


    }

    @PostMapping("/register")
    public RegisterResultResponse register(@RequestBody RegisterRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("register subject class: " + params.getUserID());
        if (token == null || token.isEmpty()) {
            throw new RegistrationController.UnauthorizedException("Token không tồn tại");
        }
        try {
            if (!JwtUtils.validateToken(token)) {
                throw new RegistrationController.UnauthorizedException("Token hết hạn hoặc không hợp lệ");
            }
        } catch (JwtException e) {
            throw new RegistrationController.UnauthorizedException("Token không hợp lệ: " + e.getMessage());
        }
        String queryFilter = params.getQueryFilter();
        List<Long> subjectClassIDs = registrationRepository.findWaitingSubjectClassByUserID(params.getUserID(), Constant.WAITING_STATUS);
        List<SubjectClass> subjectClassesCheck = subjectClassRepository.findSubjectClassByIDs(subjectClassIDs);
        Optional<SubjectClass> subjectClassCheckOptional = subjectClassRepository.findById(params.getSubjectClassID());
        SubjectClass subjectClassCheck = null;
        if (subjectClassCheckOptional.isPresent()) {
            subjectClassCheck = subjectClassCheckOptional.get();
        }
        boolean isSameTime =false;
        if(subjectClassCheck != null) {
            for(SubjectClass sc : subjectClassesCheck) {
                if(sc.getDay() == subjectClassCheck.getDay() && sc.getStartLesson()==subjectClassCheck.getStartLesson()) {
                    isSameTime = true;
                    break;
                }
            }
        }
        if(subjectClassCheck == null) {
            isSameTime = true;
        }
        List<RegisteringSubjectClassResponse> subjectClasses = new ArrayList<>();
        int status;
        if(isSameTime) {
            status = Constant.SAME_TIME_FAIL_STATUS;
        } else {
            // decrease remain qty
            int newRemainQty = subjectClassRepository.decreaseRemainQty(params.getSubjectClassID());
            if(newRemainQty >= 0){ // co the dang ky
                Optional<SubjectClass> optionalSubjectClass = subjectClassRepository.findById(params.getSubjectClassID());
                if (optionalSubjectClass.isPresent()) {
                    SubjectClass subjectClasss = optionalSubjectClass.get();
                    System.out.println("subject ID: " +subjectClasss.getSubject().getId());
                    registrationRepository.disableOldRegister(params.getUserID(),subjectClasss.getSubject().getId(), Constant.DELETED_STATUS,Constant.WAITING_STATUS);
                    User user = new User();
                    user.setId(params.getUserID());
                    SubjectClass subjectClass = new SubjectClass();
                    subjectClass.setId(params.getSubjectClassID());
                    Registration registration = new Registration();
                    registration.setUser(user);
                    registration.setSubjectClass(subjectClass);
                    registration.setCreateAt(LocalDateTime.now());
                    registration.setAvai(1);
                    registration = registrationRepository.save(registration);  //
                }
                status = Constant.SUCCESS_STATUS;
            } else { //register fail
                status = Constant.FAIL_STATUS;
            }
        }
        // dang ky xong, return a list and status result of registration
        if(queryFilter.equalsIgnoreCase("class")) {
            subjectClasses = registrationRepository.findRegisterSubjectClassesByClassID(params.getUserID(), Constant.COMING_STATUS, Constant.DELETED_STATUS);
        } else if(queryFilter.equalsIgnoreCase("program")) {
            String subjectIDsJSON = programRepository.findSubjectIdsByMajor(params.getUserID());
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Long>>() {}.getType();
            List<Long> subjectIDs = gson.fromJson(subjectIDsJSON, listType);
            subjectClasses = registrationRepository.findRegisterSubjectClassesByProgram(subjectIDs, Constant.COMING_STATUS, Constant.DELETED_STATUS);
        }

        return new RegisterResultResponse(subjectClasses,status);

    }

    @PostMapping("/cancel")
    public RegisterResultResponse cancel(@RequestBody RegisterRequest params, @RequestHeader("Authorization") String token) {
        System.out.println("cancel subject class: " + params.getUserID());
        if (token == null || token.isEmpty()) {
            throw new RegistrationController.UnauthorizedException("Token không tồn tại");
        }
        try {
            if (!JwtUtils.validateToken(token)) {
                throw new RegistrationController.UnauthorizedException("Token hết hạn hoặc không hợp lệ");
            }
        } catch (JwtException e) {
            throw new RegistrationController.UnauthorizedException("Token không hợp lệ: " + e.getMessage());
        }
        String queryFilter = params.getQueryFilter();
        int newRemainQty = subjectClassRepository.increaseRemainQty(params.getSubjectClassID());
        int cancelResult = registrationRepository.cancelRegister(params.getUserID(),params.getSubjectClassID(), Constant.DELETED_STATUS,Constant.WAITING_STATUS);
        List<RegisteringSubjectClassResponse> subjectClasses = new ArrayList<>();
        int status;
        if(cancelResult==1) { // cancel success
           status = Constant.SUCCESS_STATUS;
        } else { //cancel fail
            status = Constant.FAIL_STATUS;
        }

        // cancel finish
        if(queryFilter.equalsIgnoreCase("class")) {
            subjectClasses = registrationRepository.findRegisterSubjectClassesByClassID(params.getUserID(), Constant.COMING_STATUS, Constant.DELETED_STATUS);
        } else if(queryFilter.equalsIgnoreCase("program")) {
            String subjectIDsJSON = programRepository.findSubjectIdsByMajor(params.getUserID());
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Long>>() {}.getType();
            List<Long> subjectIDs = gson.fromJson(subjectIDsJSON, listType);
            subjectClasses = registrationRepository.findRegisterSubjectClassesByProgram(subjectIDs, Constant.COMING_STATUS, Constant.DELETED_STATUS);
        }
        return new RegisterResultResponse(subjectClasses,status);
    }

    @PostMapping("/search")
    public List<RegisteringSubjectClassResponse> searchRegisterSubjectClassByClassID(@RequestBody RegisterRequest params) {
        System.out.println("search register sc: " + params.getSearchInput());
        return registrationRepository.findRegisterSubjectClassesBySearchInput(params.getSearchInput(), Constant.COMING_STATUS, Constant.DELETED_STATUS);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }





}
