/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal;

import java.io.Serializable;

public enum PayeePreferred implements Serializable {
    UNRESTRICTED,
    IMMEDIATE_PAYMENT_REQUIRED,
}
