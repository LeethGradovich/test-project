package com.example.discoveryclient.service.impl;

import com.example.discoveryclient.dto.FirstPageDto;
import com.example.discoveryclient.dto.SecondPageDto;
import com.example.discoveryclient.dto.ThirdPageDto;
import com.example.discoveryclient.model.User;
import com.example.discoveryclient.repository.PhoneNumberRepository;
import com.example.discoveryclient.repository.UserRepository;
import com.example.discoveryclient.service.PageService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PageServiceImpl implements PageService {

    public void saveUserFirstPage(UserRepository userRepository,
                                  PhoneNumberRepository phoneNumberRepository,
                                  String phoneNumber,
                                  FirstPageDto dto) {
        userRepository.save(new User(getIdByPhoneNumber(phoneNumber, phoneNumberRepository),
                dto.getSurname(),
                dto.getName(),
                dto.getPatronymic()));
    }

    public void saveUserSecondPage(UserRepository userRepository,
                                   PhoneNumberRepository phoneNumberRepository,
                                   String phoneNumber,
                                   SecondPageDto dto) {
        val user = getUserById(getIdByPhoneNumber(phoneNumber, phoneNumberRepository), userRepository);
        user.setSecondPageInfo(dto.getPassportSeries(),
                dto.getPassportNumber(),
                dto.getDateOfBirth(),
                dto.getDateOfIssue(),
                dto.getFamilyStatus());
        userRepository.save(user);
    }

    public void saveUserThirdPage(UserRepository userRepository,
                                  PhoneNumberRepository phoneNumberRepository,
                                  String phoneNumber,
                                  ThirdPageDto dto) {
        val user = getUserById(getIdByPhoneNumber(phoneNumber, phoneNumberRepository), userRepository);
        user.setThirdPageInfo(dto.getMonthlyIncome(), dto.getMonthlyExpenseOnLoansAndAlimony(), dto.getEmail(), dto.getConfirmationMethod());
        userRepository.save(user);
    }

    public Long getIdByPhoneNumber(String phoneNumber, PhoneNumberRepository phoneNumberRepository) {
        try {
            return phoneNumberRepository.findPhoneNumberByPhoneNumber(phoneNumber).orElseThrow(Exception::new).getId();
        } catch (Exception e) {
            log.error("Can't find this phone number\n" + e);
            return null;
        }
    }

    public User getUserById(Long id, UserRepository userRepository) {
        try {
            return userRepository.findUserById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            log.error("Can't find this phone number\n" + e);
            return null;
        }
    }
}
