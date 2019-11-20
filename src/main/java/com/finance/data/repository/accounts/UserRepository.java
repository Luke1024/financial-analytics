package com.finance.data.repository.accounts;

import com.finance.data.domain.accounts.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query
    Optional<User> findUserByEmail(@Param("EMAIL")String email);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
