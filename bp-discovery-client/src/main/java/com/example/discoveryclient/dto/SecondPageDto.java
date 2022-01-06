package com.example.discoveryclient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecondPageDto {
    private String passportSeries;
    private Integer passportNumber;
    private String dateOfBirth;
    private String dateOfIssue;
    private String familyStatus;
}
