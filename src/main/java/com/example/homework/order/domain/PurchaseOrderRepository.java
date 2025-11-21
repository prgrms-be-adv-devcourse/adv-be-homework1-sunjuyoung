package com.example.homework.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseOrderRepository {

    Page<PurchaseOrder> findAll(Pageable pageable);

    PurchaseOrder save(PurchaseOrder purchaseOrder);

}
