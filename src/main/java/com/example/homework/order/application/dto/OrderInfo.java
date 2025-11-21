package com.example.homework.order.application.dto;


import com.example.homework.order.domain.PurchaseOrder;
import com.example.homework.order.domain.PurchaseOrderStatus;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderInfo(
        UUID id,
        UUID productId,
        UUID sellerId,
        UUID memberId,
        BigDecimal amount,
        PurchaseOrderStatus status,
        LocalDateTime createdAt
) {


    public static OrderInfo from(PurchaseOrder order){
        return new OrderInfo(
                order.getId(),
                order.getProductId(),
                order.getSellerId(),
                order.getMemberId(),
                order.getAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

}
