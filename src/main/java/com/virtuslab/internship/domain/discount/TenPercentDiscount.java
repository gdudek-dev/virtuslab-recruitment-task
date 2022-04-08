package com.virtuslab.internship.domain.discount;

import com.virtuslab.internship.domain.receipt.Receipt;

import java.math.BigDecimal;

public class TenPercentDiscount extends Discount{

    private static final String NAME = "TenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        return this.apply(receipt, NAME, 0.9);
    }

    protected boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) >= 0;
    }
}
