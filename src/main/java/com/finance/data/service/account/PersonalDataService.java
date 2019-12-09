package com.finance.data.service.account;

import com.finance.data.domain.accounts.UserData;
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

    public UserData getPersonalDataByUserId(Long userId){
        Optional<User> retrievedUser = userRepository.findById(userId);
        if(retrievedUser.isPresent()) {
            return retrievedUser.get().getUserData();
        } else {
            System.out.println("returning empty personalData");
            return new UserData();
        }
    }

    public UserData createUpdatePersonalData(PersonalDataDto personalDataDto) {
        Optional<User> retrievedUser = userRepository.findById(personalDataDto.getUserId());
        if(retrievedUser.isPresent()){
            User user = retrievedUser.get();
            UserData userData = personalDataMapper.mapToPersonalDataWithUser(personalDataDto, user);
            user.setUserData(userData);
            user = userRepository.save(retrievedUser.get());
            return user.getUserData();
        } else {
            System.out.println("user not found");
            return new UserData();
        }
    }
}
