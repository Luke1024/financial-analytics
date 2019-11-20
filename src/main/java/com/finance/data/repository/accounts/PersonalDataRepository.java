package com.finance.data.repository.accounts;

import com.finance.data.domain.accounts.PersonalData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends CrudRepository<PersonalData, Long> {
    @Override
    <S extends PersonalData> S save(S entity);
}
