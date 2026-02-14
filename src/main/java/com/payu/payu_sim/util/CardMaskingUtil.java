package com.payu.payu_sim.util;

public class CardMaskingUtil {

    public static String mask(String cardNumber) {

        if (cardNumber == null || cardNumber.length() < 10) {

            return "INVALID";
        }

        String first6 = cardNumber.substring(0, 6);
        String last4 = cardNumber.substring(cardNumber.length() - 4);

        return first6 + "******" + last4;
    }
}
