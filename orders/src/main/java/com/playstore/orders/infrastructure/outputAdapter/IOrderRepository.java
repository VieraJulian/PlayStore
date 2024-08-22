package com.playstore.orders.infrastructure.outputAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.playstore.orders.domain.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

}
