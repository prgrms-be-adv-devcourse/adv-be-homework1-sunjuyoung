package com.example.homework.order.domain;

import com.example.homework.order.application.dto.OrderCommand;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@ToString
@Table(name = "purchase_order", schema = "public")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseOrder {

    @Id
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "seller_id", nullable = false)
    private UUID sellerId;

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PurchaseOrderStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    //생성자 메서드
    public static PurchaseOrder create(OrderCommand command) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.amount = command.amount();
        purchaseOrder.status = PurchaseOrderStatus.CREATED;
        purchaseOrder.productId = command.productId();
        purchaseOrder.sellerId = command.sellerId();
        purchaseOrder.memberId = command.memberId();
        return purchaseOrder;
    }


    public void changeStatus(PurchaseOrderStatus status) {
        this.status = status;
    }


    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (id == null) {
            id = UUID.randomUUID();
        }
        createdAt = now;
        updatedAt = now;
        if (status == null) {
            status = PurchaseOrderStatus.CREATED;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
