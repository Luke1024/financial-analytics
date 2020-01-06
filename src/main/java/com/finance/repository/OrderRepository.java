package com.finance.repository;

import com.finance.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {


    List<Order> findOrderByOrderClosedNull(Long userId);
}
