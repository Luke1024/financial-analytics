package com.finance.data.repository.accounts;

import com.finance.data.domain.accounts.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends CrudRepository<UserData, Long> {
    @Override
    <S extends UserData> S save(S entity);
}
