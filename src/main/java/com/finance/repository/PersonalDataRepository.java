package com.finance.repository;

import com.finance.domain.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends CrudRepository<UserData, Long> {
    @Override
    <S extends UserData> S save(S entity);
}
