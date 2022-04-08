package com.virtuslab.internship.domain.discount;

import com.virtuslab.internship.domain.receipt.Receipt;

import java.math.BigDecimal;

public abstract class Discount {

    protected Receipt apply(Receipt receipt, String discountName, double discountValue ) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(discountValue));
            var discounts = receipt.discounts();
            discounts.add(discountName);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    protected abstract boolean shouldApply(Receipt receipt);
}
