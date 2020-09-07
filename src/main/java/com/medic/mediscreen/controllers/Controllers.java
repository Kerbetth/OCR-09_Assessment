package com.medic.mediscreen.controllers;


import com.medic.mediscreen.dto.AssessInfo;
import com.medic.mediscreen.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * -the root of the url give link to login or create an account
 * -userHome url is the main page of connected users
 */

@RestController
public class Controllers {

    @Autowired
    AssessmentService assessmentService;


    @PostMapping(value = "/assess")
    String getAssess(@RequestBody AssessInfo assessInfo) {
        return assessmentService.getAssessment(assessInfo);
    }

}
