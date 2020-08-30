package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.PatInfo;
import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * -the root of the url give link to login or create an account
 * -userHome url is the main page of connected users
 */

@Controller
public class controllers {

    @Autowired
    PatientService patientService;


    @GetMapping(value = "/assess")
    String getAssess(@RequestBody PatInfo patInfo) {
        return patientService.getAssessment(patInfo);
    }

}
