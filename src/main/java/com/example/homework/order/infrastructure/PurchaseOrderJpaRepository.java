package com.example.homework.order.infrastructure;


import com.example.homework.order.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseOrderJpaRepository extends JpaRepository<PurchaseOrder, UUID> {

}
