package com.example.homework.dto;

import com.example.homework.domain.PurchaseOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderRequest(
                           UUID productId,
                           UUID sellerId,
                           UUID memberId,
                           BigDecimal amount
) {
    public OrderCommand toCommand(){
        return new OrderCommand(
                 productId,
                 sellerId,
                 memberId,
                 amount

        );
    }
}
