package com.medic.mediscreen.domain;


import lombok.Getter;

import javax.persistence.*;
@Getter
public class PatHistory {
    Integer id;
    String riskLevel;
    String note;
}
