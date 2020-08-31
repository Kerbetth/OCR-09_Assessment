package com.medic.mediscreen.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Patient {
    Integer patId;
    String family;
    String given;
    Date dob;
    char sex;
    String address;
    String phone;
    private Set<PatHistory> patHistories;
}
