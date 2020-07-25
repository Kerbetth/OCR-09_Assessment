package com.medic.mediscreen.domain;


import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
@Getter
public class PatInfo {
    Patient patient;
    List<PatHistory> patHistoryList;
}
