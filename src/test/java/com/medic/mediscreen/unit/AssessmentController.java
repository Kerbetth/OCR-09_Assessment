package com.medic.mediscreen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.service.AssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class AssessmentController {

    @MockBean
    private AssessmentService  assessmentService;

	@Autowired
	MockMvc mockMvc;


    Set<PatHistory> patHistories =new HashSet<>();
    PatHistory patHistory = new PatHistory();
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        patHistory.setId(1);
        patHistory.setNote("a note");
	patHistories.add(new PatHistory());
    }

    @Test
    public void getAssess() throws Exception {
        Patient patient = new Patient();
        patient.setPatHistories(patHistories);
        when(assessmentService.getAssessment(patient)).thenReturn("this String");
       String result= mockMvc.perform(get("/patHistory")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id","1")
        )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

       assertThat(result).isEqualTo("this String");
    }

}
