/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control.paypal;

import java.io.Serializable;

public class PaymentCard implements Serializable {
    String id;
    String name;
    String number;
    String expiry;
    String security_code;
    String last_digits;
    CardType card_type;
}
