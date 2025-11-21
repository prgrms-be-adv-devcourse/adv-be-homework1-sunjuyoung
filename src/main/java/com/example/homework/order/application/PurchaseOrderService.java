package com.example.homework.order.application;

import com.example.homework.order.common.ResponseEntity;
import com.example.homework.order.domain.PurchaseOrder;
import com.example.homework.order.domain.PurchaseOrderStatus;
import com.example.homework.order.application.dto.OrderCommand;
import com.example.homework.order.application.dto.OrderInfo;
import com.example.homework.order.infrastructure.PurchaseOrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderJpaRepository purchaseOrderJpaRepository;

    public ResponseEntity<OrderInfo> create(OrderCommand command) {

        PurchaseOrder purchaseOrder = PurchaseOrder.create(command);

        PurchaseOrder saved = purchaseOrderJpaRepository.save(purchaseOrder);

        return new ResponseEntity<>(HttpStatus.CREATED.value(),  OrderInfo.from(saved),1);
    }

    public ResponseEntity<List<OrderInfo>> findAll(Pageable pageable) {

        Page<PurchaseOrder> page = purchaseOrderJpaRepository.findAll(pageable);

        List<OrderInfo> list = page.stream().map(order -> OrderInfo.from(order)).toList();

        return new ResponseEntity<>(HttpStatus.OK.value(), list, page.getNumberOfElements());
    }

    public ResponseEntity<OrderInfo> statusChange(UUID id, PurchaseOrderStatus status) {

        PurchaseOrder order = purchaseOrderJpaRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Purchase Order Not Found"));

        order.changeStatus(status);

        PurchaseOrder saved = purchaseOrderJpaRepository.save(order);

        return new ResponseEntity<>(HttpStatus.OK.value(), OrderInfo.from(saved),1);
    }
}


