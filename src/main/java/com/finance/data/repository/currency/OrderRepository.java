package com.finance.data.repository.currency;

import com.finance.data.domain.currency.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query
    List<Order> findOrderByOrderClosedAfterAnd(Long userId, LocalDateTime dateTime);

    List<Order> findOrderByOrderClosedNull(Long userId);
}
