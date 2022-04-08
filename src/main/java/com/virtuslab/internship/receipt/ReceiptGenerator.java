package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ReceiptGenerator {

    public Receipt generate(Basket basket) {

        Map<Product, Long> productsGroup = groupAndCountProducts(basket);
        List<ReceiptEntry> receiptEntries = generateReceiptEntries(productsGroup);

        return new Receipt(receiptEntries);
    }

    private List<ReceiptEntry> generateReceiptEntries(Map<Product, Long> productsGroup) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        productsGroup.forEach(
                (product, count) -> receiptEntries.add(new ReceiptEntry(product, count.intValue()))
        );

        return receiptEntries;
    }

    private Map<Product, Long> groupAndCountProducts(Basket basket) {
        return basket.getProducts().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
