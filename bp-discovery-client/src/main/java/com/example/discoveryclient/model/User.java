package com.example.discoveryclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @Column(name = "id")
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String passportSeries;
    private Integer passportNumber;
    private String dateOfBirth;
    private String dateOfIssue;
    private String familyStatus;
    private Integer monthlyIncome;
    private Integer monthlyExpenseOnLoansAndAlimony;
    private String email;
    private String confirmationMethod;
}
