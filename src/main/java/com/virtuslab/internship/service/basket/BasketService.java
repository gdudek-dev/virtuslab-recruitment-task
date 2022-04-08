package com.virtuslab.internship.service.basket;

import com.virtuslab.internship.domain.basket.Basket;
import com.virtuslab.internship.domain.receipt.Receipt;
import org.springframework.http.ResponseEntity;

public interface BasketService {
    ResponseEntity<Receipt> getReceipt(Basket basket);
}
