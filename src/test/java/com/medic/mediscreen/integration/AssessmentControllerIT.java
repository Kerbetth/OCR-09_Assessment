package com.medic.mediscreen.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.dto.AssessInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class AssessmentControllerIT {


    @Autowired
    MockMvc mockMvc;


    ObjectMapper objectMapper = new ObjectMapper();
    List<String> notes = new ArrayList<>();
    AssessInfo assessInfo;

    @BeforeEach
    void setup() {
        notes.add("a note");
        notes.add("another note");
        assessInfo = new AssessInfo("family", "1998-10-05",'M',notes);
    }

    @Test
    public void getEmptyAssess() throws Exception {
        String json = objectMapper.writeValueAsString(assessInfo);
        System.out.println(json);
        String result = mockMvc.perform(post("/assess")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(result).contains("None");
    }
    @Test
    public void getAssessBorderline() throws Exception {
        assessInfo.getNotes().add("réaction,anticorps");
        String result = mockMvc.perform(post("/assess")
                .content(objectMapper.writeValueAsString(assessInfo))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(result).contains("In Danger");
    }

    @Test
    public void getAssessInDanger() throws Exception {
        assessInfo.getNotes().add("vertige,rechute,réaction,anticorps");
        String result = mockMvc.perform(post("/assess")
                .content(objectMapper.writeValueAsString(assessInfo))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(result).contains("In Danger");
    }

    @Test
    public void getAssessEarlyOnset() throws Exception {
        assessInfo.getNotes().add("poids,fumeur,vertige,rechute,réaction,anticorps");
        String result = mockMvc.perform(post("/assess")
                .content(objectMapper.writeValueAsString(assessInfo))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(result).contains("Early onset");
    }
}
