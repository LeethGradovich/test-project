package com.example.discoveryclient.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NoArgsConstructor
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

    public void setFirstPage(Long id, String surname, String name, String patronymic) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public void setSecondPageInfo(
            String passportSeries,
            Integer passportNumber,
            String dateOfBirth,
            String dateOfIssue,
            String familyStatus) {
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfIssue = dateOfIssue;
        this.familyStatus = familyStatus;
    }

    public void setThirdPageInfo(
            Integer monthlyIncome,
            Integer monthlyExpenseOnLoansAndAlimony,
            String email,
            String confirmationMethod) {
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenseOnLoansAndAlimony = monthlyExpenseOnLoansAndAlimony;
        this.email = email;
        this.confirmationMethod = confirmationMethod;
    }
}
