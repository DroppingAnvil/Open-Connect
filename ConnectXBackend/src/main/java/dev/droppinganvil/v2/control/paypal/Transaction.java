/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal;

public class Transaction {
    public String id;
    public String status;
    public String payer_email;
    public Name payer_name;
    public AmountBreakdown amount_with_breakdown;
}
