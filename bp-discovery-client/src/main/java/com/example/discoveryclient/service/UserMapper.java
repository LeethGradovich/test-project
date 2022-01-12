package com.example.discoveryclient.service;

import com.example.discoveryclient.dto.FirstPageDto;
import com.example.discoveryclient.dto.SecondPageDto;
import com.example.discoveryclient.dto.ThirdPageDto;
import com.example.discoveryclient.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "surname", source = "dto.surname"),
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "patronymic", source = "dto.patronymic")
    })
    User firstPageDtoToUser(FirstPageDto dto, User user);

    @Mappings({
            @Mapping(target = "passportSeries", source = "dto.passportSeries"),
            @Mapping(target = "passportNumber", source = "dto.passportNumber"),
            @Mapping(target = "dateOfBirth", source = "dto.dateOfBirth"),
            @Mapping(target = "dateOfIssue", source = "dto.dateOfIssue"),
            @Mapping(target = "familyStatus", source = "dto.familyStatus")
    })
    User secondPageDtoToUser(SecondPageDto dto, @MappingTarget User user);

    @Mappings({
            @Mapping(target = "monthlyIncome", source = "dto.monthlyIncome"),
            @Mapping(target = "monthlyExpenseOnLoansAndAlimony", source = "dto.monthlyExpenseOnLoansAndAlimony"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "confirmationMethod", source = "dto.confirmationMethod")
    })
    User thirdPageDtoToUser(ThirdPageDto dto, User user);
}
