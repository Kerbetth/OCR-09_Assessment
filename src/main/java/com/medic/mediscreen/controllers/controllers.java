package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * -the root of the url give link to login or create an account
 * -userHome url is the main page of connected users
 */

@Controller
public class controllers {

    @Autowired
    AssessmentService assessmentService;


    @GetMapping(value = "/assess")
    String getAssess(@RequestBody Patient patient) {
        return assessmentService.getAssessment(patient);
    }

}
