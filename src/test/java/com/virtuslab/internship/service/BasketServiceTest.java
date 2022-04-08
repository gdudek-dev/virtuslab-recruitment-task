package com.virtuslab.internship.service;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.exception.BasketIsEmptyException;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import com.virtuslab.internship.service.basket.impl.BasketServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasketServiceTest {

    @Test
    void shouldReturnReceiptWhenGivenNonEmptyBasket() {
        // Given
        var productDb = new ProductDb();
        var banana = productDb.getProduct("Banana");
        var cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(banana, 2));
        receiptEntries.add(new ReceiptEntry(cereals, 1));

        Basket basket = new Basket();
        basket.addProduct(banana);
        basket.addProduct(banana);
        basket.addProduct(cereals);

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var generator = new ReceiptGenerator();
        var basketService = new BasketServiceImpl(generator, discount);

        // When
        var receiptAfterDiscount = basketService.getReceipt(basket);

        assertEquals(receipt.entries().size(),receiptAfterDiscount.getBody().entries().size());
    }

    @Test
    void shouldThrowErrorWhenBasketEmpty() {
        // Given
        Basket basket = new Basket();

        var discount = new FifteenPercentDiscount();
        var generator = new ReceiptGenerator();
        var basketService = new BasketServiceImpl(generator, discount);

        // When
        Throwable exception = assertThrows(BasketIsEmptyException.class, () -> basketService.getReceipt(basket));
        assertEquals("Basket is empty", exception.getMessage());
    }
}
