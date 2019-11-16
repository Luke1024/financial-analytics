package com.finance.data.mapper.currency.currency;

import com.finance.data.domain.currency.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
