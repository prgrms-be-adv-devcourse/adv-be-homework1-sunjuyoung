package com.example.homework.controller;


import com.example.homework.common.ResponseEntity;
import com.example.homework.domain.PurchaseOrder;
import com.example.homework.domain.PurchaseOrderStatus;
import com.example.homework.dto.OrderInfo;
import com.example.homework.dto.OrderRequest;
import com.example.homework.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1}/orders")
public class PurchaseOrderController {

    private final PurchaseOrderService orderService;

    @Operation(summary = "주문 생성", description = "상품과 구매자 정보를 바탕으로 주문을 생성한다.")
    @PostMapping
    public ResponseEntity<PurchaseOrder> create(@RequestBody OrderRequest request) {
        return orderService.create(request.toCommand());
    }

    @Operation(summary = "주문 목록 조회", description = "생성된 주문을 페이지 단위로 조회한다.")
    @GetMapping
    public ResponseEntity<List<OrderInfo>> findAll(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    @Operation(summary = "주문 상태 변경 PAID", description = "주문상태 결제완료 변경")
    @PatchMapping("{id}/paid")
    public ResponseEntity<PurchaseOrder> updatePaid(@PathVariable UUID id) {
        return orderService.statusChange(id, PurchaseOrderStatus.PAID);
    }

    @Operation(summary = "주문 상태 변경 CANCEL", description = "주문 상태를 취소로 변경")
    @PatchMapping("{id}/cancel")
    public ResponseEntity<PurchaseOrder> updateCancel(@PathVariable UUID id) {
        return orderService.statusChange(id,PurchaseOrderStatus.CANCELLED);
    }

}
