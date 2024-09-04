package com.playstore.orders.infrastructure.outputAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.playstore.orders.domain.Order;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.code_operation = :code_operation")
    Optional<Order> findByCodeOperation(@Param("code_operation") String code_operation);

}
