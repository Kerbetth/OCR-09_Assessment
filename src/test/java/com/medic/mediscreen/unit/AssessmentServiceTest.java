package com.medic.mediscreen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.service.AssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
public class AssessmentServiceTest {

    @Autowired
    private AssessmentService assessmentService;

    PatHistory patHistory = new PatHistory();
    Set<PatHistory> patHistories = new HashSet<>();
    PatHistory patHistory1 = new PatHistory();
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        patHistory.setId(1);
        patHistory.setNote("a note");
        patHistories.add(new PatHistory());
    }

    @Test
    public void getAllPatHistorys() {
        Patient patient = new Patient();
        patient.setPatHistories(patHistories);
        String result = assessmentService.getAssessment(patient);
        assertThat(result).isEqualTo("");
    }
}
