package com.finance.repository;

import com.finance.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query
    List<Order> findOrderByOrderClosedNull(@Param("USER_ID") Long tradingAccountId);
}
