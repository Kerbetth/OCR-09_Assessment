package com.medic.mediscreen.service;

import com.medic.mediscreen.dto.AssessInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * classic CRUD methods in order to managing de Patient table in the database
 */

@Service
@Slf4j
public class AssessmentService {

    private Set<String> termList;

    public AssessmentService(@Value("${termesDeclencheurs}") String terms) {
        termList = new HashSet<>(Arrays.asList(terms.split(",")));
    }

    public String getAssessment(AssessInfo assessInfo) {
        int occurences = getOccurrences(assessInfo.getNotes());
        int age = getAge(assessInfo.getDob());
        char sex = assessInfo.getSex();
        String assess;

        if (occurences < 2) {
            assess = "None";
        } else if (occurences < 6 && age > 30) {
            assess = "Borderline";
        } else if (occurences < 3 && age <= 30) {
            assess = "Borderline";
        } else if (occurences < 4 && age <= 30) {
            assess = "Borderline";
        } else if (occurences < 5 && age <= 30 && sex == 'M') {
            assess = "In Danger";
        } else if (occurences < 7 && age <= 30 && sex == 'F') {
            assess = "In Danger";
        } else if (occurences < 8 && age > 30) {
            assess = "In Danger";
        } else {
            assess = "Early onset";
        }
        String result = "Patient: Test "
                + assessInfo.getFamily()
                + " (age "
                + age
                + ") diabetes assessment is: "
                + assess;
        log.info("New assessment generated: " + result);
        return result;
    }

    private int getOccurrences(List<String> notes) {
        String allHistory = "";
        if (notes!=null) {
            for (String patHistory : notes) {
                allHistory += patHistory + " ";
            }
            Map<String, Integer> allWordOccurrences = new HashMap<>();
            List<String> wordList = Arrays.asList(allHistory.replaceAll(",", " ").split(" "));
            for (String a : wordList) {
                Integer freq = allWordOccurrences.get(a);
                allWordOccurrences.put(a.toLowerCase(), (freq == null) ? 1 : freq + 1);
            }

            int occurences = 0;
            for (String term : termList) {
                if (allWordOccurrences.keySet().contains(term)) {
                    occurences += allWordOccurrences.get(term.toLowerCase());
                }
            }
            return occurences;
        }
        return 0;
    }

    private int getAge(String dob) {
        LocalDate localDate = LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);
        return Period.between(localDate, LocalDate.now()).getYears();
    }

}