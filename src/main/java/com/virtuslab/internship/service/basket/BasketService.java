package com.virtuslab.internship.service.basket;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.receipt.Receipt;
import org.springframework.http.ResponseEntity;

public interface BasketService {
    ResponseEntity<Receipt> getReceipt(Basket basket);
}
