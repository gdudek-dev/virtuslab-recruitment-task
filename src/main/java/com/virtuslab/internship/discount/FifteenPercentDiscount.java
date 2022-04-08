package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

public class FifteenPercentDiscount extends Discount {

    private static final String NAME = "FifteenPercentDiscount";
    private final TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();

    public Receipt apply(Receipt receipt) {
       receipt = this.apply(receipt, NAME, 0.85);
       return tenPercentDiscount.apply(receipt);
    }

    protected boolean shouldApply(Receipt receipt) {
        return receiptHasAtLeastGivenNumberOfProductType(receipt, Product.Type.GRAINS, 3);
    }

    private boolean receiptHasAtLeastGivenNumberOfProductType(Receipt receipt, Product.Type type, int number) {
        int count = 0;

        for (ReceiptEntry receiptEntry : receipt.entries()) {
            if (receiptEntry.product().type().equals(type)) {
                count += receiptEntry.quantity();
            }
        }

        return count >= number;
    }
}
