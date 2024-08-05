package com.playstore.games.application.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateFinalPrice {

    public static BigDecimal calculateFinalPrice(BigDecimal price, int discount) {

        if (discount > 0) {
            BigDecimal discountAmount = price.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100));
            return price.subtract(discountAmount).setScale(2, RoundingMode.HALF_EVEN);
        }

        return price;

    }
}
