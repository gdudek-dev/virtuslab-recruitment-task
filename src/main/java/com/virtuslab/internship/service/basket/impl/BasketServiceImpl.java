package com.virtuslab.internship.service.basket.impl;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.exception.BasketIsEmptyException;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import com.virtuslab.internship.service.basket.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService {

    private final ReceiptGenerator receiptGenerator;
    private final FifteenPercentDiscount fifteenPercentDiscount;

    public BasketServiceImpl(ReceiptGenerator receiptGenerator, FifteenPercentDiscount fifteenPercentDiscount) {
        this.receiptGenerator = receiptGenerator;
        this.fifteenPercentDiscount = fifteenPercentDiscount;
    }

    @Override
    public ResponseEntity<Receipt> getReceipt(Basket basket) {

        if (basket == null || basket.getProducts().isEmpty()) {
            throw new BasketIsEmptyException();
        }

        Receipt receipt = receiptGenerator.generate(basket);
        receipt = fifteenPercentDiscount.apply(receipt);

        return ResponseEntity.ok(receipt);
    }
}
