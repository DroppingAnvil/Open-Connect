/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal;

import java.io.Serializable;

public class PaymentMethod implements Serializable {
    //Guessing it needs to be PAYPAL?
    public String payer_selected;
    public PayeePreferred payee_preferred;
    //Should be WEB
    public NACHACode standard_entry_class_code;
}
