package com.finance.service.database;

import com.finance.domain.UserData;
import com.finance.domain.User;
import com.finance.domain.dto.PersonalDataDto;
import com.finance.mapper.PersonalDataMapper;
import com.finance.repository.PersonalDataRepository;
import com.finance.repository.UserRepository;
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
            //System.out.println("returning empty personalData");
            return new UserData();
        }
    }

    public UserData createUpdatePersonalData(PersonalDataDto personalDataDto) {
        Optional<User> retrievedUser = userRepository.findById(personalDataDto.getUserId());
        if(retrievedUser.isPresent()){
            User user = retrievedUser.get();
            UserData userData = personalDataMapper.mapToPersonalDataWithUser(personalDataDto);
            user.setUserData(userData);
            user = userRepository.save(retrievedUser.get());
            return user.getUserData();
        } else {
            //System.out.println("user not found");
            return new UserData();
        }
    }
}
