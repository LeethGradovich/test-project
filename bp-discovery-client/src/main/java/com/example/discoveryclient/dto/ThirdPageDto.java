package com.example.discoveryclient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ThirdPageDto {
    private Integer monthlyIncome;
    private Integer monthlyExpenseOnLoansAndAlimony;
    private String email;
    private String confirmationMethod;
}
