package com.finance.data.repository.accounts;

import com.finance.data.domain.accounts.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
