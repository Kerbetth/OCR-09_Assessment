package com.medic.mediscreen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.dto.AssessInfo;
import com.medic.mediscreen.service.AssessmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class AssessmentController {

    @MockBean
    private AssessmentService  assessmentService;

	@Autowired
	MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    AssessInfo assessInfo;
    List<String> notes = new ArrayList<>();

    @BeforeEach
    void setup() {
        notes.add("a note");
        notes.add("another note");
        assessInfo = new AssessInfo("family", "1998-10-05",'M',notes);
    }

    @Test
    public void getAssess() throws Exception {
        when(assessmentService.getAssessment(any())).thenReturn("this String");
       String result= mockMvc.perform(post("/assess")
               .content(objectMapper.writeValueAsString(assessInfo))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

       assertThat(result).isEqualTo("this String");
    }

}
