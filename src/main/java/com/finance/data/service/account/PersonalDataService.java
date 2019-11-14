package com.finance.data.service.account;

import com.finance.data.domain.accounts.PersonalData;
import com.finance.data.domain.accounts.User;
import com.finance.data.domain.accounts.dto.PersonalDataDto;
import com.finance.data.repository.accounts.PersonalDataRepository;
import com.finance.data.repository.accounts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Component
public class PersonalDataService {
    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonalDataMapper personalDataMapper;

    public Optional<PersonalData> getPersonalData(Long userId){
        return personalDataRepository.findByUserId(userId);
    }

    public void createPersonalData(PersonalDataDto personalDataDto) {
        personalDataRepository.save(personalDataMapper.mapToPersonalData(personalDataDto));
    }

    public void updatePersonalData(PersonalDataDto personalDataDto) {
        Optional<User> retrievedUser = userRepository.findById(personalDataDto.getUserId());
        if(retrievedUser.isPresent()){
             Long retrievedPersonalDataId = retrievedUser.get().getPersonalData().getId();
             personalDataRepository.save(personalDataMapper.mapToPersonalDataWithId(
                     retrievedPersonalDataId, personalDataDto));
    }
}
