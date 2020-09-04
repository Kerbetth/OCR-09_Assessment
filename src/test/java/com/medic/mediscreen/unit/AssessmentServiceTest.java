package com.medic.mediscreen.unit;

import com.medic.mediscreen.dto.AssessInfo;
import com.medic.mediscreen.service.AssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
public class AssessmentServiceTest {

    private AssessmentService assessmentService = new AssessmentService("vertige,rechute,réaction,anticorps");


    List<String> notes = new ArrayList<>();
    AssessInfo assessInfo;

    @BeforeEach
    void setup() {
        notes.add("a note");
        notes.add("another note");
        assessInfo = new AssessInfo("family", "1998-10-05",'M',notes);
    }

    @Test
    public void getAllPatHistorys() {
        String result = assessmentService.getAssessment(assessInfo);
        assertThat(result).contains("None");
    }
}
