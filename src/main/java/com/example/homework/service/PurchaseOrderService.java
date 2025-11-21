package com.example.homework.service;

import com.example.homework.common.ResponseEntity;
import com.example.homework.domain.PurchaseOrder;
import com.example.homework.domain.PurchaseOrderStatus;
import com.example.homework.dto.OrderCommand;
import com.example.homework.dto.OrderInfo;
import com.example.homework.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public ResponseEntity<PurchaseOrder> create(OrderCommand command) {

        PurchaseOrder purchaseOrder = PurchaseOrder.create(command);

        PurchaseOrder save = purchaseOrderRepository.save(purchaseOrder);

        return new ResponseEntity<>(HttpStatus.CREATED.value(), save,1);
    }

    public ResponseEntity<List<OrderInfo>> findAll(Pageable pageable) {

        Page<PurchaseOrder> page = purchaseOrderRepository.findAll(pageable);

        List<OrderInfo> list = page.stream().map(order -> OrderInfo.from(order)).toList();

        return new ResponseEntity<>(HttpStatus.OK.value(), list, page.getNumberOfElements());

    }

    public ResponseEntity<PurchaseOrder> statusChange(UUID id, PurchaseOrderStatus status) {

        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Purchase Order Not Found"));

        order.setStatus(status);

        purchaseOrderRepository.save(order);

        return new ResponseEntity<>(HttpStatus.CREATED.value(), order,1);
    }
}


