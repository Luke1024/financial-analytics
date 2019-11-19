package com.finance.data.service.account;

import com.finance.data.domain.accounts.PersonalData;
import com.finance.data.domain.accounts.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonalDataServiceTest {

    @Test
    public void getPersonalData(){
        PersonalData personalData = new PersonalData(1L, "firstName", "lastName",
                "voivodeship", "city", "postalCode", "street",
                "homeNumber", "phoneNumber", new User());

        //User user = new User()
    }
}