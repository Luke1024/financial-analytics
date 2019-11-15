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

    public PersonalData getPersonalData(Long userId){
        Optional<User> retrievedUser = userRepository.findById(userId);
        if(retrievedUser.isPresent()) {
            return retrievedUser.get().getPersonalData();
        } else {
            return new PersonalData();
        }
    }

    public void createPersonalData(PersonalDataDto personalDataDto) {
        Optional<User> retrievedUser = userRepository.findById(personalDataDto.getUserId());
        if(retrievedUser.isPresent()){
            personalDataRepository.save(personalDataMapper.mapToPersonalData(personalDataDto, retrievedUser.get()));
        } else {}
    }

    public void updatePersonalData(PersonalDataDto personalDataDto) {
        Optional<User> retrievedUser = userRepository.findById(personalDataDto.getUserId());
        if(retrievedUser.isPresent()) {
            Long retrievedPersonalDataId = retrievedUser.get().getPersonalData().getId();
            personalDataRepository.save(personalDataMapper.mapToPersonalDataWithId(
                    retrievedPersonalDataId, personalDataDto, retrievedUser.get()));
        } else {}
    }
}
