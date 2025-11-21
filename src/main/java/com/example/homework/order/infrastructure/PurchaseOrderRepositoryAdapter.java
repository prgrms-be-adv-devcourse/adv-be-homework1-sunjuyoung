package com.example.homework.order.infrastructure;

import com.example.homework.order.domain.PurchaseOrder;
import com.example.homework.order.domain.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PurchaseOrderRepositoryAdapter implements PurchaseOrderRepository {

    private final PurchaseOrderJpaRepository repository;

    @Override
    public Page<PurchaseOrder> findAll(Pageable pageable) {
        return  repository.findAll(pageable);
    }

    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        return repository.save(purchaseOrder);
    }
}
