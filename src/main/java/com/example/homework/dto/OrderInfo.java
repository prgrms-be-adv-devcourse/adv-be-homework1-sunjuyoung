package com.example.homework.dto;


import com.example.homework.domain.PurchaseOrder;
import com.example.homework.domain.PurchaseOrderStatus;


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
