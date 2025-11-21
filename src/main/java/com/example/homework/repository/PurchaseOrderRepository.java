package com.example.homework.repository;


import com.example.homework.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {

}
