package com.example.discoveryclient.service.impl;

import com.example.discoveryclient.dto.FirstPageDto;
import com.example.discoveryclient.dto.SecondPageDto;
import com.example.discoveryclient.dto.ThirdPageDto;
import com.example.discoveryclient.model.User;
import com.example.discoveryclient.service.UserMapper;
import org.springframework.stereotype.Component;

@Component("UserMapperImplementation")
public class UserMapperImpl implements UserMapper {

    @Override
    public User firstPageDtoToUser(FirstPageDto dto, User user) {
        if (dto == null) {
            return null;
        }
        user.setSurname(dto.getSurname());
        user.setName(dto.getName());
        user.setPatronymic(dto.getPatronymic());
        return user;
    }

    @Override
    public User secondPageDtoToUser(SecondPageDto dto, User user) {
        if (dto == null) {
            return null;
        }
        user.setPassportSeries(dto.getPassportSeries());
        user.setPassportNumber(dto.getPassportNumber());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setDateOfIssue(dto.getDateOfIssue());
        user.setFamilyStatus(dto.getFamilyStatus());
        return user;
    }

    @Override
    public User thirdPageDtoToUser(ThirdPageDto dto, User user) {
        if (dto == null) {
            return null;
        }
        user.setMonthlyIncome(dto.getMonthlyIncome());
        user.setMonthlyExpenseOnLoansAndAlimony(dto.getMonthlyExpenseOnLoansAndAlimony());
        user.setEmail(dto.getEmail());
        user.setConfirmationMethod(dto.getConfirmationMethod());
        return user;
    }
}
