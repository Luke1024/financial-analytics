package com.finance.data.service.account;

import com.finance.data.domain.accounts.PersonalData;
import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.PersonalDataDto;
import com.finance.data.mapper.accounts.PersonalDataMapper;
import com.finance.data.repository.accounts.PersonalDataRepository;
import com.finance.data.repository.accounts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonalDataService {
    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonalDataMapper personalDataMapper;

    public PersonalData getPersonalDataByUserId(Long userId){
        Optional<User> retrievedUser = userRepository.findById(userId);
        if(retrievedUser.isPresent()) {
            return retrievedUser.get().getPersonalData();
        } else {
            System.out.println("returning empty personalData");
            return new PersonalData();
        }
    }

    public void createUpdatePersonalData(PersonalDataDto personalDataDto) {
        Optional<User> retrievedUser = userRepository.findById(personalDataDto.getUserId());
        if(retrievedUser.isPresent()){
            User user = retrievedUser.get();
            PersonalData personalData = personalDataMapper.mapToPersonalDataWithUser(personalDataDto, user);
            user.setPersonalData(personalData);
            userRepository.save(retrievedUser.get());
        } else {
            System.out.println("user not found");
        }
    }
}
